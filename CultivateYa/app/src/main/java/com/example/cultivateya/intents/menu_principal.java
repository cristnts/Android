package com.example.cultivateya.intents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cultivateya.R;

public class menu_principal extends AppCompatActivity {

    private Button button_crear_huerto,button;

    public static String USUARIO = "USER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        USUARIO = getIntent().getStringExtra("USER");



        button = findViewById(R.id.mis_huertos);
        button_crear_huerto = findViewById(R.id.crear_huerto);

        button_crear_huerto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menu_principal.this, crear_huerto.class);
                intent.putExtra("USER" , USUARIO);
                startActivity(intent);
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(menu_principal.this, listar_mis_huertos.class);
                intent.putExtra("USER" , USUARIO);
                startActivity(intent);
            }
        });

    }

}
