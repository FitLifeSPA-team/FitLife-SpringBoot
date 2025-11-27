package com.example.fitlifeSpring.service.impl;

import com.example.fitlifeSpring.model.usuario;
import com.example.fitlifeSpring.repository.usuarioRepository;
import com.example.fitlifeSpring.service.usuarioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class usuarioServiceImpl implements usuarioService {
    
    private final usuarioRepository repository;
    public usuarioServiceImpl(usuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<usuario> listar() {
        return repository.findAll();
    }

    @Override
    public usuario buscarPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Entrenador no encontrado con id: " + id));
    }

    @Override
    public List<usuario> buscarPornombre(String q) {
        return repository.findByNombreContainingIgnoreCase(q);
    }

    @Override
    public usuario crear(usuario usuario) {
        if (usuario.getActivo() == null) {
            usuario.setActivo(true);
        }
        return repository.save(usuario);
    }

    @Override
    public usuario actualizar(Long id, usuario usuario) {
        usuario existente = buscarPorId(id);
        existente.setNombre(usuario.getNombre());
        existente.setApellido(usuario.getApellido());
        existente.setEmail(usuario.getEmail());
        existente.setUsername(usuario.getUsername());
        existente.setPassword(usuario.getPassword());
        existente.setTelefono(usuario.getTelefono());
        existente.setDireccion(usuario.getDireccion());
        
        if (usuario.getActivo() != null) {
            existente.setActivo(usuario.getActivo());
        }
        return repository.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        usuario existente = buscarPorId(id);
        repository.delete(existente);
    }
}
