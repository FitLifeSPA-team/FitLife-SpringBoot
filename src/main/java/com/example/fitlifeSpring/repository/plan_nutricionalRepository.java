package com.example.fitlifeSpring.repository;
import com.example.fitlifeSpring.model.plan_nutricional;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface plan_nutricionalRepository extends JpaRepository<plan_nutricional, Long> {
    List<plan_nutricional> findByNombre_nutriContainingIgnoreCase(String nombre_nutri);
}
