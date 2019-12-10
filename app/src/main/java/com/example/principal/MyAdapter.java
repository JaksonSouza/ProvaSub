package com.example.principal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.principal.entidades.medico;
import com.example.principal.entidades.paciente;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyHolder>{
    Activity activity;
    Context c;
    List<paciente> modelPaciente;
    String date, time, nomeMedico, codigoMedico;
    byte[] byteMedico;

    public MyAdapter(Context c, List<paciente> modelPaciente, String date, String time, String nomeMedico, String codigoMedico, byte[] byteMedico,Activity activity) {
        this.c = c;
        this.modelPaciente = modelPaciente;
        this.date = date;
        this.time = time;
        this.nomeMedico = nomeMedico;
        this.codigoMedico = codigoMedico;
        this.byteMedico = byteMedico;
        this.activity = activity;
    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

           holder.nome.setText(modelPaciente.get(position).getNOME());
           holder.codigo.setText(modelPaciente.get(position).getID().toString());

           if (modelPaciente.get(position).getFOTO() != null) {
               Bitmap decoded = BitmapFactory.decodeByteArray(modelPaciente.get(position).getFOTO(), 0, modelPaciente.get(position).getFOTO().length);
               holder.foto.setImageBitmap(decoded);
           }

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {

                    String nomePaciente = modelPaciente.get(position).getNOME();
                    Integer codigoPaciente = modelPaciente.get(position).getID();
                    byte[] fotoPaciente = modelPaciente.get(position).getFOTO();

                    Intent intent = new Intent(c, agenda.class);

                    //Paciente
                    intent.putExtra("nomePaciente", nomePaciente);
                    intent.putExtra("codigoPaciente", codigoPaciente.toString());
                    intent.putExtra("bytePaciente", fotoPaciente);

                    intent.putExtra("date", date);
                    intent.putExtra("time", time);

                     //Medico
                     intent.putExtra("nomeMedico", nomeMedico);
                     intent.putExtra("codigoMedico", codigoMedico);
                     intent.putExtra("fotoMedico", byteMedico);

                    c.startActivity(intent);
                    activity.finish();
            }
        });
    }


    @Override
    public int getItemCount() {
        return modelPaciente.size();
    }
}
