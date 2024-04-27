package org.robots.modelo.unidades;

public class Jugador extends Personaje {
    private int tpSeguros;

    /**
    * Inicializa el Jugador con con cantidad de tps seguros y su ubicacion en fila-columna
    *@param tpSeguros
    *@param fila
    *@param columna
    */
    public Jugador(int tpSeguros, int fila, int columna) {
        super(0, fila, columna);
        this.tpSeguros = tpSeguros;
    }

    /**
     * modifica los puntos a los acuales del personaje
     *
     * @param puntos
     */
    public void addPuntos(int puntos) {
        this.puntaje += puntos;
    }

    /**
     * verifica si tiene tps seguros
     *
     * @throws IndexOutOfBoundsException
     */
    private void tieneTPSeguros() throws IndexOutOfBoundsException {
        if (tpSeguros <= 0) throw new IndexOutOfBoundsException();
    }

    /**
     * si tiene, resta uno a la cantidad de tps seguros
     *
     * @throws IndexOutOfBoundsException
     */
    public void usarTPseguro() throws IndexOutOfBoundsException {
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
     * @return tps seguros
     */
    public int getTpSeguros() {
        return tpSeguros;
    }
}
