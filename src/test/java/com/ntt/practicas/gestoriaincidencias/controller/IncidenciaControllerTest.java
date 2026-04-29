package com.ntt.practicas.gestoriaincidencias.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ntt.practicas.gestoriaincidencias.dto.IncidenciaDTO;
import com.ntt.practicas.gestoriaincidencias.model.EstadoIncidencia;
import com.ntt.practicas.gestoriaincidencias.model.PrioridadIncidencia;
import com.ntt.practicas.gestoriaincidencias.service.IncidenciaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

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
    private IncidenciaService incidenciaService;

    @Test
    void deberiaCrearIncidencia() throws Exception {
        IncidenciaDTO dto = new IncidenciaDTO();
        dto.setTitulo("Test");
        dto.setEstado(EstadoIncidencia.ABIERTO);
        dto.setPrioridad(PrioridadIncidencia.ALTA);

        when(incidenciaService.crear(any())).thenReturn(dto);

        mockMvc.perform(post("/incidencias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.titulo").value("Test"));
    }

    @Test
    void deberiaListarIncidencias() throws Exception {
        IncidenciaDTO dto = new IncidenciaDTO();
        dto.setTitulo("Lista");

        when(incidenciaService.listar(null, null)).thenReturn(List.of(dto));

        mockMvc.perform(get("/incidencias"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("Lista"));
    }

    @Test
    void deberiaObtenerIncidenciaPorId() throws Exception {
        IncidenciaDTO dto = new IncidenciaDTO();
        dto.setTitulo("Por id");

        when(incidenciaService.obtenerPorId(1L)).thenReturn(dto);

        mockMvc.perform(get("/incidencias/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Por id"));
    }

    @Test
    void deberiaEliminarIncidencia() throws Exception {
        mockMvc.perform(delete("/incidencias/1"))
                .andExpect(status().isNoContent());
    }
}