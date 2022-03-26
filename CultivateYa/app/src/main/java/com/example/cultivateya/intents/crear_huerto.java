package com.example.cultivateya.intents;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cultivateya.R;
import com.example.cultivateya.adaptadores.Adaptador_cultivos;
import com.example.cultivateya.adaptadores.Adaptador_huertos;
import com.example.cultivateya.modelo.Huerto;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.UUID;

public class crear_huerto extends AppCompatActivity{

    private EditText EditTextPh, EditTextMedida, EditTextNombre;
    private TextView TextViewTipoSuelo;
    private Button button;
    public static String user = "USER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_huerto);

       //Inicializar variables
        user = getIntent().getStringExtra("USER");

        EditTextMedida = findViewById(R.id.etMedida);
        EditTextNombre = findViewById(R.id.etNombre);
        EditTextPh = findViewById(R.id.etPh);
        TextViewTipoSuelo = findViewById(R.id.tvTipo);
        button = findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(EditTextNombre.getText().toString().isEmpty() || EditTextMedida.getText().toString().isEmpty() || EditTextPh.getText().toString().isEmpty() || TextViewTipoSuelo.getText().toString().isEmpty() ) {

                    Toast.makeText(crear_huerto.this, "¡Debes completar todos los campos!",
                            Toast.LENGTH_SHORT).show();

                }

                try {
                    Crear_huerto();
                }catch (Exception e){

                    Toast.makeText(crear_huerto.this, "ERROR: " + e,
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

        EditTextPh.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                    if(!hasFocus){
                        try {
                            setEditTextPh();
                        }catch (Exception e) {
                            Toast.makeText(crear_huerto.this, "ERROR: " + e,
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
            }
        });



    }

    //Metodo para ingresar objeto a la base de datos
    private void Crear_huerto(){

        Huerto huerto = new Huerto();
        huerto.setId_huerto(UUID.randomUUID().toString());
        huerto.setTipoSuelo(TextViewTipoSuelo.getText().toString());
        huerto.setPh(Float.parseFloat(EditTextPh.getText().toString()));
        huerto.setMedida(Integer.parseInt(EditTextMedida.getText().toString()));
        huerto.setNombre_huerto(EditTextNombre.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("USUARIOS").child(user).child("HUERTOS").child(huerto.getId_huerto()).setValue(huerto);

        Intent intent = new Intent(crear_huerto.this,lista_cultivos.class);
        Bundle bundle = new Bundle();
        bundle.putString("USER", user);
        bundle.putString("COD_HUERTO",huerto.getId_huerto());
        intent.putExtras(bundle);
        startActivity(intent);


    }

    //Metodo para describir el tipo del suelo segun el ph
    private void setEditTextPh(){

        float FloatPh = Float.parseFloat((EditTextPh.getText().toString()));

        if (FloatPh <= 5.5) {
            TextViewTipoSuelo.setText(R.string.tiposuelo2);
        }
        if ((FloatPh >= 5.6) && (FloatPh <= 6.0)) {
            TextViewTipoSuelo.setText(R.string.tiposuelo3);
        }
        if ((FloatPh >= 6.1) && (FloatPh <= 6.5)) {
            TextViewTipoSuelo.setText(R.string.tiposuelo4);
        }
        if (FloatPh >= 6.6 && FloatPh <= 7.3) {
            TextViewTipoSuelo.setText(R.string.tiposuelo5);
        }
        if (FloatPh >= 7.4 && FloatPh <= 7.8) {
            TextViewTipoSuelo.setText(R.string.tiposuelo6);
        }
        if (FloatPh >= 7.9 && FloatPh <= 8.4) {
            TextViewTipoSuelo.setText(R.string.tiposuelo7);
        }
        if (FloatPh >= 8.5 && FloatPh <= 9.0) {
            TextViewTipoSuelo.setText(R.string.tiposuelo8);
        }
        if (FloatPh >= 9.1 && FloatPh <= 10.0) {
            TextViewTipoSuelo.setText(R.string.tiposuelo9);
        }
        if (FloatPh >= 10.0 ) {
            TextViewTipoSuelo.setText(R.string.tiposuelo10);
        }

    }

}
