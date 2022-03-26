package com.example.cultivateya.modelo;

import android.graphics.drawable.Drawable;

public class Cultivo {

    private int imagen;
    private String id_cultivo;
    private String nombre_cultivo;
    private String temporada;
    private float ph;
    private float temperatura;
    private float humedad;
    private int tiempo;
    private float profundidad;
    private String recomendacion;
    private int cantidad;

public Cultivo(){}

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getId_cultivo() {
        return id_cultivo;
    }

    public void setId_cultivo(String id_cultivo) {
        this.id_cultivo = id_cultivo;
    }

    public String getNombre_cultivo() {
        return nombre_cultivo;
    }

    public void setNombre_cultivo(String nombre_cultivo) {
        this.nombre_cultivo = nombre_cultivo;
    }

    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }

    public float getPh() {
        return ph;
    }

    public void setPh(float ph) {
        this.ph = ph;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public float getHumedad() {
        return humedad;
    }

    public void setHumedad(float humedad) {
        this.humedad = humedad;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public float getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(float profundidad) {
        this.profundidad = profundidad;
    }

    public String getRecomendacion() {
        return recomendacion;
    }

    public void setRecomendacion(String recomendacion) {
        this.recomendacion = recomendacion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Cultivo(int imagen, String id_cultivo, String nombre_cultivo, String temporada, float ph, float temperatura, float humedad, int tiempo, float profundidad, String recomendacion, int cantidad) {
        this.imagen = imagen;
        this.id_cultivo = id_cultivo;
        this.nombre_cultivo = nombre_cultivo;
        this.temporada = temporada;
        this.ph = ph;
        this.temperatura = temperatura;
        this.humedad = humedad;
        this.tiempo = tiempo;
        this.profundidad = profundidad;
        this.recomendacion = recomendacion;
        this.cantidad = cantidad;
    }
}