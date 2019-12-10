package com.example.principal;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.principal.entidades.agendaE;

import java.util.List;

public class AdapterAgendados extends RecyclerView.Adapter<MyHolder>{

    Context c;
    List<agendaE> modelAgenda;

    public AdapterAgendados(Context c, List<agendaE> modelAgenda) {
        this.c = c;
        this.modelAgenda = modelAgenda;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.agendados, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        holder.codigoPacienteAgendado.setText(modelAgenda.get(position).getID_PACIENTE().toString());
        holder.codigoMedicoAgendado.setText(modelAgenda.get(position).getID_MEDICO().toString());


        holder.nomePacienteAgendado.setText(modelAgenda.get(position).getNOMEPACIENTE());
        holder.nomeMedicoAgendado.setText(modelAgenda.get(position).getNOMEMEDICO());
        holder.data.setText(modelAgenda.get(position).getDATA());
        holder.hora.setText(modelAgenda.get(position).getHORA());

        if (modelAgenda.get(position).getFOTOPACIENTE() != null) {
            Bitmap decoded = BitmapFactory.decodeByteArray(modelAgenda.get(position).getFOTOPACIENTE(), 0, modelAgenda.get(position).getFOTOPACIENTE().length);
            holder.fotoPacienteAgendado.setImageBitmap(decoded);
        }

        if (modelAgenda.get(position).getFOTOMEDICO() != null) {
            Bitmap decoded = BitmapFactory.decodeByteArray(modelAgenda.get(position).getFOTOMEDICO(), 0, modelAgenda.get(position).getFOTOMEDICO().length);
            holder.fotoMedicoAgendado.setImageBitmap(decoded);
        }


        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return modelAgenda.size();
    }
}
