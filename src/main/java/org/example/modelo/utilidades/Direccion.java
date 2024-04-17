package org.example.modelo.utilidades;

import java.util.Arrays;

public enum Direccion {
    CENTRO(0, 0),
    ARRIBA_DER(-1, 1),
    ARRIBA(-1, 0),
    ARRIBA_IZQ(-1, -1),
    IZQUIERDA(0, -1),
    ABAJO_IZQ(1, -1),
    ABAJO(1, 0),
    ABAJO_DER(1, 1),
    DERECHA(0, 1);

    private final int[] direccion;

    Direccion(int fila, int columna) {
        this.direccion = new int[]{fila, columna};
    }

    public static Direccion getDireccion(int i) {
        return Direccion.values()[i];
    }

    public static int getDireccion(int[] desplazamiento) {
        for (Direccion direccion : Direccion.values()) {
            if (Arrays.equals(desplazamiento, direccion.getDireccion())) {
                return direccion.ordinal();
            }
        }
        return -1;
    }

    public int[] getDireccion() {
        return direccion;
    }

    public int getFila() {
        return direccion[0];
    }

    public int getColumna() {
        return direccion[1];
    }

    public int calcularDistancia(int y1, int x1, int y2, int x2) {
        int dy = y2 - y1;
        int dx = x2 - x1;

        return Direccion.getDireccion(new int[]{(dy == 0) ? 0 : (dy / Math.abs(dy)), (dx == 0) ? 0 : (dx / Math.abs(dx))});
    }
}