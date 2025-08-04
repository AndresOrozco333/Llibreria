package com.libreria.Llibreria.model;

public class Archivo {
    private String nombre;   // PDF
    private String tipo;     // Tipo MIME
    private String portada;  // Nombre de imagen (ej: "principito.jpg")

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }
}
