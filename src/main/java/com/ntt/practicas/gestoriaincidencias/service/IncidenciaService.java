package com.ntt.practicas.gestoriaincidencias.service;

import com.ntt.practicas.gestoriaincidencias.model.EstadoIncidencia;
import com.ntt.practicas.gestoriaincidencias.model.Incidencia;
import com.ntt.practicas.gestoriaincidencias.model.PrioridadIncidencia;
import java.util.List;

public interface IncidenciaService {

    Incidencia crear(Incidencia incidencia);

    List<Incidencia> listar(EstadoIncidencia estado, PrioridadIncidencia prioridad);

    Incidencia obtenerPorId(Long id);

    Incidencia actualizar(Long id, Incidencia incidencia);

    void eliminar(Long id);
}