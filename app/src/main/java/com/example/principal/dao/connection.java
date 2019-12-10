package com.example.principal.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class connection extends SQLiteOpenHelper {

    public connection(@Nullable Context context) {
        super(context, "agenda3", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createPaciente = "CREATE TABLE PACIENTE (\n" +
                "ID_PACIENTE INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "NOME VARCHAR(100),\n" +
                "ENDERECO VARCHAR(100),\n" +
                "TELEFONE VARCHAR(20),\n" +
                "FOTO BLOB\n" +
                ");";

        String createMedico = "CREATE TABLE MEDICO (\n" +
                "ID_MEDICO INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "NOME VARCHAR(100),\n" +
                "CRO VARCHAR(50),\n" +
                "TELEFONE VARCHAR(20),\n" +
                "FOTO BLOB\n" +
                ");";

        String createAgenda = "CREATE TABLE AGENDA (\n" +
                "ID_AGENDA INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "ID_PACIENTE INTEGER,\n" +
                "ID_MEDICO INTEGER,\n" +
                "DATA VARCHAR(50),\n" +
                "HORA VARCHAR(50),\n" +
                "NOMEPACIENTE VARCHAR(50),\n" +
                "NOMEMEDICO VARCHAR(50),\n" +
                "FOTOPACIENTE BLOB,\n" +
                "FOTOMEDICO BLOB,\n" +
                "FOREIGN KEY(ID_PACIENTE) REFERENCES PACIENTE (ID_PACIENTE),\n" +
                "FOREIGN KEY(ID_MEDICO) REFERENCES MEDICO (ID_MEDICO)\n" +
                ");";

        db.execSQL(createPaciente);
        db.execSQL(createMedico);
        db.execSQL(createAgenda);
        //db.execSQL("DELETE FROM agendaE");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
