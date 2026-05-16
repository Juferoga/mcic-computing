package com.academico.model;

public class Asignatura {

    private String codA;
    private String nombre;
    private int intensidadHoraria;
    private int creditos;

    public Asignatura() {}

    public Asignatura(String codA, String nombre, int intensidadHoraria, int creditos) {
        this.codA = codA;
        this.nombre = nombre;
        this.intensidadHoraria = intensidadHoraria;
        this.creditos = creditos;
    }

    public String getCodA() { return codA; }
    public void setCodA(String codA) { this.codA = codA; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getIntensidadHoraria() { return intensidadHoraria; }
    public void setIntensidadHoraria(int intensidadHoraria) { this.intensidadHoraria = intensidadHoraria; }

    public int getCreditos() { return creditos; }
    public void setCreditos(int creditos) { this.creditos = creditos; }

    @Override
    public String toString() {
        return codA + " - " + nombre;
    }
}