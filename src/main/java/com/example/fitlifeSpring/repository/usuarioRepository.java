package com.example.fitlifeSpring.repository;

import com.example.fitlifeSpring.model.usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface usuarioRepository extends JpaRepository<usuario, Long> {
    List<usuario> findByNombreContainingIgnoreCase(String nombre);
    
    // NUEVO: Buscar por username
    Optional<usuario> findByUsername(String username);
    
    // NUEVO: Buscar por email
    Optional<usuario> findByEmail(String email);
    
    // NUEVO: Verificar si existe username
    boolean existsByUsername(String username);
    
    // NUEVO: Verificar si existe email
    boolean existsByEmail(String email);
}