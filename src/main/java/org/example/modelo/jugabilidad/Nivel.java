package org.example.modelo.jugabilidad;

import org.example.modelo.tablero.CeldaDesocupadaException;
import org.example.modelo.tablero.CeldaInvalidaException;
import org.example.modelo.tablero.CeldaOcupadaException;
import org.example.modelo.tablero.Tablero;
import org.example.modelo.unidades.Jugador;
import org.example.modelo.unidades.Personaje;
import org.example.modelo.unidades.Robot;
import org.example.modelo.utilidades.Direccion;
import org.example.modelo.utilidades.Punto;

import java.util.LinkedList;
import java.util.Random;

import static org.example.modelo.utilidades.Constantes.INCENDIO;
import static org.example.modelo.utilidades.Constantes.TIPOS_DE_ROBOTS;

public class Nivel {
    private final int id;
    private final Jugador jugador;
    private final LinkedList<Robot> robots;
    private final Tablero<Personaje> tablero;

    public Nivel(int id, Jugador jugador, int dificultad, int filas, int columnas) {
        this.id = id;
        var cantRobots = id * dificultad;
        this.robots = crearRobots(cantRobots);
        this.tablero = new Tablero<>(filas, columnas);
        this.jugador = jugador;
    }

    public int getId() {
        return id;
    }

    public LinkedList<Robot> getRobots() {
        return robots;
    }

    public LinkedList<Robot> crearRobots(int cantidad) {
        LinkedList<Robot> listado = new LinkedList<>();
        for (int i = 0; i < cantidad; i++) {
            listado.add(new Robot(new Random().nextInt(TIPOS_DE_ROBOTS - 1) + 1));
        }
        return listado;
    }

    public Tablero<Personaje> getTablero() {
        return tablero;
    }

    public int getPuntaje() {
        return jugador.getPuntaje();
    }

    public void ubicar(Personaje ocupante) throws CeldaOcupadaException {
        var ubicacion = tablero.ubicar(ocupante);
        ocupante.mover(ubicacion[0], ubicacion[1]);
    }

    public void ubicar(Personaje ocupante, int fila, int columna) throws ColisionConJugadorException {
        try {
            tablero.mover(ocupante.getFila(), ocupante.getColumna(), fila, columna);
        } catch (CeldaOcupadaException e) {
            throw new ColisionConJugadorException();
        } catch (CeldaDesocupadaException e) {
            throw new RuntimeException(e);
        }
        ocupante.mover(fila, columna);
    }

    public void ubicarEnCentro(Personaje ocupante) throws CeldaOcupadaException {
        tablero.ubicarEnCentro(ocupante);
        ocupante.mover(tablero.getFilas() / 2, tablero.getColumnas() / 2);
    }

    private boolean esRobot(Personaje personaje) {
        return personaje.getClass() == Robot.class;
    }

    private boolean esJugador(Personaje personaje) {
        return personaje.getClass() == Jugador.class;
    }

    public void mover(Personaje personaje, int[] direccion) throws CeldaDesocupadaException, ColisionConJugadorException {
        try {
            tablero.mover(personaje.getFila(), personaje.getColumna(), direccion);
        } catch (CeldaOcupadaException e) {
            var ocupanteDestino = tablero.getOcupante(personaje.getFila() + direccion[0], personaje.getColumna() + direccion[1]);
            var filaDestino = ocupanteDestino.getFila();
            var columnaDestino = ocupanteDestino.getColumna();

            if ((esJugador(personaje) && esRobot(ocupanteDestino)) || (esJugador(ocupanteDestino) && esRobot(personaje))) {
                throw new ColisionConJugadorException();
            }

            if (esRobot(personaje) && esRobot(ocupanteDestino)) {
                var robotColision = new Robot(INCENDIO, filaDestino, columnaDestino);
                tablero.forzarOcupacion(personaje.getFila(), personaje.getColumna(), null);
                tablero.forzarOcupacion(filaDestino, columnaDestino, robotColision);
                robots.remove((Robot) personaje);
                robots.remove((Robot) ocupanteDestino);
                jugador.addPuntos(personaje.getPuntaje() + ocupanteDestino.getPuntaje());
            }
        }
    }

    public boolean hayRobots() {
        return !robots.isEmpty();
    }

    public void moverRobots(Punto ubicacion) throws CeldaDesocupadaException, ColisionConJugadorException {
        for (Robot robot : robots) {
            int[] direccion = Direccion.calcularDistancia(robot.getFila(), robot.getColumna(), ubicacion.fila(), ubicacion.columna());
            direccion[0] *= robot.getMultiplicador();
            direccion[1] *= robot.getMultiplicador();
            mover(robot, direccion);
        }
    }

    public void iniciar() throws CeldaOcupadaException {
        ubicarEnCentro(jugador);

        for (Robot robot : robots) {
            ubicar(robot);
        }
    }

    public void jugarTurno(int fila, int columna) throws CeldaDesocupadaException, ColisionConJugadorException {
        try {
            ubicar(jugador, fila, columna);
            moverRobots(jugador.getUbicacion());
        } catch (CeldaInvalidaException _) {
        }
    }

    public void jugarTurno(int[] direccion) throws CeldaDesocupadaException, ColisionConJugadorException {
        mover(jugador, direccion);
        jugador.mover(direccion);

        moverRobots(jugador.getUbicacion());
    }

    public int getFilas() {
        return tablero.getFilas();
    }

    public int getColumnas() {
        return tablero.getColumnas();
    }

    public int getTPSeguros() {
        return jugador.getTpSeguros();
    }
}