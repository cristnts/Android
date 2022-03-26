package com.example.cultivateya.intents;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cultivateya.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class inicio_sesion extends AppCompatActivity {

    private EditText user,pass;
    private Button login,registro;
    private FirebaseAuth mAuth;
    public static String USUARIO = "USER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        user = findViewById(R.id.usuario);
        pass = findViewById(R.id.clave);
        mAuth = FirebaseAuth.getInstance();
        login = findViewById(R.id.button4);
        registro = findViewById(R.id.button5);

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inicio_sesion.this , registro.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(user.length() == 0 || pass.length() == 0){
                    Toast.makeText(inicio_sesion.this, "Authentication incorrect.",
                            Toast.LENGTH_SHORT).show();
                }else {


                    USUARIO = user.getText().toString().trim();
                    Log(USUARIO , pass.getText().toString().trim());

                }
            }
        });



    }


    private void Log(final String user, String pass){


        mAuth.signInWithEmailAndPassword(user, pass)
                .addOnCompleteListener(inicio_sesion.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(inicio_sesion.this, "Authentication correct.",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(inicio_sesion.this, menu_principal.class);
                            intent.putExtra("USER",USUARIO.replace("@", "1").replace(".", "2"));
                            startActivityForResult(intent, 0);

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(inicio_sesion.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

    }
}
