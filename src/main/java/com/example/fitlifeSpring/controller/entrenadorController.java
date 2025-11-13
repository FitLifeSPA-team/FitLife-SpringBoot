package com.example.fitlifeSpring.controller;

import com.example.fitlifeSpring.model.entrenador;
import com.example.fitlifeSpring.service.entrenadorService;

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
@RequestMapping("/api/v1/entrenador")
public class entrenadorController {
    private final entrenadorService service;

    public entrenadorController(entrenadorService service) {
        this.service = service;
    }

    @Operation(summary = "Listar entrenadores", description = "Devuelve todos los entrenadores")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK",
        content = @Content(array = @ArraySchema(schema = @Schema(implementation = entrenador.class))))
    })
    @GetMapping
    public List<entrenador> listar() {
        return service.listar();
    }

    @Operation(summary = "Obtener por id")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Encontrado"),
        @ApiResponse(responseCode = "404", description = "No encontrado")
    })
    @GetMapping("/{id}")
    public entrenador obtener(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @Operation(summary = "Obtener por nombre")
    @GetMapping("/search")
    public List<entrenador> buscar(@RequestParam String q) {
        return service.buscarPorNombre(q);
    }

    @Operation(summary = "Crear entrenador")
    @ApiResponse(responseCode = "201", description = "Creado")
    @PostMapping
    public ResponseEntity<entrenador> crear(@Valid @RequestBody entrenador entrenador) {
        entrenador creado = service.crear(entrenador);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @Operation(summary = "Actualizar entrenador")
    @PutMapping("/{id}")
    public entrenador actualizar(@PathVariable Long id, @Valid @RequestBody entrenador entrenador) {
        return service.actualizar(id, entrenador);
    }

    @Operation(summary = "Eliminar entrenador")
    @ApiResponse(responseCode = "204", description = "Eliminado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
