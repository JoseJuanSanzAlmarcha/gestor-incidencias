package com.ntt.practicas.gestoriaincidencias.controller;

import com.ntt.practicas.gestoriaincidencias.dto.IncidenciaDTO;
import com.ntt.practicas.gestoriaincidencias.model.EstadoIncidencia;
import com.ntt.practicas.gestoriaincidencias.model.PrioridadIncidencia;
import com.ntt.practicas.gestoriaincidencias.service.IncidenciaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/incidencias")
public class IncidenciaController {

    private final IncidenciaService incidenciaService;

    public IncidenciaController(IncidenciaService incidenciaService) {
        this.incidenciaService = incidenciaService;
    }

    @PostMapping
    public ResponseEntity<IncidenciaDTO> crear(@RequestBody IncidenciaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(incidenciaService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<IncidenciaDTO>> listar(
            @RequestParam(required = false) EstadoIncidencia estado,
            @RequestParam(required = false) PrioridadIncidencia prioridad) {
        return ResponseEntity.ok(incidenciaService.listar(estado, prioridad));
    }

    @GetMapping("/{id}")
    public ResponseEntity<IncidenciaDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(incidenciaService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IncidenciaDTO> actualizar(@PathVariable Long id, @RequestBody IncidenciaDTO dto) {
        return ResponseEntity.ok(incidenciaService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        incidenciaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}