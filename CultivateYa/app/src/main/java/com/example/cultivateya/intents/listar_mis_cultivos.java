package com.example.cultivateya.intents;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cultivateya.R;
import com.example.cultivateya.adaptadores.Adaptador_cultivos;
import com.example.cultivateya.adaptadores.Adaptador_huertos;
import com.example.cultivateya.modelo.Cultivo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class listar_mis_cultivos extends AppCompatActivity {

    private ListView lista;
    private Adaptador_cultivos adaptador_cultivos;
    private Button button,buttonlink;

    public static String USUARIO = "USER";
    public static String codhuertointent = "COD_HUERTO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_mis_cultivos);

        Bundle extras = getIntent().getExtras();

        assert extras != null;
        USUARIO = extras.getString("USER");
        codhuertointent = extras.getString("COD_HUERTO");

        buttonlink = findViewById(R.id.button9);
        button = findViewById(R.id.menu);
        lista = findViewById(R.id.lista_mis_cultivos);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(listar_mis_cultivos.this , menu_principal.class);
                intent.putExtra("USER", USUARIO);
                startActivity(intent);
            }
        });


        FirebaseDatabase.getInstance().getReference().child("USUARIOS").child(USUARIO).child("HUERTOS").child(codhuertointent).child("CULTIVOS").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                final ArrayList<Cultivo> arrayListcultivos = new ArrayList<>();

                if (!dataSnapshot.exists()) {

                    Toast.makeText(listar_mis_cultivos.this, "NO TIENES CULTIVOS EN TU HUERTO: ",
                            Toast.LENGTH_SHORT).show();
                    arrayListcultivos.clear();
                    adaptador_cultivos = new Adaptador_cultivos(listar_mis_cultivos.this, arrayListcultivos, codhuertointent, USUARIO);
                    lista.setAdapter(adaptador_cultivos);

                    buttonlink.setVisibility(View.VISIBLE);
                    buttonlink.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(listar_mis_cultivos.this,lista_cultivos.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("USER", USUARIO);
                            bundle.putString("COD_HUERTO",codhuertointent);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });


                } else {
                    try {
                        for (DataSnapshot objSnapShot : dataSnapshot.getChildren()) {
                            Cultivo cultivo = objSnapShot.getValue(Cultivo.class);
                            arrayListcultivos.add(cultivo);
                            adaptador_cultivos = new Adaptador_cultivos(listar_mis_cultivos.this, arrayListcultivos, codhuertointent, USUARIO);
                            lista.setAdapter(adaptador_cultivos);

                        }
                    } catch (Exception e) {

                        Toast.makeText(listar_mis_cultivos.this, "ERROR: " + e,
                                Toast.LENGTH_SHORT).show();
                    }

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }









}
