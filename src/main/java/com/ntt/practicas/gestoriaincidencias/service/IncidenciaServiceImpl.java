package com.ntt.practicas.gestoriaincidencias.service;

import com.ntt.practicas.gestoriaincidencias.model.EstadoIncidencia;
import com.ntt.practicas.gestoriaincidencias.model.Incidencia;
import com.ntt.practicas.gestoriaincidencias.model.PrioridadIncidencia;
import com.ntt.practicas.gestoriaincidencias.repository.IncidenciaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

// Le dice a Spring que esta clase es un Service y la gestiona automáticamente
@Service
public class IncidenciaServiceImpl implements IncidenciaService {

    // El repository es el que habla con la base de datos
    private final IncidenciaRepository repository;

    // Spring inyecta automáticamente el repository aquí, no hace falta hacer new
    public IncidenciaServiceImpl(IncidenciaRepository repository) {
        this.repository = repository;
    }

    // Guarda la incidencia en la base de datos y la devuelve con su id asignado
    @Override
    public Incidencia crear(Incidencia incidencia) {
        return repository.save(incidencia);
    }

    // Devuelve todas las incidencias de la base de datos
    @Override
    public List<Incidencia> obtenerTodas() {
        return repository.findAll();
    }

    // Busca una incidencia por id, si no existe lanza una excepción con mensaje
    @Override
    public Incidencia obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Incidencia no encontrada con id: " + id));
    }

    // Primero comprueba que existe, luego actualiza los campos y guarda de nuevo
    @Override
    public Incidencia actualizar(Long id, Incidencia incidencia) {
        Incidencia existente = obtenerPorId(id);
        existente.setTitulo(incidencia.getTitulo());
        existente.setDescripcion(incidencia.getDescripcion());
        existente.setEstado(incidencia.getEstado());
        existente.setPrioridad(incidencia.getPrioridad());
        return repository.save(existente);
    }

    // Comprueba que existe y luego la elimina
    @Override
    public void eliminar(Long id) {
        obtenerPorId(id);
        repository.deleteById(id);
    }

    // Decide qué query usar según los parámetros que lleguen
    // valueOf() convierte el texto ("ABIERTO") al valor del enum correspondiente
    @Override
    public List<Incidencia> filtrar(String estado, String prioridad) {
        if (estado != null && prioridad != null) {
            // Ambos informados → filtra por los dos
            return repository.findByEstadoAndPrioridad(
                    EstadoIncidencia.valueOf(estado),
                    PrioridadIncidencia.valueOf(prioridad)
            );
        } else if (estado != null) {
            // Solo estado → filtra solo por estado
            return repository.findByEstado(EstadoIncidencia.valueOf(estado));
        } else if (prioridad != null) {
            // Solo prioridad → filtra solo por prioridad
            return repository.findByPrioridad(PrioridadIncidencia.valueOf(prioridad));
        } else {
            // Ninguno informado → devuelve todas
            return repository.findAll();
        }
    }
}