package com.example.principal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.principal.dao.connection;
import com.example.principal.dao.dao;
import com.example.principal.entidades.paciente;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class cadastrarP extends AppCompatActivity {

    private TextView nome, endereco, telefone;
    byte []foto;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_p);

        setTitle("Cadastrar Paciente");

        nome=findViewById(R.id.tPacienteNome);
        endereco=findViewById(R.id.tPacienteEndereco);
        telefone=findViewById(R.id.tPacienteTelefone);
        imageView=findViewById(R.id.imageView);
    }

    public void bCancelar(View v){
        finish();
    }

    public void bCadastrar(View v){

        if(nome.getText().length() == 0 || endereco.getText().length() == 0 || telefone.getText().length() == 0){
            Toast toast = Toast.makeText(getApplicationContext(), "Preencha todos os campos.", Toast.LENGTH_SHORT);
            toast.show();
        }else if(foto == null){
            Toast toast = Toast.makeText(getApplicationContext(), "Você precisa tirar uma foto.", Toast.LENGTH_SHORT);
            toast.show();
        }else{
                paciente paciente = new paciente();
                paciente.setNOME(nome.getText().toString());
                paciente.setENDERECO(endereco.getText().toString());
                paciente.setTELEFONE((telefone.getText().toString()));
                paciente.setFOTO((foto));

                connection connection = new connection(this);
                dao dao = new dao(connection.getWritableDatabase());

                dao.inserirPaciente(paciente);
                Toast toast = Toast.makeText(getApplicationContext(), "Cadastrado com sucesso!", Toast.LENGTH_SHORT);
                toast.show();
                finish();
        }
    }

    public void bTirarFoto(View v){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 1 && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imagen = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imagen);

            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            imagen.compress(Bitmap.CompressFormat.JPEG, 100, byteArray);
            foto = byteArray.toByteArray();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}