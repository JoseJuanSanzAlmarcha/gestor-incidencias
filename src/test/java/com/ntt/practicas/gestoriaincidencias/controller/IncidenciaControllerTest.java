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
import static org.mockito.Mockito.doNothing;
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
        IncidenciaDTO dto = new IncidenciaDTO(null, "Test", null, EstadoIncidencia.ABIERTO, PrioridadIncidencia.ALTA);

        when(incidenciaService.crear(any(IncidenciaDTO.class))).thenReturn(dto);

        mockMvc.perform(post("/incidencias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

    @Test
    void deberiaListarIncidencias() throws Exception {
        IncidenciaDTO dto = new IncidenciaDTO(1L, "Lista", null, EstadoIncidencia.ABIERTO, PrioridadIncidencia.BAJA);

        when(incidenciaService.listar()).thenReturn(List.of(dto));

        mockMvc.perform(get("/incidencias"))
                .andExpect(status().isOk());
    }

    @Test
    void deberiaObtenerIncidenciaPorId() throws Exception {
        IncidenciaDTO dto = new IncidenciaDTO(1L, "Por id", null, EstadoIncidencia.ABIERTO, PrioridadIncidencia.BAJA);

        when(incidenciaService.obtenerPorId(1L)).thenReturn(dto);

        mockMvc.perform(get("/incidencias/1"))
                .andExpect(status().isOk());
    }

    @Test
    void deberiaEliminarIncidencia() throws Exception {
        doNothing().when(incidenciaService).eliminar(1L);

        mockMvc.perform(delete("/incidencias/1"))
                .andExpect(status().isNoContent());
    }
}