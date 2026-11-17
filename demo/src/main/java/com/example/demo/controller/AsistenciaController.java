package com.example.demo.controller;

import com.example.demo.model.Asistencia;
import com.example.demo.repository.AsistenciaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/asistencias")
public class AsistenciaController {

    private final AsistenciaRepository asistenciaRepository;

    public AsistenciaController(AsistenciaRepository asistenciaRepository) {
        this.asistenciaRepository = asistenciaRepository;
    }

    @PostMapping("/sync")
    public ResponseEntity<?> sincronizar(@RequestBody List<Asistencia> lote) {
        int guardadas = 0;
        int duplicadas = 0;
        int invalidas = 0;
        List<String> sincronizadosIds = new ArrayList<>();

        for (Asistencia a : lote) {
            boolean sinId = a.getId() == null || a.getId().isBlank();
            boolean sinCodigo = a.getCodigoAsistente() == null || a.getCodigoAsistente().isBlank();

            if (sinId || sinCodigo) {
                invalidas++;
            } else if (asistenciaRepository.existsById(a.getId())) {
                duplicadas++;
                sincronizadosIds.add(a.getId());
            } else {
                asistenciaRepository.save(a);
                guardadas++;
                sincronizadosIds.add(a.getId());
            }
        }

        return ResponseEntity.ok(Map.of(
                "recibidas", lote.size(),
                "guardadas", guardadas,
                "duplicadas", duplicadas,
                "invalidas", invalidas,
                "sincronizadosIds", sincronizadosIds
        ));
    }

    @GetMapping
    public List<Asistencia> listar(@RequestParam(required = false) Long eventoId) {
        if (eventoId != null) {
            return asistenciaRepository.findByEventoId(eventoId);
        }
        return asistenciaRepository.findAll();
    }
}