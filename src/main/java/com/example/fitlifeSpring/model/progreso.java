package com.example.fitlifeSpring.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "progreso")
@Data
public class progreso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Column(nullable = false)
    private String progreso;

    @Column(nullable = false, unique = true)
    private String descripcion_progreso;

    private Boolean activo;

    // getters y setters (o usa Lombok @Data)
}
