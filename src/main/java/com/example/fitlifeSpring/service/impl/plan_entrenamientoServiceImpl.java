package com.example.fitlifeSpring.service.impl;

import com.example.fitlifeSpring.model.plan_entrenamiento;
import com.example.fitlifeSpring.repository.plan_entrenamientoRepository;
import com.example.fitlifeSpring.service.plan_entrenamientoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class plan_entrenamientoServiceImpl implements plan_entrenamientoService {
    
    private final plan_entrenamientoRepository repository;
    public plan_entrenamientoServiceImpl(plan_entrenamientoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<plan_entrenamiento> listar() {
        return repository.findAll();
    }

    @Override
    public plan_entrenamiento buscarPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Entrenador no encontrado con id: " + id));
    }

    @Override
    public List<plan_entrenamiento> buscarPorNombre_Plan(String q) {
        return repository.findByNombrePlanContainingIgnoreCase(q);
    }

    @Override
    public plan_entrenamiento crear(plan_entrenamiento plan_entrenamiento) {
        if (plan_entrenamiento.getActivo() == null) {
            plan_entrenamiento.setActivo(true);
        }
        return repository.save(plan_entrenamiento);
    }

    @Override
    public plan_entrenamiento actualizar(Long id, plan_entrenamiento plan_entrenamiento) {
        plan_entrenamiento existente = buscarPorId(id);
        existente.setNombre_plan(plan_entrenamiento.getNombre_plan());
        existente.setPrecio(plan_entrenamiento.getPrecio());
        existente.setDescripcion(plan_entrenamiento.getDescripcion());
        
        if (plan_entrenamiento.getActivo() != null) {
            existente.setActivo(plan_entrenamiento.getActivo());
        }
        return repository.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        plan_entrenamiento existente = buscarPorId(id);
        repository.delete(existente);
    }
}
