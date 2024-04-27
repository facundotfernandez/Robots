package org.example.modelo.unidades;

import static org.example.modelo.utilidades.Constantes.MULTIPLICADOR_PUNTAJE;

public class Robot extends Personaje {

    private int multiplicador;

    /**
     * Inicializa el Robot con multiplicador dado
     *
     * @param multiplicador
     */
    public Robot(int multiplicador) {
        super(multiplicador * MULTIPLICADOR_PUNTAJE);
        this.multiplicador = multiplicador;
    }

    /**
     * Inicializa al Robot con multiplicador, fila-columna dados
     *
     * @param multiplicador 
     *@param fila
     * @param columna
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
    *Destruye al robot, anulando su multiplicador
    */
    public void destruir() {
        this.multiplicador *= 0;
    }


}

