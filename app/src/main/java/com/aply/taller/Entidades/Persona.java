package com.aply.taller.Entidades;

public class Persona {
    private String nombre;
    private String telefono;
    private String correo;
    private int imagenid;

    public Persona(){}

    public Persona(String nombre, String telefono, String correo, int imagenid) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.imagenid = imagenid;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public int getImagenid() {
        return imagenid;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setImagenid(int imagenid) {
        this.imagenid = imagenid;
    }
}
