package com.example.fitlifeSpring.controller;

import com.example.fitlifeSpring.model.plan_entrenamiento;
import com.example.fitlifeSpring.service.plan_entrenamientoService;

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
@RequestMapping("/api/v1/plan_entrenamiento")
public class plan_entenamientoController {
    private final plan_entrenamientoService service;

    public plan_entenamientoController(plan_entrenamientoService service) {
        this.service = service;
    }

    @Operation(summary = "Listar plan entrenamiento", description = "Devuelve todos los planes de entrenamiento")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK",
        content = @Content(array = @ArraySchema(schema = @Schema(implementation = plan_entrenamiento.class))))
    })
    @GetMapping
    public List<plan_entrenamiento> listar() {
        return service.listar();
    }

    @Operation(summary = "Obtener por id")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Encontrado"),
        @ApiResponse(responseCode = "404", description = "No encontrado")
    })
    @GetMapping("/{id}")
    public plan_entrenamiento obtener(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @Operation(summary = "Obtener por nombre de plan")
    @GetMapping("/search")
    public List<plan_entrenamiento> buscar(@RequestParam String q) {
        return service.buscarPorNombre_Plan(q);
    }

    @Operation(summary = "Crear plan de entrenamiento")
    @ApiResponse(responseCode = "201", description = "Creado")
    @PostMapping
    public ResponseEntity<plan_entrenamiento> crear(@Valid @RequestBody plan_entrenamiento plan_entrenamiento) {
        plan_entrenamiento creado = service.crear(plan_entrenamiento);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @Operation(summary = "Actualizar plan de entrenamiento")
    @PutMapping("/{id}")
    public plan_entrenamiento actualizar(@PathVariable Long id, @Valid @RequestBody plan_entrenamiento plan_entrenamiento) {
        return service.actualizar(id, plan_entrenamiento);
    }

    @Operation(summary = "Eliminar plan de entrenamiento")
    @ApiResponse(responseCode = "204", description = "Eliminado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
