package com.example.fitlifeSpring.config;

import com.example.fitlifeSpring.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.http.HttpMethod;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configure(http))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                // ============================================
                // RUTAS PÚBLICAS (sin autenticación)
                // ============================================
                .requestMatchers("/api/v1/auth/**").permitAll()
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("/doc/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                
                // ============================================
                // PERMITIR VER DATOS PÚBLICAMENTE (GET)
                // ============================================
                // Permitir que cualquiera VEA entrenadores, planes y nutrición
                .requestMatchers(HttpMethod.GET, "/api/v1/entrenador/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/plan_entrenamiento/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/plan_nutricional/**").permitAll()
                
                // ============================================
                // ENTRENADORES (Modificación requiere autenticación)
                // ============================================
                .requestMatchers(HttpMethod.POST, "/api/v1/entrenador/**").hasAnyRole("ADMIN", "TRAINER")
                .requestMatchers(HttpMethod.PUT, "/api/v1/entrenador/**").hasAnyRole("ADMIN", "TRAINER")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/entrenador/**").hasAnyRole("ADMIN", "TRAINER")
                
                // ============================================
                // PLANES DE ENTRENAMIENTO (Solo ADMIN modifica)
                // ============================================
                .requestMatchers(HttpMethod.POST, "/api/v1/plan_entrenamiento/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/v1/plan_entrenamiento/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/plan_entrenamiento/**").hasRole("ADMIN")
                
                // ============================================
                // PLANES NUTRICIONALES (Solo ADMIN modifica)
                // ============================================
                .requestMatchers(HttpMethod.POST, "/api/v1/plan_nutricional/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/v1/plan_nutricional/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/plan_nutricional/**").hasRole("ADMIN")
                
                // ============================================
                // USUARIOS
                // ============================================
                .requestMatchers(HttpMethod.GET, "/api/v1/usuario/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/v1/usuario/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/v1/usuario/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/api/v1/usuario/**").hasRole("ADMIN")
                
                // Resto de rutas requieren autenticación
                .anyRequest().authenticated()
            );

        // Agregar filtro JWT
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        // Para H2 Console
        http.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));

        return http.build();
    }
}