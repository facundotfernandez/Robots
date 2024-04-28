package org.example.modelo.unidades;

import org.example.modelo.utilidades.Punto;

public class Personaje {
    protected int puntaje;
    private Punto ubicacion;

    /**
     * Inicializa el Personaje con puntaje dado
     *
     * @param puntaje Puntaje acumulado
     */
    public Personaje(int puntaje) {
        this.puntaje = puntaje;
        this.ubicacion = null;
    }

    /**
     * Inicializa el Personaje con puntaje, fila-columna dados
     *
     * @param puntaje Puntaje acumulado
     * @param fila    Fila inicial
     * @param columna Columna inicial
     */
    public Personaje(int puntaje, int fila, int columna) {
        this.puntaje = puntaje;
        this.ubicacion = new Punto(fila, columna);
    }

    /**
     * @return puntaje actual del jugador
     */
    public int getPuntaje() {
        return puntaje;
    }

    /**
     * @return ubicacion del jugador
     */
    public Punto getUbicacion() {
        return ubicacion;
    }

    /**
     * modifica la ubicacion del juagdor segun fila-columna
     *
     * @param fila    Fila de destino
     * @param columna Columna de destino
     */
    public void mover(int fila, int columna) {
        this.ubicacion = new Punto(fila, columna);
    }

    /**
     * @return fila actual del juagdor
     */
    public int getFila() {
        return ubicacion.fila();
    }

    /**
     * @return columna actual del jugador
     */
    public int getColumna() {
        return ubicacion.columna();
    }
}
