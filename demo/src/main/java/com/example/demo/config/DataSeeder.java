package com.example.demo.config;

import com.example.demo.model.Evento;
import com.example.demo.model.User;
import com.example.demo.repository.EventoRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner cargarDatosDePrueba(UserRepository usuarios,
                                          EventoRepository eventos,
                                          PasswordEncoder encoder) {
        return args -> {
            if (usuarios.findByCorreo("docente@syncpass.cl").isEmpty()) {
                usuarios.save(new User("Profesor Carlos", "docente@syncpass.cl", encoder.encode("123456"), "Docente"));
            }
            if (usuarios.findByCorreo("admin@syncpass.cl").isEmpty()) {
                usuarios.save(new User("Administrador General", "admin@syncpass.cl", encoder.encode("123456"), "Docente"));
            }
            if (usuarios.findByCorreo("alumno@syncpass.cl").isEmpty()) {
                usuarios.save(new User("Estudiante Matías", "alumno@syncpass.cl", encoder.encode("123456"), "Alumno"));
            }
            if (usuarios.findByCorreo("asistente@syncpass.cl").isEmpty()) {
                usuarios.save(new User("Camila Asistente", "asistente@syncpass.cl", encoder.encode("123456"), "Asistente"));
            }

            if (eventos.count() == 0) {
                eventos.save(new Evento("Trekking", "Ruta guiada por el Cerro Santa Lucía...", "Ruta guiada por el Cerro...", "18/30", "Hoy • 2 hr", "18 participantes", "09:00 - 10:00 hrs.", "29/09/2026", -33.4410, -70.6440));
                eventos.save(new Evento("Mente activa", "Desafíos lúdicos...", "Desafíos lúdicos...", "15/30", "Martes • 1:20 hr", "15 participantes", "10:00 - 11:20 hrs.", "30/09/2026", -33.4425, -70.6500));
                eventos.save(new Evento("Vida en marcha", "Sesiones de ejercicio...", "Sesiones de ejercicio...", "15/30", "Viernes • 1 hr", "15 participantes", "11:00 - 12:00 hrs.", "02/10/2026", -33.4380, -70.6400));
            }
        };
    }
}