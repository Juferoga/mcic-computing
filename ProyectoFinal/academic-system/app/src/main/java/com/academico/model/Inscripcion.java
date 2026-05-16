package com.academico.model;

public class Inscripcion {

    private String codEstudiante;
    private String codAsignatura;
    private String idProfesor;
    private int grupo;
    private double n1;
    private double n2;
    private double n3;

    public Inscripcion() {}

    public Inscripcion(String codEstudiante, String codAsignatura, String idProfesor, int grupo) {
        this.codEstudiante = codEstudiante;
        this.codAsignatura = codAsignatura;
        this.idProfesor = idProfesor;
        this.grupo = grupo;
        this.n1 = 0.0;
        this.n2 = 0.0;
        this.n3 = 0.0;
    }

    public String getCodEstudiante() { return codEstudiante; }
    public void setCodEstudiante(String codEstudiante) { this.codEstudiante = codEstudiante; }

    public String getCodAsignatura() { return codAsignatura; }
    public void setCodAsignatura(String codAsignatura) { this.codAsignatura = codAsignatura; }

    public String getIdProfesor() { return idProfesor; }
    public void setIdProfesor(String idProfesor) { this.idProfesor = idProfesor; }

    public int getGrupo() { return grupo; }
    public void setGrupo(int grupo) { this.grupo = grupo; }

    public double getN1() { return n1; }
    public void setN1(double n1) { this.n1 = n1; }

    public double getN2() { return n2; }
    public void setN2(double n2) { this.n2 = n2; }

    public double getN3() { return n3; }
    public void setN3(double n3) { this.n3 = n3; }

    // Todo - cambiar esto a un script en bd 
    public double getDefinitiva() {
        return (n1 * 0.35) + (n2 * 0.35) + (n3 * 0.30);
    }
}
