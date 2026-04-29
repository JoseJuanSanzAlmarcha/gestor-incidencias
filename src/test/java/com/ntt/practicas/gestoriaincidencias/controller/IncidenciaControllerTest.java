package com.ntt.practicas.gestoriaincidencias.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ntt.practicas.gestoriaincidencias.dto.IncidenciaDTO;
import com.ntt.practicas.gestoriaincidencias.model.EstadoIncidencia;
import com.ntt.practicas.gestoriaincidencias.model.Incidencia;
import com.ntt.practicas.gestoriaincidencias.model.PrioridadIncidencia;
import com.ntt.practicas.gestoriaincidencias.repository.IncidenciaRepository;
import com.ntt.practicas.gestoriaincidencias.service.IncidenciaServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(IncidenciaController.class)
class IncidenciaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private IncidenciaRepository repository;

    @MockitoBean
    private IncidenciaServiceImpl incidenciaService;

    @Test
    void deberiaCrearIncidencia() throws Exception {
        IncidenciaDTO dto = new IncidenciaDTO();
        dto.setTitulo("Test");
        dto.setEstado(EstadoIncidencia.ABIERTO);
        dto.setPrioridad(PrioridadIncidencia.ALTA);

        Incidencia incidencia = new Incidencia();
        incidencia.setTitulo("Test");
        incidencia.setEstado(EstadoIncidencia.ABIERTO);
        incidencia.setPrioridad(PrioridadIncidencia.ALTA);

        when(repository.save(any(Incidencia.class))).thenReturn(incidencia);

        mockMvc.perform(post("/incidencias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

    @Test
    void deberiaListarIncidencias() throws Exception {
        Incidencia incidencia = new Incidencia();
        incidencia.setTitulo("Lista");

        when(repository.findAll()).thenReturn(List.of(incidencia));

        mockMvc.perform(get("/incidencias"))
                .andExpect(status().isOk());
    }

    @Test
    void deberiaObtenerIncidenciaPorId() throws Exception {
        Incidencia incidencia = new Incidencia();
        incidencia.setTitulo("Por id");

        when(repository.findById(1L)).thenReturn(Optional.of(incidencia));

        mockMvc.perform(get("/incidencias/1"))
                .andExpect(status().isOk());
    }

    @Test
    void deberiaEliminarIncidencia() throws Exception {
        Incidencia incidencia = new Incidencia();
        when(repository.findById(1L)).thenReturn(Optional.of(incidencia));

        mockMvc.perform(delete("/incidencias/1"))
                .andExpect(status().isNoContent());
    }
}