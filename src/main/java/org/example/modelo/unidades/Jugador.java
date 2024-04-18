package org.example.modelo.unidades;

public class Jugador extends Personaje {
    private final String nombre;
    private int tpSeguros;

    public Jugador(String nombre, int tps) {
        super(0);
        this.nombre = nombre;
        this.tpSeguros = tps;
    }

    public void addPuntos(int puntos) {
        this.puntaje += puntos;
    }

    private void tieneTPSeguros() {
        if (tpSeguros <= 0) throw new IndexOutOfBoundsException();
    }

    public void usarTPseguro() {
        tieneTPSeguros();
        this.tpSeguros -= 1;
    }

    public String getNombre() {
        return nombre;
    }

    public void addTPSeguro() {
        this.tpSeguros += 1;
    }
}
