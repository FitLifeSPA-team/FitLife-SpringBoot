package com.example.fitlifeSpring.repository;
import com.example.fitlifeSpring.model.progreso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface progresoRepository extends JpaRepository<progreso, Long> {
    @Query("SELECT p FROM progreso p WHERE LOWER(p.progreso) LIKE LOWER(CONCAT('%', :progreso, '%'))")
    List<progreso> findByProgresoContainingIgnoreCase(@Param("progreso") String progreso);
}