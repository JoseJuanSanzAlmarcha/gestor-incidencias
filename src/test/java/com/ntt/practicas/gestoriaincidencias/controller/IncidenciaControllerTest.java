package com.ntt.practicas.gestoriaincidencias.controller;

import com.ntt.practicas.gestoriaincidencias.dto.IncidenciaDTO;
import com.ntt.practicas.gestoriaincidencias.mapper.IncidenciaMapper;
import com.ntt.practicas.gestoriaincidencias.model.EstadoIncidencia;
import com.ntt.practicas.gestoriaincidencias.model.Incidencia;
import com.ntt.practicas.gestoriaincidencias.model.PrioridadIncidencia;
import com.ntt.practicas.gestoriaincidencias.repository.IncidenciaRepository;
import com.ntt.practicas.gestoriaincidencias.service.IncidenciaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IncidenciaControllerTest {

    @Mock
    private IncidenciaRepository incidenciaRepository;

    @Mock
    private IncidenciaMapper incidenciaMapper;

    private IncidenciaServiceImpl incidenciaService;

    private IncidenciaController incidenciaController;

    @BeforeEach
    void setUp() {
        incidenciaService = new IncidenciaServiceImpl(incidenciaRepository, incidenciaMapper);
        incidenciaController = new IncidenciaController(incidenciaService);
    }

    @Test
    void deberiaCrearIncidencia() {
        IncidenciaDTO dto = new IncidenciaDTO(null, "Test", null, EstadoIncidencia.ABIERTO, PrioridadIncidencia.ALTA);
        Incidencia incidencia = new Incidencia();
        incidencia.setTitulo("Test");

        when(incidenciaMapper.toEntity(dto)).thenReturn(incidencia);
        when(incidenciaRepository.save(any(Incidencia.class))).thenReturn(incidencia);
        when(incidenciaMapper.toDTO(incidencia)).thenReturn(dto);

        var response = incidenciaController.crear(dto);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(dto);
    }

    @Test
    void deberiaListarIncidencias() {
        Incidencia incidencia = new Incidencia();
        IncidenciaDTO dto = new IncidenciaDTO(1L, "Lista", null, EstadoIncidencia.ABIERTO, PrioridadIncidencia.BAJA);

        when(incidenciaRepository.findAll()).thenReturn(List.of(incidencia));
        when(incidenciaMapper.toDTO(incidencia)).thenReturn(dto);

        var response = incidenciaController.listar();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(1);
    }

    @Test
    void deberiaObtenerIncidenciaPorId() {
        Incidencia incidencia = new Incidencia();
        IncidenciaDTO dto = new IncidenciaDTO(1L, "Por id", null, EstadoIncidencia.ABIERTO, PrioridadIncidencia.BAJA);

        when(incidenciaRepository.findById(1L)).thenReturn(Optional.of(incidencia));
        when(incidenciaMapper.toDTO(incidencia)).thenReturn(dto);

        var response = incidenciaController.obtenerPorId(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(dto);
    }

    @Test
    void deberiaEliminarIncidencia() {
        Incidencia incidencia = new Incidencia();
        when(incidenciaRepository.findById(1L)).thenReturn(Optional.of(incidencia));

        var response = incidenciaController.eliminar(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}