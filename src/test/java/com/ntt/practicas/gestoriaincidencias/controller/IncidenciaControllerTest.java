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

// Solo levanta la capa web (Controller), sin base de datos ni service real
// Es más rápido que arrancar toda la aplicación
@WebMvcTest(IncidenciaController.class)
class IncidenciaControllerTest {

    // MockMvc simula peticiones HTTP como si fuera Postman, pero dentro del test
    @Autowired
    private MockMvc mockMvc;

    // ObjectMapper convierte objetos Java a JSON y viceversa
    @Autowired
    private ObjectMapper objectMapper;

    // Crea un service falso (mock) para no depender de la base de datos
    // El test controla qué devuelve el service en cada momento
    @MockitoBean
    private IncidenciaService incidenciaService;

    @Test
    void deberiaCrearIncidencia() throws Exception {
        // Creamos una incidencia de prueba
        Incidencia incidencia = new Incidencia();
        incidencia.setTitulo("Test");
        incidencia.setEstado(EstadoIncidencia.ABIERTO);
        incidencia.setPrioridad(PrioridadIncidencia.ALTA);

        // Le decimos al service falso: cuando alguien llame a crear(), devuelve esta incidencia
        when(incidenciaService.crear(any())).thenReturn(incidencia);

        // Simulamos un POST a /incidencias con la incidencia en el body
        // Comprobamos que devuelve 201 (CREATED) y que el título es "Test"
        mockMvc.perform(post("/incidencias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(incidencia)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.titulo").value("Test"));
    }

    @Test
    void deberiaListarIncidencias() throws Exception {
        // Creamos una incidencia de prueba
        Incidencia incidencia = new Incidencia();
        incidencia.setTitulo("Lista");

        // Cuando se llame a listar() sin filtros, devuelve una lista con esa incidencia
        when(incidenciaService.listar(null, null)).thenReturn(List.of(incidencia));

        // Simulamos un GET a /incidencias
        // Comprobamos que devuelve 200 (OK) y que el primer elemento tiene título "Lista"
        mockMvc.perform(get("/incidencias"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("Lista"));
    }

    @Test
    void deberiaObtenerIncidenciaPorId() throws Exception {
        // Creamos una incidencia de prueba
        Incidencia incidencia = new Incidencia();
        incidencia.setTitulo("Por id");

        // Cuando se llame a obtenerPorId(1), devuelve esa incidencia
        when(incidenciaService.obtenerPorId(1L)).thenReturn(incidencia);

        // Simulamos un GET a /incidencias/1
        // Comprobamos que devuelve 200 (OK) y que el título es "Por id"
        mockMvc.perform(get("/incidencias/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Por id"));
    }

    @Test
    void deberiaEliminarIncidencia() throws Exception {
        // Simulamos un DELETE a /incidencias/1
        // Comprobamos que devuelve 204 (NO CONTENT) — eliminado correctamente
        mockMvc.perform(delete("/incidencias/1"))
                .andExpect(status().isNoContent());
    }
}