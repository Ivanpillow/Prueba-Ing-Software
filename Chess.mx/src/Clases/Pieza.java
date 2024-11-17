/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author luigi
 */
public class Pieza {
    private String tipo;
    private String color; 
    private String imagenPath; 
    
    public Pieza(String tipo, String color, String imagenPath) {
        this.tipo = tipo;
        this.color = color;
        this.imagenPath = imagenPath;
    }

    public String getTipo() {
        return tipo;
    }

    public String getColor() {
        return color;
    }

    public String getImagenPath() {
        return imagenPath;
    }
}
