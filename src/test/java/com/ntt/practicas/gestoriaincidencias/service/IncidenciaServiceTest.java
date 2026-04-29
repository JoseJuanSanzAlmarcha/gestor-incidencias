package com.ntt.practicas.gestoriaincidencias.service;

import com.ntt.practicas.gestoriaincidencias.dto.IncidenciaDTO;
import com.ntt.practicas.gestoriaincidencias.model.EstadoIncidencia;
import com.ntt.practicas.gestoriaincidencias.model.Incidencia;
import com.ntt.practicas.gestoriaincidencias.model.PrioridadIncidencia;
import com.ntt.practicas.gestoriaincidencias.repository.IncidenciaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IncidenciaServiceTest {

    @Mock
    private IncidenciaRepository repository;

    @InjectMocks
    private IncidenciaServiceImpl service;

    @Test
    void deberiaCrearIncidencia() {
        IncidenciaDTO dto = new IncidenciaDTO();
        dto.setTitulo("Test");
        dto.setEstado(EstadoIncidencia.ABIERTO);
        dto.setPrioridad(PrioridadIncidencia.ALTA);

        Incidencia incidencia = new Incidencia();
        incidencia.setTitulo("Test");
        incidencia.setEstado(EstadoIncidencia.ABIERTO);
        incidencia.setPrioridad(PrioridadIncidencia.ALTA);

        when(repository.save(any(Incidencia.class))).thenReturn(incidencia);

        IncidenciaDTO resultado = service.crear(dto);

        assertThat(resultado.getTitulo()).isEqualTo("Test");
        verify(repository, times(1)).save(any(Incidencia.class));
    }

    @Test
    void deberiaLanzarExcepcionSiNoExisteId() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.obtenerPorId(99L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("99");
    }

    @Test
    void deberiaListarTodasSiNoHayFiltros() {
        Incidencia incidencia = new Incidencia();
        incidencia.setTitulo("Sin filtro");

        when(repository.findAll()).thenReturn(List.of(incidencia));

        List<IncidenciaDTO> resultado = service.listar(null, null);

        assertThat(resultado).hasSize(1);
        verify(repository, times(1)).findAll();
    }

    @Test
    void deberiaEliminarIncidencia() {
        Incidencia incidencia = new Incidencia();
        incidencia.setTitulo("A eliminar");

        when(repository.findById(1L)).thenReturn(Optional.of(incidencia));

        service.eliminar(1L);

        verify(repository, times(1)).deleteById(1L);
    }
}