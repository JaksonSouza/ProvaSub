package com.example.principal.entidades;

import java.io.Serializable;

public class agendaE implements Serializable {

       Integer ID_AGENDA;
       Integer ID_PACIENTE;
       Integer ID_MEDICO;
       String DATA;
       String HORA;
       String NOMEPACIENTE;
       String NOMEMEDICO;
       byte []FOTOPACIENTE;
       byte []FOTOMEDICO;

    public Integer getID_AGENDA() {
        return ID_AGENDA;
    }

    public String getNOMEPACIENTE() {
        return NOMEPACIENTE;
    }

    public void setNOMEPACIENTE(String NOMEPACIENTE) {
        this.NOMEPACIENTE = NOMEPACIENTE;
    }

    public String getNOMEMEDICO() {
        return NOMEMEDICO;
    }

    public void setNOMEMEDICO(String NOMEMEDICO) {
        this.NOMEMEDICO = NOMEMEDICO;
    }

    public byte[] getFOTOPACIENTE() {
        return FOTOPACIENTE;
    }

    public void setFOTOPACIENTE(byte[] FOTOPACIENTE) {
        this.FOTOPACIENTE = FOTOPACIENTE;
    }

    public byte[] getFOTOMEDICO() {
        return FOTOMEDICO;
    }

    public void setFOTOMEDICO(byte[] FOTOMEDICO) {
        this.FOTOMEDICO = FOTOMEDICO;
    }

    public void setID_AGENDA(Integer ID_AGENDA) {
        this.ID_AGENDA = ID_AGENDA;
    }

    public Integer getID_PACIENTE() {
        return ID_PACIENTE;
    }

    public void setID_PACIENTE(Integer ID_PACIENTE) {
        this.ID_PACIENTE = ID_PACIENTE;
    }

    public Integer getID_MEDICO() {
        return ID_MEDICO;
    }

    public void setID_MEDICO(Integer ID_MEDICO) {
        this.ID_MEDICO = ID_MEDICO;
    }

    public String getDATA() {
        return DATA;
    }

    public void setDATA(String DATA) {
        this.DATA = DATA;
    }

    public String getHORA() {
        return HORA;
    }

    public void setHORA(String HORA) {
        this.HORA = HORA;
    }
}
