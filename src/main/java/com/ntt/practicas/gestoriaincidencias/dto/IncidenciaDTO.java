package com.ntt.practicas.gestoriaincidencias.dto;

import com.ntt.practicas.gestoriaincidencias.model.EstadoIncidencia;
import com.ntt.practicas.gestoriaincidencias.model.PrioridadIncidencia;

public record IncidenciaDTO(
        Long id,
        String titulo,
        String descripcion,
        EstadoIncidencia estado,
        PrioridadIncidencia prioridad
) {}