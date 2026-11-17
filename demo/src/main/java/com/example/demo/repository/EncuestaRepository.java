package com.example.demo.repository;
import com.example.demo.model.Encuesta;
import org.springframework.data.jpa.repository.JpaRepository;
public interface EncuestaRepository extends JpaRepository<Encuesta, String> {}
