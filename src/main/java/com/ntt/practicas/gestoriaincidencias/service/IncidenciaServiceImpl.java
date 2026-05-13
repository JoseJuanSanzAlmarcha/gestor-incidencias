package com.ntt.practicas.gestoriaincidencias.service;

import com.ntt.practicas.gestoriaincidencias.dto.IncidenciaDTO;
import com.ntt.practicas.gestoriaincidencias.mapper.IncidenciaMapper;
import com.ntt.practicas.gestoriaincidencias.model.Incidencia;
import com.ntt.practicas.gestoriaincidencias.repository.IncidenciaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IncidenciaServiceImpl implements IncidenciaService {

    private final IncidenciaRepository repository;
    private final IncidenciaMapper mapper;

    public IncidenciaServiceImpl(IncidenciaRepository repository, IncidenciaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public IncidenciaDTO crear(IncidenciaDTO dto) {
        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public List<IncidenciaDTO> listar() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public IncidenciaDTO obtenerPorId(Long id) {
        return mapper.toDTO(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Incidencia no encontrada con id: " + id)));
    }

    @Override
    public IncidenciaDTO actualizar(Long id, IncidenciaDTO dto) {
        Incidencia existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Incidencia no encontrada con id: " + id));
        existente.setTitulo(dto.titulo());
        existente.setDescripcion(dto.descripcion());
        existente.setEstado(dto.estado());
        existente.setPrioridad(dto.prioridad());
        return mapper.toDTO(repository.save(existente));
    }

    @Override
    public void eliminar(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Incidencia no encontrada con id: " + id));
        repository.deleteById(id);
    }
}