package com.example.fitlifeSpring.service;

import java.util.List;
import com.example.fitlifeSpring.model.progreso;

public interface progresoService {
    List<progreso> listar();
    progreso buscarPorId(Long id);
    progreso crear(progreso progreso);
    progreso actualizar(Long id, progreso progreso);
    void eliminar(Long id);
}
