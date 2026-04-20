package com.ntt.practicas.gestoriaincidencias.service;

import com.ntt.practicas.gestoriaincidencias.model.Incidencia;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface IncidenciaService {

    //Crear una nueva incidencia
    Incidencia crear(Incidencia incidencia);

    //Obtener todas las incidencias, devuelve una lista con todas las incidencias
    List<Incidencia> obtenerTodas();

    //Recibe un id y devuelve la incidencia que tenga ese id
    Incidencia obtenerPorId (Long id);

    //Actualizar una incidencia ya existente
    Incidencia actualizar(Long id, Incidencia incidencia);

    //Elimina incidencia por su idd, devuelve un VOID, solo elimina incidencia con ID
    void eliminar(Long id);

    //Filtrar por estado o/ y prioridad
    List<Incidencia> filtrar(String estado, String prioridad);
}
