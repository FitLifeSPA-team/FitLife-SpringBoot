package com.example.fitlifeSpring.controller;

import com.example.fitlifeSpring.model.plan_nutricional;
import com.example.fitlifeSpring.service.plan_nutricionalService;

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
@RequestMapping("/api/v1/plan_nutricional")
public class plan_nutricionalController {

    private final plan_nutricionalService service;

    public plan_nutricionalController(plan_nutricionalService service) {
        this.service = service;
    }

    @Operation(summary = "Listar plan nutricional", description = "Devuelve todos los planes nutricionales")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = plan_nutricional.class))))
    })
    @GetMapping
    public List<plan_nutricional> listar() {
        return service.listar();
    }

    @Operation(summary = "Obtener por id")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Encontrado"),
        @ApiResponse(responseCode = "404", description = "No encontrado")
    })
    @GetMapping("/{id}")
    public plan_nutricional obtener(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @Operation(summary = "Obtener por nombre de plan nutricional")
    @GetMapping("/search")
    public List<plan_nutricional> buscar(@RequestParam String q) {
        return service.buscarPornombre_nutri(q);
    }

    @Operation(summary = "Crear plan nutricional")
    @ApiResponse(responseCode = "201", description = "Creado")
    @PostMapping
    public ResponseEntity<plan_nutricional> crear(@Valid @RequestBody plan_nutricional plan_nutricional) {
        plan_nutricional creado = service.crear(plan_nutricional);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @Operation(summary = "Actualizar plan nutricional")
    @PutMapping("/{id}")
    public plan_nutricional actualizar(@PathVariable Long id, @Valid @RequestBody plan_nutricional plan_nutricional) {
        return service.actualizar(id, plan_nutricional);
    }

    @Operation(summary = "Eliminar plan nutricional")
    @ApiResponse(responseCode = "204", description = "Eliminado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
