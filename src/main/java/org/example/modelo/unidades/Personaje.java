package org.example.modelo.unidades;

import org.example.modelo.tablero.Punto;

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
        int dx = ubicacion.x() + desplazamiento[1];
        int dy = ubicacion.y() + desplazamiento[0];
        this.ubicacion = new Punto(dy, dx);
    }

    public void mover(int fila, int columna) {
        this.ubicacion = new Punto(fila, columna);
    }
}
