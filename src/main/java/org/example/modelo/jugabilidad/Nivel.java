package org.example.modelo.jugabilidad;

import org.example.modelo.unidades.Robot;

import java.util.LinkedList;
import java.util.Random;

public class Nivel {

    private final LinkedList<Robot> robots;
    private final int id;

    public Nivel(int id) {
        this.id = id;
        this.robots = createRobots(id * 3);
    }

    public int getId() {
        return id;
    }

    public LinkedList<Robot> getRobots() {
        return robots;
    }

    public LinkedList<Robot> createRobots(int cantidad) {
        LinkedList<Robot> listado = new LinkedList<>();
        for (int i = 0; i < cantidad; i++) {
            listado.add(new Robot(new Random().nextInt(3)));
        }
        return listado;
    }
}
