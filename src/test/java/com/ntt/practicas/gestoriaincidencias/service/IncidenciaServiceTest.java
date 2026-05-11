package com.ntt.practicas.gestoriaincidencias.service;

import com.ntt.practicas.gestoriaincidencias.dto.IncidenciaDTO;
import com.ntt.practicas.gestoriaincidencias.mapper.IncidenciaMapper;
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

    @Mock
    private IncidenciaMapper mapper;

    @InjectMocks
    private IncidenciaServiceImpl service;

    @Test
    void deberiaCrearIncidencia() {
        IncidenciaDTO dto = new IncidenciaDTO(null, "Test", null, EstadoIncidencia.ABIERTO, PrioridadIncidencia.ALTA);
        Incidencia incidencia = new Incidencia();
        incidencia.setTitulo("Test");

        when(mapper.toEntity(dto)).thenReturn(incidencia);
        when(repository.save(incidencia)).thenReturn(incidencia);
        when(mapper.toDTO(incidencia)).thenReturn(dto);

        IncidenciaDTO resultado = service.crear(dto);

        assertThat(resultado.titulo()).isEqualTo("Test");
        verify(repository, times(1)).save(incidencia);
    }

    @Test
    void deberiaLanzarExcepcionSiNoExisteId() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.obtenerPorId(99L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("99");
    }

    @Test
    void deberiaListarTodas() {
        Incidencia incidencia = new Incidencia();
        incidencia.setTitulo("Sin filtro");
        IncidenciaDTO dto = new IncidenciaDTO(null, "Sin filtro", null, null, null);

        when(repository.findAll()).thenReturn(List.of(incidencia));
        when(mapper.toDTO(incidencia)).thenReturn(dto);

        List<IncidenciaDTO> resultado = service.listar();

        assertThat(resultado).hasSize(1);
        verify(repository, times(1)).findAll();
    }

    @Test
    void deberiaEliminarIncidencia() {
        Incidencia incidencia = new Incidencia();
        when(repository.findById(1L)).thenReturn(Optional.of(incidencia));

        service.eliminar(1L);

        verify(repository, times(1)).deleteById(1L);
    }
}