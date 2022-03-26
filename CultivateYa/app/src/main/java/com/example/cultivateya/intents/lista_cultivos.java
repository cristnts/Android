package com.example.cultivateya.intents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cultivateya.R;
import com.example.cultivateya.adaptadores.Adaptador_cultivos;
import com.example.cultivateya.modelo.Cultivo;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class lista_cultivos extends AppCompatActivity {

    private ListView lista;
    private Adaptador_cultivos adaptador_cultivos;
    private TextView codhuerto;
    private Button button;
    private ImageView imageView;
    private View view;
    public static String user = "USER";
    public static String codhuertointent = "COD_HUERTO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cultivos);

        Bundle extras = getIntent().getExtras();
         user = extras.getString("USER");
         codhuertointent = extras.getString("COD_HUERTO");

        lista = findViewById(R.id.lista_cultivos);
        button = findViewById(R.id.button3);

       // codhuerto.setText(getIntent().getStringExtra("NOMBRE_HUERTO"));



        adaptador_cultivos = new Adaptador_cultivos(this, arrayListCultivos(), codhuertointent,user);
        lista.setAdapter(adaptador_cultivos);




      //  Bundle extras = new Bundle();
       // extras.putString("COD_HUERTO", getIntent().getStringExtra("COD_HUERTO"));
        // extras.putString("NOMBRE_HUERTO", getIntent().getStringExtra("NOMBRE_HUERTO"));
       // Intent intent = new Intent(lista_cultivos.this , Adaptador_cultivos.class);
      //  intent.putExtras(extras);
      //  startActivity(intent);
       // (lista_cultivos.this).getIntent().putExtras(extras);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //   Bundle extras = new Bundle();
              //   extras.putString("COD_HUERTO", getIntent().getStringExtra("COD_HUERTO"));
              //   extras.putString("NOMBRE_HUERTO", getIntent().getStringExtra("NOMBRE_HUERTO"));
                Intent intent = new Intent(lista_cultivos.this,listar_mis_cultivos.class);
                Bundle bundle = new Bundle();
                bundle.putString("USER", user);
                bundle.putString("COD_HUERTO",codhuertointent);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });




    }


    public ArrayList<Cultivo> arrayListCultivos(){
        ArrayList<Cultivo> arrayListcultivos = new ArrayList<>();

        arrayListcultivos.add(new Cultivo(R.drawable.captura,"5","manzana verde","Otoño", (float) 3.4,21,5,10,1,"El manzano se puede multiplicar por semilla, por injerto y también por estaca, aunque este último método no es recomendable.",0));

        arrayListcultivos.add(new Cultivo(R.drawable.platano,"4","platano","verano", (float) 4.5,25,7,9,(float)0.5,"Se cultivan los plátanos en las regiones tropicales donde la temperatura promedio es de 80° F (27° C) y la precipitación anual es entre 78 y 98 pulgadas",0));

        arrayListcultivos.add(new Cultivo(R.drawable.durazno,"2","durazno","verano", (float) 6.3,21,4,12,(float)0.5,"Se trata de un frutal de zona templada no muy resistente al frío, su área de cultivo se extiende entre 30 y 40º de latitud.\n",0));

        arrayListcultivos.add(new Cultivo(R.drawable.pera,"3","Pera","otño", (float) 7.1,2,21,6,(float)0.5,"Prospera bien en climas templados y algo húmedos, siendo más resistente al frío que al calor",0));


        return arrayListcultivos;
    }

}
