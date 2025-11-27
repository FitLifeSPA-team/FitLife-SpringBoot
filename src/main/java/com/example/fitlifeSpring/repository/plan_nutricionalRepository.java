package com.example.fitlifeSpring.repository;
import com.example.fitlifeSpring.model.plan_nutricional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface plan_nutricionalRepository extends JpaRepository<plan_nutricional, Long> {
    @Query("SELECT p FROM plan_nutricional p WHERE LOWER(p.nombre_nutri) LIKE LOWER(CONCAT('%', :nombreNutri, '%'))")
    List<plan_nutricional> findByNombre_nutriContainingIgnoreCase(@Param("nombreNutri") String nombreNutri);
}