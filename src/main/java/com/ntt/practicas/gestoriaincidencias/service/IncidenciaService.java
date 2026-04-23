package com.ntt.practicas.gestoriaincidencias.service;

import com.ntt.practicas.gestoriaincidencias.model.EstadoIncidencia;
import com.ntt.practicas.gestoriaincidencias.model.Incidencia;
import com.ntt.practicas.gestoriaincidencias.model.PrioridadIncidencia;

import java.util.List;

/* Esta es una interfaz: define QUÉ puede hacer el servicio, pero no el CÓMO.
 El CÓMO lo implementa IncidenciaServiceImpl.
 Esto es buena práctica porque separa el contrato de la implementación*/
public interface IncidenciaService {

    /*  Recibe una incidencia nueva y la guarda en la base de datos.
     Devuelve la incidencia ya guardada (con su id asignado).*/
    Incidencia crear(Incidencia incidencia);

    /*  Devuelve una lista de incidencias.
    // Si se pasa estado y/o prioridad, filtra por esos valores.
    // Si se pasan null, devuelve todas*/
    List<Incidencia> listar(EstadoIncidencia estado, PrioridadIncidencia prioridad);

    /*  Busca una incidencia por su id.
    Si no existe, lanzará una excepción (lo gestionaremos en el impl)*/
    Incidencia obtenerPorId(Long id);

    /*  Actualiza los datos de una incidencia existente.
    Recibe el id de la incidencia a modificar y los nuevos datos.*/
    Incidencia actualizar(Long id, Incidencia incidencia);

    /*  Elimina la incidencia con ese id.
    Devuelve void porque no necesita devolver nada.*/
    void eliminar(Long id);
}