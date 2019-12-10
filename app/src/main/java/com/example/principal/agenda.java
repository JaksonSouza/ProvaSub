package com.example.principal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.principal.dao.connection;
import com.example.principal.dao.dao;
import com.example.principal.entidades.agendaE;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;


public class agenda extends AppCompatActivity {

        private static Button date, time;
        private static TextView set_date, set_time;
        private static final int Date_id = 0;
        private static final int Time_id = 1;

        TextView nomePaciente, codigoPaciente;
        ImageView fotoPaciente;

        TextView nomeMedico, codigoMedico;
        ImageView fotoMedico;

        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_agenda);

            setTitle("Agendar Horário");

            date = (Button) findViewById(R.id.selectdate);
            time = (Button) findViewById(R.id.selecttime);

            set_date = (TextView) findViewById(R.id.set_date); //vou passar esses dados
            set_time = (TextView) findViewById(R.id.set_time);

            nomePaciente = (TextView) findViewById(R.id.nomePaciente);
            codigoPaciente = (TextView) findViewById(R.id.codigoPaciente);
            fotoPaciente = (ImageView) findViewById(R.id.fotoPaciente);

            nomeMedico = (TextView) findViewById(R.id.nomeMedico);
            codigoMedico = (TextView) findViewById(R.id.codigoMedico);
            fotoMedico = (ImageView) findViewById(R.id.fotoMedico);

            date.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    showDialog(Date_id);
                }
            });
            time.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    showDialog(Time_id);
                }
            });

            Intent intent = getIntent();

            String nomePac = intent.getStringExtra("nomePaciente");
            String idPac = intent.getStringExtra("codigoPaciente");
            byte []fotoPac = intent.getByteArrayExtra("bytePaciente");
            String nomeMed = intent.getStringExtra("nomeMedico");
            String idMed = intent.getStringExtra("codigoMedico");
            byte []fotoMed = intent.getByteArrayExtra("fotoMedico");

            String date = intent.getStringExtra("date");
            String time = intent.getStringExtra("time");

            if(date != null){
                set_date.setText(date);
            }if(time != null){
                set_time.setText(time);
            }
            if(idPac != null){
                codigoPaciente.setText(idPac);
            }
            if(idMed != null){
                codigoMedico.setText(idMed);
            }

            if(nomePac != null){
                if(fotoPac != null) {
                    Bitmap decoded = BitmapFactory.decodeByteArray(fotoPac, 0, fotoPac.length);
                    fotoPaciente.setImageBitmap(decoded);
                }
                nomePaciente.setText(nomePac);
            }

            if(nomeMed != null){
                if(fotoMed != null) {
                    Bitmap decoded = BitmapFactory.decodeByteArray(fotoMed, 0, fotoMed.length);
                    fotoMedico.setImageBitmap(decoded);
                }
                nomeMedico.setText(nomeMed);
                    codigoMedico.setText(idMed);
            }
        }

        protected Dialog onCreateDialog(int id) {
            Calendar c = Calendar.getInstance();

            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            switch (id) {
                case Date_id:

                    return new DatePickerDialog(this, date_listener, year,
                            month, day);
                case Time_id:
                    return new TimePickerDialog(this, time_listener, hour,
                            minute, false);
            }
            return null;
        }
        DatePickerDialog.OnDateSetListener date_listener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                String date1 = String.valueOf(month+1) + "/" + String.valueOf(day)
                        + "/" + String.valueOf(year);
                set_date.setText(date1);
            }
        };
        TimePickerDialog.OnTimeSetListener time_listener = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {
                String time1 = String.valueOf(hour) + ":" + String.valueOf(minute);
                set_time.setText(time1);
            }
        };

        public void bSelecionarPaciente(View v){

            Intent intent = new Intent(this, recyclerView.class);

            //data e hota
            intent.putExtra("date", set_date.getText());
            intent.putExtra("time", set_time.getText());
            intent.putExtra("flag", 1);

            //dados medico
            intent.putExtra("nomeMedico", nomeMedico.getText());
            intent.putExtra("codigoMedico", codigoMedico.getText().toString());

            Bitmap bitmap = Bitmap.createBitmap(fotoMedico.getDrawable().getIntrinsicWidth(),
            fotoMedico.getDrawable().getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            final Canvas canvas = new Canvas(bitmap);
            fotoMedico.getDrawable().setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            fotoMedico.getDrawable().draw(canvas);

            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArray);
            byte[] byteMedico = byteArray.toByteArray();
            intent.putExtra("byteMedico", byteMedico);
            startActivity(intent);
            finish();
        }

        public void bSelecionarMedico(View v){
            Intent intent = new Intent(this, recyclerView.class);

            //data e hota
            intent.putExtra("date", set_date.getText());
            intent.putExtra("time", set_time.getText());
            intent.putExtra("flag", 2);

            Bitmap bitmap = Bitmap.createBitmap(fotoPaciente.getDrawable().getIntrinsicWidth(),
            fotoPaciente.getDrawable().getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            final Canvas canvas = new Canvas(bitmap);
            fotoPaciente.getDrawable().setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            fotoPaciente.getDrawable().draw(canvas);

            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArray);
            byte[] bytePaciente = byteArray.toByteArray();

            intent.putExtra("nomePaciente", nomePaciente.getText());
            intent.putExtra("codigoPaciente", codigoPaciente.getText().toString());
            intent.putExtra("bytePaciente", bytePaciente);

            startActivity(intent);
            finish();
        }

        public  void bCancelar(View v){
            finish();
        }

        public void bCadastrar(View v){
            if(codigoPaciente.getText().length() == 0 || codigoMedico.getText().length() == 0 || set_date.getText().length() == 0 || set_time.getText().length() == 0) {
                Toast toast = Toast.makeText(getApplicationContext(), "Selecione todos os campos.", Toast.LENGTH_SHORT);
                toast.show();

            }else{

                agendaE  agenda = new agendaE();

                agenda.setID_PACIENTE(Integer.parseInt(codigoPaciente.getText().toString()));
                agenda.setID_MEDICO(Integer.parseInt(codigoMedico.getText().toString()));
                agenda.setDATA(set_date.getText().toString());
                agenda.setHORA(set_time.getText().toString());
                agenda.setNOMEMEDICO(nomeMedico.getText().toString());
                agenda.setNOMEPACIENTE(nomePaciente.getText().toString());

                Bitmap bitmap = Bitmap.createBitmap(fotoPaciente.getDrawable().getIntrinsicWidth(),
                fotoPaciente.getDrawable().getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                final Canvas canvas = new Canvas(bitmap);
                fotoPaciente.getDrawable().setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                fotoPaciente.getDrawable().draw(canvas);

                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArray);
                byte[] bytePaciente = byteArray.toByteArray();
                agenda.setFOTOPACIENTE(bytePaciente);

                Bitmap bitmap2 = Bitmap.createBitmap(fotoMedico.getDrawable().getIntrinsicWidth(),
                fotoMedico.getDrawable().getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                final Canvas canvas2 = new Canvas(bitmap2);
                fotoMedico.getDrawable().setBounds(0, 0, canvas2.getWidth(), canvas2.getHeight());
                fotoMedico.getDrawable().draw(canvas2);

                ByteArrayOutputStream byteArray2 = new ByteArrayOutputStream();
                bitmap2.compress(Bitmap.CompressFormat.JPEG, 100, byteArray2);
                byte[] byteMedico = byteArray2.toByteArray();
                agenda.setFOTOMEDICO(byteMedico);

                connection connection = new connection(this);
                dao dao = new dao(connection.getWritableDatabase());

                dao.inserirAgenda(agenda);
                Toast toast = Toast.makeText(getApplicationContext(), "Cadastrado com sucesso!", Toast.LENGTH_SHORT);
                toast.show();
                finish();
            }
        }
    }