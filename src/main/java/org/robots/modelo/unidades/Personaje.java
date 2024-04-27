package org.example.modelo.unidades;

import org.example.modelo.utilidades.Punto;

public class Personaje {
    protected int puntaje;
    private Punto ubicacion;

    /**
    * Inicializa el Perosnaje con puntaje dado
    *@param puntaje
    */
    public Personaje(int puntaje) {
        this.puntaje = puntaje;
        this.ubicacion = null;
    }

    /**
    * Inicializa el Personaje con puntaje, fila-columna dados
    *@param puntaje
    *@param fila
    @param columna
    */
    public Personaje(int puntaje, int fila, int columna) {
        this.puntaje = puntaje;
        this.ubicacion = new Punto(fila, columna);
    }

    /**
     *
     * @return puntaje actual del jugador
     */
    public int getPuntaje() {
        return puntaje;
    }

    /**
     *
     * @return ubicacion del jugador
     */
    public Punto getUbicacion() {
        return ubicacion;
    }

    /**
     * modifica la ubicacion del juagdor
     * @param desplazamiento
     */
    public void mover(int[] desplazamiento) {
        this.ubicacion = new Punto(ubicacion.fila() + desplazamiento[0], ubicacion.columna() + desplazamiento[1]);
    }

    /**
     * modifica la ubicacion del juagdor segun fila-columna
     * @param fila
     * @param columna
     */
    public void mover(int fila, int columna) {
        this.ubicacion = new Punto(fila, columna);
    }

    /**
     *
     * @return fila actual del juagdor
     */
    public int getFila(){
        return ubicacion.fila();
    }

    /**
     *
     * @return columna actual del jugador
     */
    public int getColumna(){
        return ubicacion.columna();
        }
}
