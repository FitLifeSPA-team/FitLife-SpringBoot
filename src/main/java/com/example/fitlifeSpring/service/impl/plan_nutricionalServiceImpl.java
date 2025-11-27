package com.example.fitlifeSpring.service.impl;


import com.example.fitlifeSpring.model.plan_nutricional;
import com.example.fitlifeSpring.repository.plan_nutricionalRepository;
import com.example.fitlifeSpring.service.plan_nutricionalService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional

public class plan_nutricionalServiceImpl implements plan_nutricionalService {
    
    private final plan_nutricionalRepository repository;
    public plan_nutricionalServiceImpl(plan_nutricionalRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<plan_nutricional> listar() {
        return repository.findAll();
    }

    @Override
    public plan_nutricional buscarPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Entrenador no encontrado con id: " + id));
    }

    @Override
    public List<plan_nutricional> buscarPornombre_nutri(String q) {
        return repository.findByNombre_nutriContainingIgnoreCase(q);
    }

    @Override
    public plan_nutricional crear(plan_nutricional plan_nutricional) {
        if (plan_nutricional.getActivo() == null) {
            plan_nutricional.setActivo(true);
        }
        return repository.save(plan_nutricional);
    }

    @Override
    public plan_nutricional actualizar(Long id, plan_nutricional plan_nutricional) {
        plan_nutricional existente = buscarPorId(id);
        existente.setNombre_nutri(plan_nutricional.getNombre_nutri());
        existente.setDescripcion_nutri(plan_nutricional.getDescripcion_nutri());
        existente.setPrecio(plan_nutricional.getPrecio());
        
        if (plan_nutricional.getActivo() != null) {
            existente.setActivo(plan_nutricional.getActivo());
        }
        return repository.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        plan_nutricional existente = buscarPorId(id);
        repository.delete(existente);
    }
}
