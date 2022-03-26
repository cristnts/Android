package com.example.cultivateya.modelo;

public class Usuario {

        private String id_usuario;
        private String Correo;
        private String Clave;
        private int Img;

        public Usuario(){}

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String clave) {
        Clave = clave;
    }

    public int getImg() {
        return Img;
    }

    public void setImg(int img) {
        Img = img;
    }

    public Usuario(String id_usuario, String correo, String clave, int img) {
        this.id_usuario = id_usuario;
        Correo = correo;
        Clave = clave;
        Img = img;
    }
}
