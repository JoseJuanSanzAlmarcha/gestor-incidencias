package com.ntt.practicas.gestoriaincidencias.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

// Le dice a JPA que esta clase es una tabla en la base de datos
@Entity
public class Incidencia {

    // Clave primaria de la tabla
    @Id
    // El id se genera automáticamente, no lo ponemos nosotros
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Columnas normales de texto en la tabla
    private String titulo;
    private String descripcion;

    // Le dice a JPA que guarde el enum como texto ("ABIERTO", "EN_PROGRESO", "CERRADO")
    @Enumerated(EnumType.STRING)
    private EstadoIncidencia estado;

    @Enumerated(EnumType.STRING)
    private PrioridadIncidencia prioridad;

    // Columna para guardar la fecha y hora de creación
    private LocalDateTime fechaCreaccion;

    /*Constructor vacío — se ejecuta cuando se crea una nueva Incidencia
    Asigna automáticamente la fecha actual, no hay que ponerla a mano*/
    public Incidencia(){
        this.fechaCreaccion = LocalDateTime.now();
    }
    /* Getters y Setters — permiten leer y modificar los campos privados desde fuera de la clase
    Sin ellos, nadie podría acceder a id, titulo, etc.*/

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
