package com.example.demo.controller;

import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Set<String> ROLES = Set.of(
            "Docente", "Alumno", "Asistente",
            "Coordinador", "Voluntario", "Beneficiario"
    );

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        String error = validar(req);
        if (error != null) {
            return ResponseEntity.badRequest().body(Map.of("mensaje", error));
        }

        String correo = req.correo().trim();
        if (userRepository.findByCorreo(correo).isPresent()) {
            return ResponseEntity.status(409).body(Map.of("mensaje", "Este correo ya está registrado"));
        }

        User usuario = new User(
                req.nombre().trim(),
                correo,
                passwordEncoder.encode(req.password()),
                req.rol()
        );
        userRepository.save(usuario);

        String token = jwtUtils.generateToken(correo);
        return ResponseEntity.ok(new AuthResponse(token, usuario.getNombre(), usuario.getRol()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        if (req.correo() == null || req.password() == null) {
            return ResponseEntity.status(401).body(Map.of("mensaje", "Credenciales incorrectas"));
        }

        Optional<User> encontrado = userRepository.findByCorreo(req.correo().trim());
        if (encontrado.isPresent()
                && passwordEncoder.matches(req.password(), encontrado.get().getPassword())) {
            User usuario = encontrado.get();
            String token = jwtUtils.generateToken(usuario.getCorreo());
            return ResponseEntity.ok(new AuthResponse(token, usuario.getNombre(), usuario.getRol()));
        }

        return ResponseEntity.status(401).body(Map.of("mensaje", "Credenciales incorrectas"));
    }

    private String validar(RegisterRequest req) {
        if (req.nombre() == null || req.nombre().isBlank()) return "Ingresa tu nombre";
        if (req.correo() == null || !req.correo().contains("@")) return "El correo no es válido";
        if (req.password() == null || req.password().length() < 6)
            return "La contraseña debe tener al menos 6 caracteres";
        if (req.rol() == null || !ROLES.contains(req.rol())) return "Rol no válido: use Docente, Alumno o Asistente";
        return null;
    }
}