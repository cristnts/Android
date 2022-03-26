package com.example.cultivateya.modelo;

public class Huerto {

    private int foto;
    private String id_huerto;
    private String nombre_huerto;
    private int medida;
    private float ph;
    private String TipoSuelo;
    private String Cultivos;

    public Huerto(){}

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getId_huerto() {
        return id_huerto;
    }

    public void setId_huerto(String id_huerto) {
        this.id_huerto = id_huerto;
    }

    public String getNombre_huerto() {
        return nombre_huerto;
    }

    public void setNombre_huerto(String nombre_huerto) {
        this.nombre_huerto = nombre_huerto;
    }

    public int getMedida() {
        return medida;
    }

    public void setMedida(int medida) {
        this.medida = medida;
    }

    public float getPh() {
        return ph;
    }

    public void setPh(float ph) {
        this.ph = ph;
    }

    public String getTipoSuelo() {
        return TipoSuelo;
    }

    public void setTipoSuelo(String tipoSuelo) {
        TipoSuelo = tipoSuelo;
    }

    public String getCultivos() {
        return Cultivos;
    }

    public void setCultivos(String cultivos) {
        Cultivos = cultivos;
    }

    public Huerto(int foto, String id_huerto, String nombre_huerto, int medida, float ph, String tipoSuelo, String cultivos) {
        this.foto = foto;
        this.id_huerto = id_huerto;
        this.nombre_huerto = nombre_huerto;
        this.medida = medida;
        this.ph = ph;
        TipoSuelo = tipoSuelo;
        Cultivos = cultivos;
    }
}
