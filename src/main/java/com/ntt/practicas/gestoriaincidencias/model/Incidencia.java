package com.ntt.practicas.gestoriaincidencias.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Incidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descripcion;

    @Enumerated(EnumType.STRING)
    private EstadoIncidencia estado;

    @Enumerated(EnumType.STRING)
    private PrioridadIncidencia prioridad;

    private LocalDateTime fechaCreaccion;

    public Incidencia(){
        this.fechaCreaccion = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EstadoIncidencia getEstado() {
        return estado;
    }

    public void setEstado(EstadoIncidencia estado) {
        this.estado = estado;
    }

    public PrioridadIncidencia getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(PrioridadIncidencia prioridad) {
        this.prioridad = prioridad;
    }

    public LocalDateTime getFechaCreaccion() {
        return fechaCreaccion;
    }

    public void setFechaCreaccion(LocalDateTime fechaCreaccion) {
        this.fechaCreaccion = fechaCreaccion;
    }
}