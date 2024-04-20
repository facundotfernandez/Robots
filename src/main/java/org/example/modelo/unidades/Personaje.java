package org.example.modelo.unidades;

import org.example.modelo.utilidades.Punto;

public class Personaje {
    protected int puntaje;
    private Punto ubicacion;

    public Personaje(int puntaje) {
        this.puntaje = puntaje;
        this.ubicacion = null;
    }

    public Personaje(int puntaje, int fila, int columna) {
        this.puntaje = puntaje;
        this.ubicacion = new Punto(fila, columna);
    }

    public int getPuntaje() {
        return puntaje;
    }

    public Punto getUbicacion() {
        return ubicacion;
    }

    public void mover(int[] desplazamiento) {
        this.ubicacion = new Punto(ubicacion.fila() + desplazamiento[0], ubicacion.columna() + desplazamiento[1]);
    }

    public void mover(int fila, int columna) {
        this.ubicacion = new Punto(fila, columna);
    }

    public int getFila(){
        return ubicacion.fila();
    }
    public int getColumna(){
        return ubicacion.columna();
        }
}
