package org.example.modelo.utilidades;

public enum Direccion {
    ARRIBA(-1, 0), ABAJO(1, 0), IZQUIERDA(0, -1), DERECHA(0, 1), ARRIBA_IZQ(-1, -1), ARRIBA_DER(-1, 1), ABAJO_IZQ(1, -1), ABAJO_DER(1, 1);

    private final int[] direccion;

    Direccion(int fila, int columna) {
        this.direccion = new int[]{fila, columna};
    }

    public static Direccion getDireccion(int i) {
        return Direccion.values()[i];
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
}