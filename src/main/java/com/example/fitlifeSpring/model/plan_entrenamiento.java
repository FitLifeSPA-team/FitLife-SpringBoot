package com.example.fitlifeSpring.model;

import jakarta.persistence.*;
import lombok.Data;




@Entity
@Table(name = "plan_entrenamiento")
@Data
public class plan_entrenamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Column(nullable = false)
    private String nombre_plan;

    
    @Column(nullable = false, unique = true)
    private int precio;

    
    @Column(nullable = false)
    private String descripcion;

    private Boolean activo;

    
}
