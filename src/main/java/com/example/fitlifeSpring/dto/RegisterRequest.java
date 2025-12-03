package com.example.fitlifeSpring.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    
    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;
    
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Email inválido")
    private String email;
    
    @NotBlank(message = "El username es obligatorio")
    private String username;
    
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 4, message = "La contraseña debe tener al menos 4 caracteres")
    private String password;
    
    @Pattern(regexp = "^[0-9]{8,15}$", message = "Teléfono inválido (8-15 dígitos)")
    private String telefono;
    
    private String direccion;
}