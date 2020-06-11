package com.example.fruitworld.models;
// Clase fruita
public class Fruta {
    // atributs
    private  String Nombre;
    private String Origen;
    private  int icono;

    //constructors
 /*   public Fruta() {}
    public Fruta(String nombre, String origen) {
        Nombre = nombre;
        Origen = origen;

    }*/



    public Fruta(String nombre, String origen, int icono) {
        Nombre = nombre;
        Origen = origen;
        this.icono = icono;
    }

    // gets i sets

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getOrigen() {
        return Origen;
    }

    public void setOrigen(String origen) {
        Origen = origen;
    }

    public int getIcono() {
        return icono;
    }

    public void setIcono(int icono) {
        this.icono = icono;
    }
}
