package com.ntt.practicas.gestoriaincidencias.repository;

import com.ntt.practicas.gestoriaincidencias.model.EstadoIncidencia;
import com.ntt.practicas.gestoriaincidencias.model.Incidencia;
import com.ntt.practicas.gestoriaincidencias.model.PrioridadIncidencia;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class IncidenciaRepositoryTest {

    @Autowired
    private IncidenciaRepository repository;

    @Test
    void deberiaGuardarYRecuperarIncidencia() {
        Incidencia incidencia = new Incidencia();
        incidencia.setTitulo("Test");
        incidencia.setDescripcion("Descripción de prueba");
        incidencia.setEstado(EstadoIncidencia.ABIERTO);
        incidencia.setPrioridad(PrioridadIncidencia.ALTA);

        repository.save(incidencia);

        List<Incidencia> resultado = repository.findAll();
        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getTitulo()).isEqualTo("Test");
    }

    @Test
    void deberiaFiltrarPorEstado() {
        Incidencia incidencia = new Incidencia();
        incidencia.setTitulo("Abierta");
        incidencia.setEstado(EstadoIncidencia.ABIERTO);
        incidencia.setPrioridad(PrioridadIncidencia.MEDIA);
        repository.save(incidencia);

        List<Incidencia> resultado = repository.findByEstado(EstadoIncidencia.ABIERTO);
        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getEstado()).isEqualTo(EstadoIncidencia.ABIERTO);
    }

    @Test
    void deberiaFiltrarPorPrioridad() {
        Incidencia incidencia = new Incidencia();
        incidencia.setTitulo("Alta prioridad");
        incidencia.setEstado(EstadoIncidencia.ABIERTO);
        incidencia.setPrioridad(PrioridadIncidencia.ALTA);
        repository.save(incidencia);

        List<Incidencia> resultado = repository.findByPrioridad(PrioridadIncidencia.ALTA);
        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getPrioridad()).isEqualTo(PrioridadIncidencia.ALTA);
    }
}