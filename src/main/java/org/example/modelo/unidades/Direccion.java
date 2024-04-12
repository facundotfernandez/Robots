package org.example.modelo.unidades;

public enum Direccion {
    ARRIBA(new int[]{-1, 0}),
    ABAJO(new int[]{1, 0}),
    IZQUIERDA(new int[]{0, -1}),
    DERECHA(new int[]{0, 1}),
    ARRIBA_IZQ(new int[]{-1, -1}),
    ARRIBA_DER(new int[]{-1, 1}),
    ABAJO_IZQ(new int[]{1, -1}),
    ABAJO_DER(new int[]{1, 1});

    private final int[] cambio;

    Direccion(int[] cambio) {
        this.cambio = cambio;
    }

    public int[] getCambio() {
        return cambio;
    }
}