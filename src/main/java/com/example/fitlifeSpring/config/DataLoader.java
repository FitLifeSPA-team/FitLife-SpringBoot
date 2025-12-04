package com.example.fitlifeSpring.config;

import com.example.fitlifeSpring.model.*;
import com.example.fitlifeSpring.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(
            entrenadorRepository entrenadorRepo,
            plan_entrenamientoRepository planEntrenamientoRepo,
            plan_nutricionalRepository planNutricionalRepo,
            usuarioRepository usuarioRepo,
            PasswordEncoder passwordEncoder) {
        
        return args -> {
            // Solo cargar datos si las tablas están vacías
            if (entrenadorRepo.count() == 0) {
                System.out.println("Cargando datos de ejemplo...");

                // ===== ENTRENADORES =====
                entrenador e1 = new entrenador();
                e1.setNombre("Carlos");
                e1.setApellido("González");
                e1.setEmail("carlos@fitlyfe.com");
                e1.setTelefono("912345678");
                e1.setActivo(true);
                entrenadorRepo.save(e1);

                entrenador e2 = new entrenador();
                e2.setNombre("María");
                e2.setApellido("Silva");
                e2.setEmail("maria@fitlyfe.com");
                e2.setTelefono("987654321");
                e2.setActivo(true);
                entrenadorRepo.save(e2);

                entrenador e3 = new entrenador();
                e3.setNombre("Pedro");
                e3.setApellido("Ramírez");
                e3.setEmail("pedro@fitlyfe.com");
                e3.setTelefono("956789012");
                e3.setActivo(true);
                entrenadorRepo.save(e3);

                // ===== PLANES DE ENTRENAMIENTO =====
                plan_entrenamiento p1 = new plan_entrenamiento();
                p1.setNombre_plan("Plan Básico");
                p1.setDescripcion("Ideal para principiantes. Incluye rutinas de 3 días por semana.");
                p1.setPrecio(15000);
                p1.setActivo(true);
                planEntrenamientoRepo.save(p1);

                plan_entrenamiento p2 = new plan_entrenamiento();
                p2.setNombre_plan("Plan Intermedio");
                p2.setDescripcion("Para quienes buscan mejorar. 4 días de entrenamiento semanal.");
                p2.setPrecio(25000);
                p2.setActivo(true);
                planEntrenamientoRepo.save(p2);

                plan_entrenamiento p3 = new plan_entrenamiento();
                p3.setNombre_plan("Plan Avanzado");
                p3.setDescripcion("Entrenamiento intensivo 5-6 días. Incluye asesoría personalizada.");
                p3.setPrecio(40000);
                p3.setActivo(true);
                planEntrenamientoRepo.save(p3);

                // ===== PLANES NUTRICIONALES =====
                plan_nutricional n1 = new plan_nutricional();
                n1.setNombre_nutri("Nutrición Básica");
                n1.setDescripcion_nutri("Plan nutricional general con menús semanales.");
                n1.setPrecio(12000);
                n1.setActivo(true);
                planNutricionalRepo.save(n1);

                plan_nutricional n2 = new plan_nutricional();
                n2.setNombre_nutri("Nutrición Personalizada");
                n2.setDescripcion_nutri("Plan adaptado a tus objetivos con seguimiento mensual.");
                n2.setPrecio(30000);
                n2.setActivo(true);
                planNutricionalRepo.save(n2);

                plan_nutricional n3 = new plan_nutricional();
                n3.setNombre_nutri("Nutrición Deportiva");
                n3.setDescripcion_nutri("Para atletas. Incluye suplementación y macros personalizados.");
                n3.setPrecio(45000);
                n3.setActivo(true);
                planNutricionalRepo.save(n3);

                // ===== USUARIOS DE PRUEBA =====
                // Usuario Admin
                if (!usuarioRepo.existsByUsername("admin")) {
                    usuario admin = new usuario();
                    admin.setNombre("Admin");
                    admin.setApellido("Sistema");
                    admin.setEmail("admin@fitlyfe.com");
                    admin.setUsername("admin");
                    admin.setPassword(passwordEncoder.encode("admin123"));
                    admin.setTelefono("900000000");
                    admin.setDireccion("Oficina Central");
                    admin.setRole(Role.ADMIN);
                    admin.setActivo(true);
                    usuarioRepo.save(admin);
                }

                // Usuario Trainer
                if (!usuarioRepo.existsByUsername("trainer1")) {
                    usuario trainer = new usuario();
                    trainer.setNombre("Trainer");
                    trainer.setApellido("Demo");
                    trainer.setEmail("trainer@fitlyfe.com");
                    trainer.setUsername("trainer1");
                    trainer.setPassword(passwordEncoder.encode("trainer123"));
                    trainer.setTelefono("911111111");
                    trainer.setRole(Role.TRAINER);
                    trainer.setActivo(true);
                    usuarioRepo.save(trainer);
                }

                // Usuario Normal
                if (!usuarioRepo.existsByUsername("usuario1")) {
                    usuario user = new usuario();
                    user.setNombre("Juan");
                    user.setApellido("Pérez");
                    user.setEmail("juan@example.com");
                    user.setUsername("usuario1");
                    user.setPassword(passwordEncoder.encode("1234"));
                    user.setTelefono("922222222");
                    user.setRole(Role.USER);
                    user.setActivo(true);
                    usuarioRepo.save(user);
                }

                System.out.println("✅ Datos de ejemplo cargados exitosamente!");
                System.out.println("📝 Usuarios de prueba:");
                System.out.println("   Admin: admin / admin123");
                System.out.println("   Trainer: trainer1 / trainer123");
                System.out.println("   Usuario: usuario1 / 1234");
            } else {
                System.out.println("⚠️  Base de datos ya contiene datos. No se cargaron ejemplos.");
            }
        };
    }
}