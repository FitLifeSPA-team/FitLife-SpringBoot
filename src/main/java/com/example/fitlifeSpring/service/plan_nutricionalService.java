package com.example.fitlifeSpring.service;

import com.example.fitlifeSpring.model.plan_nutricional;
import java.util.List;

public interface plan_nutricionalService {
    List<plan_nutricional> listar();
    plan_nutricional buscarPorId(Long id);
    List<plan_nutricional> buscarPornombre_nutri(String q);
    plan_nutricional crear(plan_nutricional plan_nutricional);
    plan_nutricional actualizar(Long id, plan_nutricional plan_nutricional);
    void eliminar(Long id);
}
