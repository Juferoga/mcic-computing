package com.academico.model;

import java.time.LocalDate;

public class Estudiante {

    private String codE;
    private String nomE;
    private String dirE;
    private String telE;
    private LocalDate fechNac;
    private String idCarr;

    public Estudiante() {}

    public Estudiante(String codE, String nomE, String dirE, String telE, LocalDate fechNac, String idCarr) {
        this.codE = codE;
        this.nomE = nomE;
        this.dirE = dirE;
        this.telE = telE;
        this.fechNac = fechNac;
        this.idCarr = idCarr;
    }

    public String getCodE() { return codE; }
    public void setCodE(String codE) { this.codE = codE; }

    public String getNomE() { return nomE; }
    public void setNomE(String nomE) { this.nomE = nomE; }

    public String getDirE() { return dirE; }
    public void setDirE(String dirE) { this.dirE = dirE; }

    public String getTelE() { return telE; }
    public void setTelE(String telE) { this.telE = telE; }

    public LocalDate getFechNac() { return fechNac; }
    public void setFechNac(LocalDate fechNac) { this.fechNac = fechNac; }

    public String getIdCarr() { return idCarr; }
    public void setIdCarr(String idCarr) { this.idCarr = idCarr; }

    @Override
    public String toString() {
        return codE + " - " + nomE;
    }
}