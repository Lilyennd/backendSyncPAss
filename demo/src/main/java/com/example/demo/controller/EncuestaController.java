package com.example.demo.controller;

import com.example.demo.model.Encuesta;
import com.example.demo.repository.EncuestaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/encuestas")
public class EncuestaController {

    private final EncuestaRepository encuestaRepository;

    public EncuestaController(EncuestaRepository encuestaRepository) {
        this.encuestaRepository = encuestaRepository;
    }

    @PostMapping("/sync")
    public ResponseEntity<?> sincronizar(@RequestBody List<Encuesta> lote) {
        List<String> sincronizadosIds = new ArrayList<>();
        int guardadas = 0;

        for (Encuesta e : lote) {
            if (e.getId() != null && !e.getId().isBlank()) {
                encuestaRepository.save(e);
                sincronizadosIds.add(e.getId());
                guardadas++;
            }
        }

        return ResponseEntity.ok(Map.of(
                "recibidas", lote.size(),
                "guardadas", guardadas,
                "sincronizadosIds", sincronizadosIds
        ));
    }

    @GetMapping
    public List<Encuesta> listar() {
        return encuestaRepository.findAll();
    }
}