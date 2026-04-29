package com.ntt.practicas.gestoriaincidencias.service;

import com.ntt.practicas.gestoriaincidencias.dto.IncidenciaDTO;
import com.ntt.practicas.gestoriaincidencias.model.EstadoIncidencia;
import com.ntt.practicas.gestoriaincidencias.model.Incidencia;
import com.ntt.practicas.gestoriaincidencias.model.PrioridadIncidencia;
import com.ntt.practicas.gestoriaincidencias.repository.IncidenciaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IncidenciaServiceImpl implements IncidenciaService {

    private final IncidenciaRepository repository;

    public IncidenciaServiceImpl(IncidenciaRepository repository) {
        this.repository = repository;
    }

    private IncidenciaDTO toDTO(Incidencia incidencia) {
        IncidenciaDTO dto = new IncidenciaDTO();
        dto.setId(incidencia.getId());
        dto.setTitulo(incidencia.getTitulo());
        dto.setDescripcion(incidencia.getDescripcion());
        dto.setEstado(incidencia.getEstado());
        dto.setPrioridad(incidencia.getPrioridad());
        return dto;
    }

    private Incidencia toEntity(IncidenciaDTO dto) {
        Incidencia incidencia = new Incidencia();
        incidencia.setTitulo(dto.getTitulo());
        incidencia.setDescripcion(dto.getDescripcion());
        incidencia.setEstado(dto.getEstado());
        incidencia.setPrioridad(dto.getPrioridad());
        return incidencia;
    }

    @Override
    public IncidenciaDTO crear(IncidenciaDTO dto) {
        return toDTO(repository.save(toEntity(dto)));
    }

    @Override
    public List<IncidenciaDTO> listar(EstadoIncidencia estado, PrioridadIncidencia prioridad) {
        List<Incidencia> incidencias;
        if (estado != null && prioridad != null) {
            incidencias = repository.findByEstadoAndPrioridad(estado, prioridad);
        } else if (estado != null) {
            incidencias = repository.findByEstado(estado);
        } else if (prioridad != null) {
            incidencias = repository.findByPrioridad(prioridad);
        } else {
            incidencias = repository.findAll();
        }
        return incidencias.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public IncidenciaDTO obtenerPorId(Long id) {
        return toDTO(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Incidencia no encontrada con id: " + id)));
    }

    @Override
    public IncidenciaDTO actualizar(Long id, IncidenciaDTO dto) {
        Incidencia existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Incidencia no encontrada con id: " + id));
        existente.setTitulo(dto.getTitulo());
        existente.setDescripcion(dto.getDescripcion());
        existente.setEstado(dto.getEstado());
        existente.setPrioridad(dto.getPrioridad());
        return toDTO(repository.save(existente));
    }

    @Override
    public void eliminar(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Incidencia no encontrada con id: " + id));
        repository.deleteById(id);
    }
}