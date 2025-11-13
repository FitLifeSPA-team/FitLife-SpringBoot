package com.example.fitlifeSpring.repository;

import com.example.fitlifeSpring.model.entrenador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface entrenadorRepository extends JpaRepository<entrenador, Long> {
    List<entrenador> findByNombreContainingIgnoreCase(String nombre);
}
