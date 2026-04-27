package com.ntt.practicas.gestoriaincidencias.repository;

import com.ntt.practicas.gestoriaincidencias.model.EstadoIncidencia;
import com.ntt.practicas.gestoriaincidencias.model.Incidencia;
import com.ntt.practicas.gestoriaincidencias.model.PrioridadIncidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IncidenciaRepository extends JpaRepository<Incidencia, Long> {

    List<Incidencia> findByEstado(EstadoIncidencia estado);

    List<Incidencia> findByPrioridad(PrioridadIncidencia prioridad);

    List<Incidencia> findByEstadoAndPrioridad(EstadoIncidencia estado, PrioridadIncidencia prioridad);
}