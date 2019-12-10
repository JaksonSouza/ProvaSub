package com.example.principal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Consultório de Odontologia");

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},0);
        }
    }

    public void bCadastrarPaciente(View v){
        Intent intent = new Intent(this, cadastrarP.class);
        startActivity(intent);
    }

    public void bCadastrarMedico(View v){
        Intent intent = new Intent(this, cadastrarMedico.class);
        startActivity(intent);
    }

    public void bAgenda(View v){
        Intent intent = new Intent(this, agenda.class);
        startActivity(intent);
    }

    public void bAgendados(View v){
        Intent intent = new Intent(this, recyclerView.class);
        intent.putExtra("flag", 3);

        startActivity(intent);
        //finish();
    }
}
