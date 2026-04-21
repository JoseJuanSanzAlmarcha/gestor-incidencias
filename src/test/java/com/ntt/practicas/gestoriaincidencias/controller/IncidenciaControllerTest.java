package com.ntt.practicas.gestoriaincidencias.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ntt.practicas.gestoriaincidencias.model.EstadoIncidencia;
import com.ntt.practicas.gestoriaincidencias.model.Incidencia;
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

// @WebMvcTest solo levanta la capa web, sin base de datos
// MockMvc simula peticiones HTTP sin necesidad de Postman
@WebMvcTest(IncidenciaController.class)
class IncidenciaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // Simula el service para no depender de la base de datos
    @MockitoBean
    private IncidenciaService incidenciaService;

    @Test
    void deberiaCrearIncidencia() throws Exception {
        Incidencia incidencia = new Incidencia();
        incidencia.setTitulo("Test");
        incidencia.setEstado(EstadoIncidencia.ABIERTO);
        incidencia.setPrioridad(PrioridadIncidencia.ALTA);

        when(incidenciaService.crear(any())).thenReturn(incidencia);

        mockMvc.perform(post("/api/incidencias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(incidencia)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.titulo").value("Test"));
    }

    @Test
    void deberiaListarIncidencias() throws Exception {
        Incidencia incidencia = new Incidencia();
        incidencia.setTitulo("Lista");

        when(incidenciaService.listar(null, null)).thenReturn(List.of(incidencia));

        mockMvc.perform(get("/api/incidencias"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("Lista"));
    }

    @Test
    void deberiaObtenerIncidenciaPorId() throws Exception {
        Incidencia incidencia = new Incidencia();
        incidencia.setTitulo("Por id");

        when(incidenciaService.obtenerPorId(1L)).thenReturn(incidencia);

        mockMvc.perform(get("/api/incidencias/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Por id"));
    }

    @Test
    void deberiaEliminarIncidencia() throws Exception {
        mockMvc.perform(delete("/api/incidencias/1"))
                .andExpect(status().isNoContent());
    }
}