package org.robots.modelo.unidades;

import static org.robots.modelo.utilidades.Constantes.MULTIPLICADOR_PUNTAJE;

public class Robot extends Personaje {

    private int multiplicador;

    /**
     * Inicializa el Robot con multiplicador dado
     *
     * @param multiplicador Multiplicador del Robot
     */
    public Robot(int multiplicador) {
        super(multiplicador * MULTIPLICADOR_PUNTAJE);
        this.multiplicador = multiplicador;
    }

    /**
     * Inicializa al Robot con multiplicador, fila-columna dados
     *
     * @param multiplicador Multiplicador del Robot
     * @param fila          Fila inicial
     * @param columna       Columna Inicial
     */
    public Robot(int multiplicador, int fila, int columna) {
        super(multiplicador * MULTIPLICADOR_PUNTAJE, fila, columna);
        this.multiplicador = multiplicador;
    }

    /**
     * @return multiplicador
     */
    public int getMultiplicador() {
        return multiplicador;
    }

    /**
     * Destruye al robot, anulando su multiplicador
     */
    public void destruir() {
        this.multiplicador *= 0;
    }


}

