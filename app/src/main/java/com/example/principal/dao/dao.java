package com.example.principal.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.principal.entidades.agendaE;
import com.example.principal.entidades.medico;
import com.example.principal.entidades.paciente;

import java.util.LinkedList;
import java.util.List;

public class dao {

    private SQLiteDatabase connection;

    public dao(SQLiteDatabase connection) {
        this.connection = connection;
    }

    public void inserirPaciente(paciente paciente){
        ContentValues values = new ContentValues();

        values.put("NOME", paciente.getNOME());
        values.put("ENDERECO", paciente.getENDERECO());
        values.put("TELEFONE", paciente.getTELEFONE());
        values.put("FOTO", paciente.getFOTO());

        connection.insertOrThrow("PACIENTE",null,values);
    }

    public List<paciente> listarPaciente(){
        Cursor cursor=connection.rawQuery("SELECT * FROM PACIENTE",null);

        List<paciente> lista = new LinkedList();
        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {
            paciente paciente = new paciente();

            paciente.setID(cursor.getInt(cursor.getColumnIndexOrThrow("ID_PACIENTE")));
            paciente.setNOME(cursor.getString(cursor.getColumnIndexOrThrow("NOME")));
            paciente.setENDERECO(cursor.getString(cursor.getColumnIndexOrThrow("ENDERECO")));
            paciente.setTELEFONE(cursor.getString(cursor.getColumnIndexOrThrow("TELEFONE")));
            paciente.setFOTO(cursor.getBlob(cursor.getColumnIndexOrThrow("FOTO")));

            lista.add(paciente);
            cursor.moveToNext();
        }
        return  lista;
    }

    public void inserirMedico(medico medico){
        ContentValues values = new ContentValues();

        values.put("NOME", medico.getNOME());
        values.put("CRO", medico.getCRO());
        values.put("TELEFONE", medico.getTELEFONE());
        values.put("FOTO", medico.getFOTO());

        connection.insertOrThrow("MEDICO",null,values);
    }

    public List<medico> listarMedico(){
        Cursor cursor=connection.rawQuery("SELECT * FROM MEDICO",null);

        List<medico> lista = new LinkedList();
        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {
            medico medico = new medico();

            medico.setID(cursor.getInt(cursor.getColumnIndexOrThrow("ID_MEDICO")));
            medico.setNOME(cursor.getString(cursor.getColumnIndexOrThrow("NOME")));
            medico.setCRO(cursor.getString(cursor.getColumnIndexOrThrow("CRO")));
            medico.setTELEFONE(cursor.getString(cursor.getColumnIndexOrThrow("TELEFONE")));
            medico.setFOTO(cursor.getBlob(cursor.getColumnIndexOrThrow("FOTO")));

            lista.add(medico);
            cursor.moveToNext();
        }
        return lista;
    }

    public void inserirAgenda(agendaE agenda){
        ContentValues values = new ContentValues();

        values.put("ID_PACIENTE", agenda.getID_PACIENTE());
        values.put("ID_MEDICO", agenda.getID_MEDICO());
        values.put("DATA", agenda.getDATA());
        values.put("HORA", agenda.getHORA());
        values.put("NOMEPACIENTE", agenda.getNOMEPACIENTE());
        values.put("NOMEMEDICO", agenda.getNOMEMEDICO());
        values.put("FOTOPACIENTE", agenda.getFOTOPACIENTE());
        values.put("FOTOMEDICO", agenda.getFOTOMEDICO());

        connection.insertOrThrow("AGENDA",null,values);
    }

    public List<agendaE> listarAgendados(){
        Cursor cursor=connection.rawQuery("SELECT * FROM AGENDA;",null);

        List<agendaE> lista = new LinkedList();
        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {
            agendaE agenda = new agendaE();

            agenda.setID_AGENDA(cursor.getInt(cursor.getColumnIndexOrThrow("ID_AGENDA")));
            agenda.setID_PACIENTE(cursor.getInt(cursor.getColumnIndexOrThrow("ID_PACIENTE")));
            agenda.setID_MEDICO(cursor.getInt(cursor.getColumnIndexOrThrow("ID_MEDICO")));
            agenda.setDATA(cursor.getString(cursor.getColumnIndexOrThrow("DATA")));
            agenda.setHORA(cursor.getString(cursor.getColumnIndexOrThrow("HORA")));
            agenda.setNOMEPACIENTE(cursor.getString(cursor.getColumnIndexOrThrow("NOMEPACIENTE")));
            agenda.setNOMEMEDICO(cursor.getString(cursor.getColumnIndexOrThrow("NOMEMEDICO")));
            agenda.setFOTOPACIENTE(cursor.getBlob(cursor.getColumnIndexOrThrow("FOTOPACIENTE")));
            agenda.setFOTOMEDICO(cursor.getBlob(cursor.getColumnIndexOrThrow("FOTOMEDICO")));

            lista.add(agenda);
            cursor.moveToNext();
        }
        return lista;
    }
}
