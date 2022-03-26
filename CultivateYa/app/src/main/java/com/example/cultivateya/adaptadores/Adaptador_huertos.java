package com.example.cultivateya.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.cultivateya.R;
import com.example.cultivateya.intents.crear_huerto;
import com.example.cultivateya.intents.lista_cultivos;
import com.example.cultivateya.intents.listar_mis_cultivos;
import com.example.cultivateya.intents.listar_mis_huertos;
import com.example.cultivateya.modelo.Huerto;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class Adaptador_huertos extends BaseAdapter {

    private static LayoutInflater inflater = null;
    private Context context;
    private ArrayList<Huerto> ListItems;
    private String user;

    public Adaptador_huertos(Context context, ArrayList<Huerto> listItems, String user) {
        this.context = context;
        ListItems = listItems;

        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        this.user = user;
    }

    @Override
    public int getCount() {
        return ListItems.size();
    }

    @Override
    public Huerto getItem(int position) {
        return ListItems.get(position);
    }



    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final View vista = inflater.inflate(R.layout.formato_lista_huertos, null);

        final Huerto item = (Huerto) getItem(position);



        Button button = vista.findViewById(R.id.button2);
        Button button1 = vista.findViewById(R.id.button7);


        TextView nombre = vista.findViewById(R.id.name);
        nombre.setText(item.getNombre_huerto());
        TextView ph = vista.findViewById(R.id.acidez);
        ph.setText(Float.toString(item.getPh()));
        TextView tam = vista.findViewById(R.id.tamanio);
        tam.setText(Float.toString(R.id.tamanio));
        TextView tipo = vista.findViewById(R.id.tiposuelo);
        tipo.setText(item.getTipoSuelo());


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, listar_mis_cultivos.class);
                Bundle bundle = new Bundle();
                bundle.putString("USER", user);
                bundle.putString("COD_HUERTO",item.getId_huerto());
                intent.putExtras(bundle);
                context.startActivity(intent);

                }

        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase.getInstance().getReference().child("USUARIOS").child(user).child("HUERTOS").child(item.getId_huerto()).setValue(null);



            }
        });

        return vista;
    }
}
