package com.ntt.practicas.gestoriaincidencias.repository;

import com.ntt.practicas.gestoriaincidencias.model.EstadoIncidencia;
import com.ntt.practicas.gestoriaincidencias.model.Incidencia;
import com.ntt.practicas.gestoriaincidencias.model.PrioridadIncidencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//El objetivo es poder filtrar incidencias según criterios

public interface IncidenciaRepository extends JpaRepository<Incidencia, Long> {

    // Busca todas las incidencias con un estado concreto
    List<Incidencia> findByEstado(EstadoIncidencia estado);

    // Busca todas las incidencias con una prioridad concreta
    List<Incidencia> findByPrioridad(PrioridadIncidencia prioridad);

    // Busca por estado Y prioridad a la vez
    List<Incidencia> findByEstadoAndPrioridad(EstadoIncidencia estado, PrioridadIncidencia prioridad);
}

//Importante: Spring lee el nombre del método y genera la query SQL automáticamente
