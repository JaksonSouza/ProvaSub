package com.example.principal;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    ImageView foto;
    TextView codigo, nome;

    ImageView fotoPacienteAgendado, fotoMedicoAgendado;
    TextView codigoPacienteAgendado, nomePacienteAgendado, codigoMedicoAgendado, nomeMedicoAgendado, data, hora;


    ItemClickListener itemClickListener;


    public MyHolder(@NonNull View itemView) {
        super(itemView);

        this.foto = itemView.findViewById(R.id.foto);
        this.nome = itemView.findViewById(R.id.nome);
        this.codigo = itemView.findViewById(R.id.codigo);

        this.fotoPacienteAgendado = itemView.findViewById(R.id.fotoPacienteAgendado);
        this.nomePacienteAgendado = itemView.findViewById(R.id.nomePacienteAgendado);
        this.codigoPacienteAgendado= itemView.findViewById(R.id.codigoPacienteAgendado);

        this.fotoMedicoAgendado = itemView.findViewById(R.id.fotoMedicoAgendado);
        this.nomeMedicoAgendado = itemView.findViewById(R.id.nomeMedicoAgendado);
        this.codigoMedicoAgendado = itemView.findViewById(R.id.codigoMedicoAgendado);

        this.data = itemView.findViewById(R.id.dataAgenda);
        this.hora = itemView.findViewById(R.id.horaAgenda);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        this.itemClickListener.onItemClickListener(view, getLayoutPosition());

    }

    public void setItemClickListener(ItemClickListener ic){
        this.itemClickListener = ic;
    }
}
