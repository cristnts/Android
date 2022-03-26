package com.example.cultivateya.intents;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cultivateya.R;
import com.example.cultivateya.modelo.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class registro extends AppCompatActivity {

    TextView user, password, password2;
    Button button;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        user = findViewById(R.id.usuario);
        password = findViewById(R.id.clave);
        password2 = findViewById(R.id.clave2);

        button = findViewById(R.id.registrar);
        firebaseAuth = FirebaseAuth.getInstance();



            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(password2.getText().toString().equals(password.getText().toString()) && !password2.getText().toString().isEmpty() && !password.getText().toString().isEmpty() && !user.getText().toString().isEmpty()){

                        try {

                            registrar(user.getText().toString().trim() , password2.getText().toString().trim());
                            Usuario u = new Usuario();
                            u.setCorreo(user.getText().toString().replace("@","1").replace(".","2"));
                            u.setClave(password2.getText().toString());

                            FirebaseDatabase.getInstance().getReference().child("USUARIOS").child(u.getCorreo()).setValue(u);
                            Intent intent = new Intent(registro.this, inicio_sesion.class);
                            startActivityForResult(intent, 0);

                        }catch (Exception e){

                            Toast.makeText(registro.this, "ERROR" + e ,  Toast.LENGTH_SHORT).show();
                                  }
                    }
                    else{
                        Toast.makeText(registro.this, "Formulario invalido", Toast.LENGTH_SHORT).show();
                    }

                }
            });



    }

    private void registrar(final String user, final String password){


        firebaseAuth.createUserWithEmailAndPassword(user,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(registro.this, "El usuario se registro con éxito", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(registro.this, "El usuario no se registro.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



}