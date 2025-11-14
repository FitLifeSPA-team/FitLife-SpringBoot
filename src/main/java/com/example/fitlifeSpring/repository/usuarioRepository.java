package com.example.fitlifeSpring.repository;
import com.example.fitlifeSpring.model.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface usuarioRepository extends JpaRepository<usuario, Long> {
    List<usuario> findByNombreContainingIgnoreCase(String nombre);

    
}

