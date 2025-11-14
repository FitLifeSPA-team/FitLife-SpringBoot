package com.example.fitlifeSpring.controller;

import com.example.fitlifeSpring.model.usuario;
import com.example.fitlifeSpring.service.usuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(
    origins = "http://localhost:3000",
    allowedHeaders = "*",
    methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.PUT,
        RequestMethod.DELETE,
        RequestMethod.PATCH,
        RequestMethod.OPTIONS
    }
)
@RestController
@RequestMapping("/api/v1/usuario")
public class usuarioController {

    private final usuarioService service;

    public usuarioController(usuarioService service) {
        this.service = service;
    }

    @Operation(summary = "Listar usuarios", description = "Devuelve todos los usuarios")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = usuario.class))))
    })
    @GetMapping
    public List<usuario> listar() {
        return service.listar();
    }

    @Operation(summary = "Obtener usuario por id")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Encontrado"),
        @ApiResponse(responseCode = "404", description = "No encontrado")
    })
    @GetMapping("/{id}")
    public usuario obtener(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @Operation(summary = "Buscar usuarios por nombre")
    @GetMapping("/search")
    public List<usuario> buscar(@RequestParam String q) {
        return service.buscarPornombre(q);
    }

    @Operation(summary = "Crear usuario")
    @ApiResponse(responseCode = "201", description = "Creado")
    @PostMapping
    public ResponseEntity<usuario> crear(@Valid @RequestBody usuario usuario) {
        usuario creado = service.crear(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @Operation(summary = "Actualizar usuario")
    @PutMapping("/{id}")
    public usuario actualizar(@PathVariable Long id, @Valid @RequestBody usuario usuario) {
        return service.actualizar(id, usuario);
    }

    @Operation(summary = "Eliminar usuario")
    @ApiResponse(responseCode = "204", description = "Eliminado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
