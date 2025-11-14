package com.example.fitlifeSpring.repository;
import com.example.fitlifeSpring.model.progreso;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface progresoRepository extends JpaRepository<progreso, Long> {
    List<progreso> findByNombre_nutriContainingIgnoreCase(String nombre_nutri);
}