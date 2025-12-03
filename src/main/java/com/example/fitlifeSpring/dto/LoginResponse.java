package com.example.fitlifeSpring.dto;

import com.example.fitlifeSpring.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String tipo = "Bearer";
    private Long id;
    private String username;
    private String email;
    private String nombre;
    private String apellido;
    private Role role;
    
    public LoginResponse(String token, Long id, String username, String email, 
                        String nombre, String apellido, Role role) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.role = role;
    }
}