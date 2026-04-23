package com.ntt.practicas.gestoriaincidencias.service;

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

// No arranca Spring, solo usa Mockito para simular el repository
@ExtendWith(MockitoExtension.class)
class IncidenciaServiceTest {

    // Crea un repository falso, no toca la base de datos real
    @Mock
    private IncidenciaRepository repository;

    // Inyecta el mock en el service para probarlo aislado
    @InjectMocks
    private IncidenciaServiceImpl service;

    @Test
    void deberiaCrearIncidencia() {
        Incidencia incidencia = new Incidencia();
        incidencia.setTitulo("Test");
        incidencia.setEstado(EstadoIncidencia.ABIERTO);
        incidencia.setPrioridad(PrioridadIncidencia.ALTA);

        // Le decimos al mock qué devolver cuando se llame a save()
        when(repository.save(incidencia)).thenReturn(incidencia);

        Incidencia resultado = service.crear(incidencia);

        assertThat(resultado.getTitulo()).isEqualTo("Test");
        verify(repository, times(1)).save(incidencia);
    }

    @Test
    void deberiaLanzarExcepcionSiNoExisteId() {
        // El mock devuelve vacío cuando busca por id
        when(repository.findById(99L)).thenReturn(Optional.empty());

        // Verificamos que lanza excepción con ese id
        assertThatThrownBy(() -> service.obtenerPorId(99L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("99");
    }

    @Test
    void deberiaListarTodasSiNoHayFiltros() {
        Incidencia incidencia = new Incidencia();
        incidencia.setTitulo("Sin filtro");

        when(repository.findAll()).thenReturn(List.of(incidencia));

        List<Incidencia> resultado = service.listar(null, null);

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