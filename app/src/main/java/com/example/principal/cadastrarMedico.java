package com.example.principal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.principal.dao.connection;
import com.example.principal.dao.dao;
import com.example.principal.entidades.medico;

import java.io.ByteArrayOutputStream;

public class cadastrarMedico extends AppCompatActivity {

    private TextView nome, cro, telefone;
    byte []foto;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_medico);

        setTitle("Cadastrar Médico");

        nome=findViewById(R.id.tNomeMedico);
        cro=findViewById(R.id.tCRO);
        telefone=findViewById(R.id.tTelefoneMedico);
        imageView=findViewById(R.id.imageView2);
    }

    public void bCancelar(View v){
        finish();
    }

    public void bCadastrar(View v){

        if(nome.getText().length() == 0 || cro.getText().length() == 0 || telefone.getText().length() == 0){
            Toast toast = Toast.makeText(getApplicationContext(), "Preencha todos os campos.", Toast.LENGTH_SHORT);
            toast.show();

        }else if(foto == null){
            Toast toast = Toast.makeText(getApplicationContext(), "Você precisa tirar uma foto.", Toast.LENGTH_SHORT);
            toast.show();

        }else{

            medico medico = new medico();

            medico.setNOME(nome.getText().toString());
            medico.setCRO(cro.getText().toString());
            medico.setTELEFONE((telefone.getText().toString()));
            medico.setFOTO((foto));

            connection connection = new connection(this);
            dao dao = new dao(connection.getWritableDatabase());

            dao.inserirMedico(medico);
            Toast toast = Toast.makeText(getApplicationContext(), "Cadastrado com sucesso!", Toast.LENGTH_SHORT);
            toast.show();
            finish();
        }
    }

    public void bFoto(View v){
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
