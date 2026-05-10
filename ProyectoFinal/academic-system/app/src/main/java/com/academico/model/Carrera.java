package com.academico.model;

public class Carrera {

    private String id;
    private String nombre;
    private String registroCalificacion;

    public Carrera() {}

    public Carrera(String id, String nombre, String registroCalificacion) {
        this.id = id;
        this.nombre = nombre;
        this.registroCalificacion = registroCalificacion;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getRegistroCalificacion() { return registroCalificacion; }
    public void setRegistroCalificacion(String registroCalificacion) { this.registroCalificacion = registroCalificacion; }

    @Override
    public String toString() {
        return id + " - " + nombre;
    }
}