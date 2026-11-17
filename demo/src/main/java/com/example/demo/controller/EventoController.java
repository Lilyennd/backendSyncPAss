package com.example.demo.controller;

import com.example.demo.model.Evento;
import com.example.demo.repository.EventoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {
    private final EventoRepository eventoRepository;
    public EventoController(EventoRepository eventoRepository) { this.eventoRepository = eventoRepository; }

    @GetMapping
    public List<Evento> listar() { return eventoRepository.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> obtener(@PathVariable Long id) {
        return eventoRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Evento crear(@RequestBody Evento evento) {
        evento.setId(null);
        return eventoRepository.save(evento);
    }
}