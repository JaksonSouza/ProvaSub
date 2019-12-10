package com.example.principal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.principal.entidades.medico;

import java.util.List;

public class AdapterMedico extends RecyclerView.Adapter<MyHolder>{

    Context c;
    List<medico> modelMedico;
    String date, time, nomePaciente, codigoPaciente;
    byte[] bytePaciente;
    Activity activity;


    public AdapterMedico(Context c, List<medico> modelMedico, String date, String time, String nomePaciente, String codigoPaciente, byte[] bytePaciente, Activity activity) {
        this.c = c;
        this.modelMedico = modelMedico;
        this.date = date;
        this.time = time;
        this.nomePaciente = nomePaciente;
        this.codigoPaciente = codigoPaciente;
        this.bytePaciente = bytePaciente;
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

        holder.nome.setText(modelMedico.get(position).getNOME());
        holder.codigo.setText(modelMedico.get(position).getID().toString());

        if (modelMedico.get(position).getFOTO() != null) {
            Bitmap decoded = BitmapFactory.decodeByteArray(modelMedico.get(position).getFOTO(), 0, modelMedico.get(position).getFOTO().length);
            holder.foto.setImageBitmap(decoded);
        }

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {

                String nomeMedico = modelMedico.get(position).getNOME();
                Integer codigoMedico = modelMedico.get(position).getID();
                byte[] fotoMedico = modelMedico.get(position).getFOTO();

                Intent intent = new Intent(c, agenda.class);

                intent.putExtra("nomeMedico", nomeMedico);
                intent.putExtra("codigoMedico", codigoMedico.toString());
                intent.putExtra("fotoMedico", fotoMedico);

                intent.putExtra("date", date);
                intent.putExtra("time", time);

                //Paciente
                intent.putExtra("nomePaciente", nomePaciente);
                intent.putExtra("codigoPaciente", codigoPaciente);
                intent.putExtra("bytePaciente", bytePaciente);

                c.startActivity(intent);
                activity.finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelMedico.size();
    }
}
