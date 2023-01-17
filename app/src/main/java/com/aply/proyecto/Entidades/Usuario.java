package com.aply.proyecto.Entidades;

public class Usuario {
    int id;
    String nombre;
    String apellido;
    String telefono;
    String fechaNac;
    String email;
    String password;

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, String telefono, String fechaNac, String email, String password) {

        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.fechaNac = fechaNac;
        this.email = email;
        this.password = password;
    }

        public Usuario(int id, String nombre, String apellido, String telefono,  String fechaNac, String email, String password) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.fechaNac = fechaNac;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

