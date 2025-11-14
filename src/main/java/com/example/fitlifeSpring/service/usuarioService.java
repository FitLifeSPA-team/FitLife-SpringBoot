package com.example.fitlifeSpring.service;

import java.util.List;
import com.example.fitlifeSpring.model.usuario;

public interface usuarioService {
    List<usuario> listar();
    usuario buscarPorId(Long id);
    List<usuario> buscarPornombre(String q);
    usuario crear(usuario usuario);
    usuario actualizar(Long id, usuario usuario);
    void eliminar(Long id);
}
