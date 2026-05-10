package com.academico.model;

public class Requiere {

    private String codAsignatura;
    private String codAsignaturaRequerida;

    public Requiere() {}

    public Requiere(String codAsignatura, String codAsignaturaRequerida) {
        this.codAsignatura = codAsignatura;
        this.codAsignaturaRequerida = codAsignaturaRequerida;
    }

    public String getCodAsignatura() { return codAsignatura; }
    public void setCodAsignatura(String codAsignatura) { this.codAsignatura = codAsignatura; }

    public String getCodAsignaturaRequerida() { return codAsignaturaRequerida; }
    public void setCodAsignaturaRequerida(String codAsignaturaRequerida) { this.codAsignaturaRequerida = codAsignaturaRequerida; }
}
