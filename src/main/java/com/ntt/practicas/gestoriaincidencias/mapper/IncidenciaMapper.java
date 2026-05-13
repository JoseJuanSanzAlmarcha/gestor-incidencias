package com.ntt.practicas.gestoriaincidencias.mapper;

import com.ntt.practicas.gestoriaincidencias.dto.IncidenciaDTO;
import com.ntt.practicas.gestoriaincidencias.model.Incidencia;
import org.springframework.stereotype.Component;

@Component
public class IncidenciaMapper {

    public IncidenciaDTO toDTO(Incidencia incidencia) {
        return new IncidenciaDTO(
                incidencia.getId(),
                incidencia.getTitulo(),
                incidencia.getDescripcion(),
                incidencia.getEstado(),
                incidencia.getPrioridad()
        );
    }

    public Incidencia toEntity(IncidenciaDTO dto) {
        Incidencia incidencia = new Incidencia();
        incidencia.setTitulo(dto.titulo());
        incidencia.setDescripcion(dto.descripcion());
        incidencia.setEstado(dto.estado());
        incidencia.setPrioridad(dto.prioridad());
        return incidencia;
    }
}