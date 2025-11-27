package com.example.fitlifeSpring.repository;

import com.example.fitlifeSpring.model.plan_entrenamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface plan_entrenamientoRepository extends JpaRepository<plan_entrenamiento, Long> {
    @Query("SELECT p FROM plan_entrenamiento p WHERE LOWER(p.nombre_plan) LIKE LOWER(CONCAT('%', :nombrePlan, '%'))")
    List<plan_entrenamiento> findByNombrePlanContainingIgnoreCase(@Param("nombrePlan") String nombrePlan);
}
