package com.example.fitlifeSpring.service.impl;

import com.example.fitlifeSpring.model.progreso;
import com.example.fitlifeSpring.repository.progresoRepository;
import com.example.fitlifeSpring.service.progresoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class progresoServiceImpl implements progresoService {
    
    private final progresoRepository repository;
    public progresoServiceImpl(progresoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<progreso> listar() {
        return repository.findAll();
    }

    @Override
    public progreso buscarPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Entrenador no encontrado con id: " + id));
    }

    @Override
    public List<progreso> buscarPorProgreso(String q) {
        return repository.findByProgresoContainingIgnoreCase(q);
    }

    @Override
    public progreso crear(progreso progreso) {
        if (progreso.getActivo() == null) {
            progreso.setActivo(true);
        }
        return repository.save(progreso);
    }

    @Override
    public progreso actualizar(Long id, progreso progreso) {
        progreso existente = buscarPorId(id);
        existente.setProgreso(progreso.getProgreso());
        existente.setDescripcion_progreso(progreso.getDescripcion_progreso());
        existente.setProgreso(progreso.getProgreso());
        
        if (progreso.getActivo() != null) {
            existente.setActivo(progreso.getActivo());
        }
        return repository.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        progreso existente = buscarPorId(id);
        repository.delete(existente);
    }
}