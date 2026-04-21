package com.ntt.practicas.gestoriaincidencias.controller;

import com.ntt.practicas.gestoriaincidencias.model.EstadoIncidencia;
import com.ntt.practicas.gestoriaincidencias.model.Incidencia;
import com.ntt.practicas.gestoriaincidencias.model.PrioridadIncidencia;
import com.ntt.practicas.gestoriaincidencias.service.IncidenciaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidencias")
public class IncidenciaController {

    private final IncidenciaService incidenciaService;

    public IncidenciaController(IncidenciaService incidenciaService) {
        this.incidenciaService = incidenciaService;
    }

    @PostMapping
    public ResponseEntity<Incidencia> crear(@RequestBody Incidencia incidencia) {
        return ResponseEntity.status(HttpStatus.CREATED).body(incidenciaService.crear(incidencia));
    }

    @GetMapping
    public ResponseEntity<List<Incidencia>> listar(
            @RequestParam(required = false) EstadoIncidencia estado,
            @RequestParam(required = false) PrioridadIncidencia prioridad) {
        return ResponseEntity.ok(incidenciaService.listar(estado, prioridad));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Incidencia> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(incidenciaService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Incidencia> actualizar(@PathVariable Long id, @RequestBody Incidencia incidencia) {
        return ResponseEntity.ok(incidenciaService.actualizar(id, incidencia));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        incidenciaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}