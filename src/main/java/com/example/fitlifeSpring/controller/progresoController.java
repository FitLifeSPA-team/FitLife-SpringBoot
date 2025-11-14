package com.example.fitlifeSpring.controller;

import com.example.fitlifeSpring.model.progreso;
import com.example.fitlifeSpring.service.progresoService;

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
@RequestMapping("/api/v1/progreso")
public class progresoController {

    private final progresoService service;

    public progresoController(progresoService service) {
        this.service = service;
    }

    @Operation(summary = "Listar progreso", description = "Devuelve todos los registros de progreso")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = progreso.class))))
    })
    @GetMapping
    public List<progreso> listar() {
        return service.listar();
    }

    @Operation(summary = "Obtener progreso por id")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Encontrado"),
        @ApiResponse(responseCode = "404", description = "No encontrado")
    })
    @GetMapping("/{id}")
    public progreso obtener(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @Operation(summary = "Crear progreso")
    @ApiResponse(responseCode = "201", description = "Creado")
    @PostMapping
    public ResponseEntity<progreso> crear(@Valid @RequestBody progreso progreso) {
        progreso creado = service.crear(progreso);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @Operation(summary = "Actualizar progreso")
    @PutMapping("/{id}")
    public progreso actualizar(@PathVariable Long id, @Valid @RequestBody progreso progreso) {
        return service.actualizar(id, progreso);
    }

    @Operation(summary = "Eliminar progreso")
    @ApiResponse(responseCode = "204", description = "Eliminado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
