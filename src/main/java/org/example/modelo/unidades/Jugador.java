package org.example.modelo.unidades;

public class Jugador extends Personaje {
    private int tpSeguros;

    public Jugador(int tpSeguros) {
        super(0);
        this.tpSeguros = tpSeguros;
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

    public void addTPSeguro() {
        this.tpSeguros += 1;
    }
}
