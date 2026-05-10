package com.academico.model;

public class Incluye {

    private String icD;
    private String codAsignatura;

    public Incluye() {}

    public Incluye(String icD, String codAsignatura) {
        this.icD = icD;
        this.codAsignatura = codAsignatura;
    }

    public String getIcD() { return icD; }
    public void setIcD(String icD) { this.icD = icD; }

    public String getCodAsignatura() { return codAsignatura; }
    public void setCodAsignatura(String codAsignatura) { this.codAsignatura = codAsignatura; }
}
