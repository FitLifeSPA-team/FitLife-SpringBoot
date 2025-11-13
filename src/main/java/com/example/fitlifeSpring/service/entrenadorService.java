package com.example.fitlifeSpring.service;

import com.example.fitlifeSpring.model.entrenador;
import java.util.List;

public interface entrenadorService {
    List<entrenador> listar();
    entrenador buscarPorId(Long id);
    List<entrenador> buscarPorNombre(String q);
    entrenador crear(entrenador entrenador);
    entrenador actualizar(Long id, entrenador entrenador);
    void eliminar(Long id);
}
