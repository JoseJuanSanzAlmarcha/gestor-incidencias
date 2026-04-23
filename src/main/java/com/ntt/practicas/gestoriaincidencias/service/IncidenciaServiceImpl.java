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

    /* Devuelve incidencias filtradas según los parámetros recibidos.
     Si ambos son null, devuelve todas. Si uno o los dos tienen valor, filtra.*/
    @Override
    public List<Incidencia> listar(EstadoIncidencia estado, PrioridadIncidencia prioridad) {
        if (estado != null && prioridad != null) {
            return repository.findByEstadoAndPrioridad(estado, prioridad);
        } else if (estado != null) {
            return repository.findByEstado(estado);
        } else if (prioridad != null) {
            return repository.findByPrioridad(prioridad);
        } else {
            return repository.findAll();
        }
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
}