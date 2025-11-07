package com.example.fitlifeSpring.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "plan_nutricional")
@Data
public class plan_nutricional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Column(nullable = false)
    private String nombre_nutri;

    @Column(nullable = false, unique = true)
    private String descripcion_nutri;

    @Column(nullable = false, unique = true)
    private int precio;

    private Boolean activo;

    // getters y setters (o usa Lombok @Data)
}
