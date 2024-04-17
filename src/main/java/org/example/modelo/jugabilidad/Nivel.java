package org.example.modelo.jugabilidad;

import org.example.modelo.tablero.Punto;
import org.example.modelo.tablero.Tablero;
import org.example.modelo.unidades.Personaje;
import org.example.modelo.unidades.Robot;

import java.util.LinkedList;
import java.util.Random;

public class Nivel {

    private final LinkedList<Robot> robots;
    private final int id;
    private final Tablero<Personaje> tablero;


    public Nivel(int id) {
        this.id = id;
        var cantRobots = id * 3;
        this.robots = createRobots(cantRobots);
        this.tablero = new Tablero<>(cantRobots * 2, cantRobots * 2);
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
            listado.add(new Robot(new Random().nextInt(2) + 1));
        }
        return listado;
    }

    public Tablero<Personaje> getTablero() {
        return tablero;
    }

    public void ubicar(Personaje ocupante) {
        tablero.ubicar(ocupante);
    }

    public void ubicar(Personaje ocupante, int fila, int columna) {
        tablero.ubicar(ocupante, fila, columna);
    }

    public void ubicarEnCentro(Personaje ocupante) {
        tablero.ubicarEnCentro(ocupante);
        ocupante.mover(tablero.getFilas() / 2, tablero.getColumnas() / 2);
    }

    public void mover(Punto ubicacion, int direccion) {
    }

    public void moverRobots(Punto ubicacion) {
    }
}
