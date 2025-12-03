package com.example.fitlifeSpring.controller;

import com.example.fitlifeSpring.dto.LoginRequest;
import com.example.fitlifeSpring.dto.LoginResponse;
import com.example.fitlifeSpring.dto.RegisterRequest;
import com.example.fitlifeSpring.model.Role;
import com.example.fitlifeSpring.model.usuario;
import com.example.fitlifeSpring.repository.usuarioRepository;
import com.example.fitlifeSpring.security.JwtTokenProvider;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Autenticación", description = "Endpoints para login y registro")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private usuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Operation(summary = "Login de usuario", description = "Autentica usuario y devuelve token JWT")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Login exitoso"),
        @ApiResponse(responseCode = "401", description = "Credenciales inválidas")
    })
    @PostMapping("/login")
public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
    try {
        // Buscar usuario por email PRIMERO
        usuario user = usuarioRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Autenticar con USERNAME (no email)
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                user.getUsername(),  // <--- USAR USERNAME
                loginRequest.getPassword()
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generar token
        String token = tokenProvider.generateToken(user.getUsername());

        // Crear respuesta
        LoginResponse response = new LoginResponse(
            token,
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getNombre(),
            user.getApellido(),
            user.getRole()
        );

        return ResponseEntity.ok(response);

    } catch (Exception e) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Credenciales inválidas: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }
}
    @Operation(summary = "Registro de nuevo usuario")
    @ApiResponse(responseCode = "201", description = "Usuario registrado exitosamente")
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        try {
            
            if (usuarioRepository.existsByUsername(registerRequest.getUsername())) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "El nombre de usuario ya está en uso");
                return ResponseEntity.badRequest().body(error);
            }

            
            if (usuarioRepository.existsByEmail(registerRequest.getEmail())) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "El email ya está registrado");
                return ResponseEntity.badRequest().body(error);
            }

            
            usuario nuevoUsuario = new usuario();
            nuevoUsuario.setNombre(registerRequest.getNombre());
            nuevoUsuario.setApellido(registerRequest.getApellido());
            nuevoUsuario.setEmail(registerRequest.getEmail());
            nuevoUsuario.setUsername(registerRequest.getUsername());
            nuevoUsuario.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            nuevoUsuario.setTelefono(registerRequest.getTelefono());
            nuevoUsuario.setDireccion(registerRequest.getDireccion());
            nuevoUsuario.setRole(Role.USER);
            nuevoUsuario.setActivo(true);

            usuario usuarioGuardado = usuarioRepository.save(nuevoUsuario);

            
            String token = tokenProvider.generateToken(usuarioGuardado.getUsername());

            LoginResponse response = new LoginResponse(
                token,
                usuarioGuardado.getId(),
                usuarioGuardado.getUsername(),
                usuarioGuardado.getEmail(),
                usuarioGuardado.getNombre(),
                usuarioGuardado.getApellido(),
                usuarioGuardado.getRole()
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al registrar usuario: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @Operation(summary = "Obtener usuario actual")
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String username = authentication.getName();
        usuario user = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Map<String, Object> response = new HashMap<>();
        response.put("id", user.getId());
        response.put("username", user.getUsername());
        response.put("email", user.getEmail());
        response.put("nombre", user.getNombre());
        response.put("apellido", user.getApellido());
        response.put("role", user.getRole());

        return ResponseEntity.ok(response);
    }
}