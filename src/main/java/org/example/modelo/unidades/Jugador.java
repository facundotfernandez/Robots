package org.example.modelo.unidades;

public class Jugador extends Personaje {
    private int tpSeguros;

    public Jugador(int tpSeguros) {
        super(0);
        this.tpSeguros = tpSeguros;
    }

    /**
     * modifica los puntos a los acuales del personaje
     * @param puntos
     */
    public void addPuntos(int puntos) {
        this.puntaje += puntos;
    }

    /**
     * verifica si tiene tps seguros
     * @throws IndexOutOfBoundsException
     */
    private void tieneTPSeguros() throws IndexOutOfBoundsException{
        if (tpSeguros <= 0) throw new IndexOutOfBoundsException();
    }

    /**
     * si tiene, resta uno a la cantidad de tps seguros
     * @throws IndexOutOfBoundsException
     */
    public void usarTPseguro() throws IndexOutOfBoundsException{
        tieneTPSeguros();
        this.tpSeguros -= 1;
    }

    /**
     * agrega uno a la cantidad de tps sguros
     */
    public void addTPSeguro() {
        this.tpSeguros += 1;
    }

    /**
     *
     * @return tps seguros
     */
    public int getTpSeguros() {
        return tpSeguros;
    }
}
