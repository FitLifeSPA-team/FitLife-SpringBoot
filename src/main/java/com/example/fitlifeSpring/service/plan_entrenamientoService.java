package com.example.fitlifeSpring.service;

import com.example.fitlifeSpring.model.plan_entrenamiento;
import java.util.List;

public interface plan_entrenamientoService {
    List<plan_entrenamiento> listar();
    plan_entrenamiento buscarPorId(Long id);
    List<plan_entrenamiento> buscarPorNombre_Plan(String q);
    plan_entrenamiento crear(plan_entrenamiento plan_entrenamiento);
    plan_entrenamiento actualizar(Long id, plan_entrenamiento plan_entrenamiento);
    void eliminar(Long id);
}
