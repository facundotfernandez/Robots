package org.example.modelo.unidades;

import java.util.Random;

public class Jugador extends Personaje{
    private int puntos;
    private int teletrnsp_segura_disp;

    public Jugador(int fila, int columna) {
        super(fila, columna);
        this.puntos = 0;
        this.teletrnsp_segura_disp = 0;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getPuntos() {
        return puntos;
    }

    public boolean hay_Teletrnsp_segura_disp() {
        return (teletrnsp_segura_disp > 0);
    }

    public int getTeletrnsp_seguras() {
        return teletrnsp_segura_disp;
    }

    public void teletransportacion_aleaotira(int limite_filas, int limite_columnas){
        Random rnd = new Random();
        int columna = rnd.nextInt(limite_columnas);
        int fila = rnd.nextInt(limite_filas);
        setColumna(columna);
        setFila(fila);
    }
}
