package com.academico.model;

public class Profesor {

    private String id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String profesion;

    public Profesor() {}

    public Profesor(String id, String nombre, String direccion, String telefono, String profesion) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.profesion = profesion;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getProfesion() { return profesion; }
    public void setProfesion(String profesion) { this.profesion = profesion; }

    @Override
    public String toString() {
        return id + " - " + nombre;
    }
}