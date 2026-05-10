package com.academico.model;

public class Imparte {

    private String idProfesor;
    private String codAsignatura;
    private int grupo;
    private String horario;

    public Imparte() {}

    public Imparte(String idProfesor, String codAsignatura, int grupo, String horario) {
        this.idProfesor = idProfesor;
        this.codAsignatura = codAsignatura;
        this.grupo = grupo;
        this.horario = horario;
    }

    public String getIdProfesor() { return idProfesor; }
    public void setIdProfesor(String idProfesor) { this.idProfesor = idProfesor; }

    public String getCodAsignatura() { return codAsignatura; }
    public void setCodAsignatura(String codAsignatura) { this.codAsignatura = codAsignatura; }

    public int getGrupo() { return grupo; }
    public void setGrupo(int grupo) { this.grupo = grupo; }

    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }

    @Override
    public String toString() {
        return codAsignatura + " - Grupo " + grupo + " - " + idProfesor;
    }
}