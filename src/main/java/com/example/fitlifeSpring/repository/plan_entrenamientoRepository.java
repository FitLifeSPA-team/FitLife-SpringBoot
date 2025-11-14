package com.example.fitlifeSpring.repository;

import com.example.fitlifeSpring.model.plan_entrenamiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface plan_entrenamientoRepository extends JpaRepository<plan_entrenamiento, Long> {
    List<plan_entrenamiento> findByNombre_PlanContainingIgnoreCase(String nombre_plan);
}
