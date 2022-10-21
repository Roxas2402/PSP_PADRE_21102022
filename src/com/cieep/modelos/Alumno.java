package com.cieep.modelos;

public class Alumno {
    private String nombre, apellidos, dni;

    @Override
    public String toFile() {
        return nombre + ";" + apellidos + ";" + dni;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", dni='" + dni + '\'' +
                '}';
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Alumno() {
    }

    public Alumno(String nombre, String apellidos, String dni) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
    }

    public Alumno(String alumnoCodificado) {
        this.nombre = alumnoCodificado.split(";") [0];
        this.apellidos = alumnoCodificado.split(";") [1];
        this.dni = alumnoCodificado.split(";") [2];
    }

}
