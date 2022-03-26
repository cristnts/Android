package com.example.cultivateya.intents;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cultivateya.R;
import com.example.cultivateya.adaptadores.Adaptador_huertos;
import com.example.cultivateya.modelo.Huerto;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class listar_mis_huertos extends AppCompatActivity {

    private ListView lista;
    private Adaptador_huertos adaptador_huertos;
   // private TextView user;
    private Button button,buttonDel, buttonlink;
    public static String user = "USER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_mis_huertos);

        user = getIntent().getStringExtra("USER");

        lista = findViewById(R.id.lista_mis_huertos);
     //   user = findViewById(R.id.user);
        button = findViewById(R.id.button6);
        buttonDel = findViewById(R.id.button2);
        buttonlink = findViewById(R.id.button8);




/**
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                final Huerto item = adaptador_huertos.getItem(position);

                Intent intent = new Intent(view.getContext(), listar_mis_cultivos.class);

                 Bundle bundle = new Bundle();
                bundle.putString("USER", user);
                bundle.putString("COD_HUERTO",item.getId_huerto());
                intent.putExtras(bundle);
                startActivity(intent);



            }
        });
**/


       //user.setText("");



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                Intent intent = new Intent(listar_mis_huertos.this , menu_principal.class);
                intent.putExtra("USER" , user);
                startActivity(intent);
                }
                catch (Exception e){
                    Toast.makeText(listar_mis_huertos.this, "ERROR : " + e,
                            Toast.LENGTH_SHORT).show();

                }
            }
        });

        FirebaseDatabase.getInstance().getReference().child("USUARIOS").child(user).child("HUERTOS").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final  ArrayList<Huerto> arrayListhuertos = new ArrayList<>();

                if(!dataSnapshot.exists()){
                    Toast.makeText(listar_mis_huertos.this, "NO TIENES HUERTOS REGISTRADOS",
                            Toast.LENGTH_SHORT).show();
                    arrayListhuertos.clear();
                    adaptador_huertos = new Adaptador_huertos(listar_mis_huertos.this, arrayListhuertos, user);
                    lista.setAdapter(adaptador_huertos);

                    buttonlink.setVisibility(View.VISIBLE);
                    buttonlink.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(listar_mis_huertos.this, crear_huerto.class);
                            intent.putExtra("USER" , user);
                            startActivity(intent);

                        }
                    });

                }
                else {

                    try {
                        for (DataSnapshot objSnapShot : dataSnapshot.getChildren()) {
                            Huerto huerto = objSnapShot.getValue(Huerto.class);
                            arrayListhuertos.add(huerto);
                            adaptador_huertos = new Adaptador_huertos(listar_mis_huertos.this, arrayListhuertos, user);
                            lista.setAdapter(adaptador_huertos);

                        }

                    } catch (Exception e) {

                        Toast.makeText(listar_mis_huertos.this, "ERROR: " + e,
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
