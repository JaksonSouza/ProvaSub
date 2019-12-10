package com.example.principal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.principal.dao.connection;
import com.example.principal.dao.dao;

public class recyclerView extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter Adapter;
    AdapterMedico AdapterMedico;
    AdapterAgendados AdapterAgendados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        setTitle("Lista");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        connection connection = new connection(this);
        dao dao = new dao(connection.getReadableDatabase());

        Intent intent = getIntent();

        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");
        Integer flag = intent.getIntExtra("flag",0);

        //medico
        String nomeMedico = intent.getStringExtra("nomeMedico");
        String codigoMedico = intent.getStringExtra("codigoMedico");
        byte [] byteMedico = intent.getByteArrayExtra("byteMedico");

        //paciente
        String nomePaciente = intent.getStringExtra("nomePaciente");
        String codigoPaciente = intent.getStringExtra("codigoPaciente");
        byte [] bytePaciente = intent.getByteArrayExtra("bytePaciente");

        if(flag == 1){
            Adapter = new MyAdapter(this, dao.listarPaciente(),date, time, nomeMedico, codigoMedico, byteMedico, this);
            recyclerView.setAdapter(Adapter);
        }else if(flag == 2){
            AdapterMedico = new AdapterMedico(this, dao.listarMedico(), date, time, nomePaciente, codigoPaciente, bytePaciente, this);
            recyclerView.setAdapter(AdapterMedico);
        }
        else if(flag == 3){
            AdapterAgendados = new AdapterAgendados(this, dao.listarAgendados());
            recyclerView.setAdapter(AdapterAgendados);
        }
    }
}
