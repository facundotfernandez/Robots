package org.example.modelo.jugabilidad;

import org.example.modelo.unidades.Robot;

public class ColisionRobotsException extends Exception {

    private final Robot robotOrigen;
    private final Robot robotDestino;

    public ColisionRobotsException(Robot robotOrigen, Robot robotDestino) {
        this.robotOrigen = robotOrigen;
        this.robotDestino = robotDestino;
    }

    public Robot getRobotOrigen() {
        return robotOrigen;
    }

    public Robot getRobotDestino() {
        return robotDestino;
    }
}
