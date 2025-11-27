package com.example.fitlifeSpring.service.impl;

import com.example.fitlifeSpring.model.entrenador;
import com.example.fitlifeSpring.repository.entrenadorRepository;
import com.example.fitlifeSpring.service.entrenadorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class entrenadorServiceImpl implements entrenadorService {
    
    private final entrenadorRepository repository;
    public entrenadorServiceImpl(entrenadorRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<entrenador> listar() {
        return repository.findAll();
    }

    @Override
    public entrenador buscarPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Entrenador no encontrado con id: " + id));
    }

    @Override
    public List<entrenador> buscarPorNombre(String q) {
        return repository.findByNombreContainingIgnoreCase(q);
    }

    @Override
    public entrenador crear(entrenador entrenador) {
        if (entrenador.getActivo() == null) {
            entrenador.setActivo(true);
        }
        return repository.save(entrenador);
    }

    @Override
    public entrenador actualizar(Long id, entrenador entrenador) {
        entrenador existente = buscarPorId(id);
        existente.setNombre(entrenador.getNombre());
        existente.setApellido(entrenador.getApellido());
        existente.setEmail(entrenador.getEmail());
        existente.setTelefono(entrenador.getTelefono());
        if (entrenador.getActivo() != null) {
            existente.setActivo(entrenador.getActivo());
        }
        return repository.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        entrenador existente = buscarPorId(id);
        repository.delete(existente);
    }
}