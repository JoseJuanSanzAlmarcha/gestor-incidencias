package com.ntt.practicas.gestoriaincidencias.dto;

import com.ntt.practicas.gestoriaincidencias.model.EstadoIncidencia;
import com.ntt.practicas.gestoriaincidencias.model.PrioridadIncidencia;

public class IncidenciaDTO {

    private Long id;
    private String titulo;
    private String descripcion;
    private EstadoIncidencia estado;
    private PrioridadIncidencia prioridad;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public EstadoIncidencia getEstado() { return estado; }
    public void setEstado(EstadoIncidencia estado) { this.estado = estado; }

    public PrioridadIncidencia getPrioridad() { return prioridad; }
    public void setPrioridad(PrioridadIncidencia prioridad) { this.prioridad = prioridad; }
}