package org.robots.modelo.unidades;

public class Jugador extends Personaje {
    private int tpSeguros;

    /**
     * Inicializa el Jugador con con cantidad de tps seguros y su ubicacion en fila-columna
     *
     * @param tpSeguros TPSeguros disponibles inicialmente
     * @param fila      Fila de la ubicación del Jugador
     * @param columna   Columna de la ubicación del Jugador
     */
    public Jugador(int tpSeguros, int fila, int columna) {
        super(0, fila, columna);
        this.tpSeguros = tpSeguros;
    }

    /**
     * modifica los puntos a los acuales del personaje
     *
     * @param puntos Cantidad de puntos a agregar
     */
    public void addPuntos(int puntos) {
        this.puntaje += puntos;
    }

    /**
     * verifica si tiene tps seguros
     *
     * @throws IndexOutOfBoundsException Si el jugador no tiene más TPSeguros disponibles
     */
    private void tieneTPSeguros() throws IndexOutOfBoundsException {
        if (tpSeguros <= 0) throw new IndexOutOfBoundsException();
    }

    /**
     * si tiene, resta uno a la cantidad de tps seguros
     *
     * @throws IndexOutOfBoundsException Si el jugador no tiene más TPSeguros disponibles
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
