package com.example.cultivateya.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cultivateya.R;
import com.example.cultivateya.modelo.Cultivo;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Adaptador_cultivos extends BaseAdapter {

    private static LayoutInflater inflater = null;
    private Context context;
    private ArrayList<Cultivo> ListItems;
    private String cod_huerto;
    private String cod_user;


    public Adaptador_cultivos(Context context, ArrayList<Cultivo> listItems, String cod_huerto, String cod_user) {
        this.context = context;
        ListItems = listItems;

        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        this.cod_huerto = cod_huerto;

        this.cod_user = cod_user;
    }

    @Override
    public int getCount() {
        return ListItems.size();
    }

    @Override
    public Object getItem(int position) {
        return ListItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final View vista = inflater.inflate(R.layout.formato_lista_cultivos, null);

        final Cultivo item =(Cultivo) getItem(position);

        final Button button = vista.findViewById(R.id.button2);

        ImageView imageView = vista.findViewById(R.id.imagen);
        imageView.setImageResource(item.getImagen());
        TextView nombre = vista.findViewById(R.id.name);
        nombre.setText(item.getNombre_cultivo());
        TextView tempora = vista.findViewById(R.id.tamanio);
        tempora.setText(item.getTemporada());
        TextView ph = vista.findViewById(R.id.acidez);
        ph.setText(Float.toString(item.getPh()));
        TextView tempe = vista.findViewById(R.id.tiposuelo);
        tempe.setText(Float.toString(item.getTemperatura()));
        TextView hum = vista.findViewById(R.id.humedad);
        hum.setText(Float.toString(item.getHumedad()));
        TextView time = vista.findViewById(R.id.tiempo);
        time.setText(Integer.toString(item.getTiempo()));
        TextView prof = vista.findViewById(R.id.profundidad);
        prof.setText(Float.toString(item.getProfundidad()));
        TextView rec = vista.findViewById(R.id.reco);
        rec.setText(item.getRecomendacion());
        final TextView textView = vista.findViewById(R.id.textView8);

        textView.setText(Integer.toString(item.getCantidad()));

        item.getImagen();
        item.getNombre_cultivo();
        item.getTemporada();
        item.getPh();
        item.getTemperatura();
        item.getHumedad();
        item.getTiempo();
        item.getProfundidad();
        item.getRecomendacion();


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              item.setCantidad(item.getCantidad()+1);
                if(item.getCantidad()>0) {
                    FirebaseDatabase.getInstance().getReference().child("USUARIOS").child(cod_user).child("HUERTOS").child(cod_huerto).child("CULTIVOS").child(item.getNombre_cultivo()).setValue(item);

                    textView.setVisibility(View.VISIBLE);

                }

                textView.setText(Integer.toString(item.getCantidad()));


            }

        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                item.setCantidad(item.getCantidad()-1);

                textView.setText(Integer.toString(item.getCantidad()));
                if(item.getCantidad()<=0) {
                    FirebaseDatabase.getInstance().getReference().child("USUARIOS").child(cod_user).child("HUERTOS").child(cod_huerto).child("CULTIVOS").child(item.getNombre_cultivo()).setValue(null);
                    textView.setVisibility(View.INVISIBLE);
                }else{
                    FirebaseDatabase.getInstance().getReference().child("USUARIOS").child(cod_user).child("HUERTOS").child(cod_huerto).child("CULTIVOS").child(item.getNombre_cultivo()).setValue(item);
                }
            }
        });



        return vista;
    }
}
