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

    /**
     * Devuelve el ID del nivel
     */
    public int getId() {
        return id;
    }

    /**
     * @return La lista<Robot> del Nivel
     */
    public LinkedList<Robot> getRobots() {
        return robots;
    }

    /**
     * crea una lista de robots de cantidad definida por parámetro
     *
     * @param cantidad
     * @return lista<Robots>
     */
    public LinkedList<Robot> crearRobots(int cantidad) {
        LinkedList<Robot> listado = new LinkedList<>();
        for (int i = 0; i < cantidad; i++) {
            listado.add(new Robot(new Random().nextInt(TIPOS_DE_ROBOTS - 1) + 1));
        }
        return listado;
    }

    /**
     *
     * @return Tablero<Personaje>
     */
    public Tablero<Personaje> getTablero() {
        return tablero;
    }

    /**
     *
     * @return el puntaje acumulado por el juaador
     */
    public int getPuntaje() {
        return jugador.getPuntaje();
    }

    /**
     * Ubica el personaje en una celda aleatoria mediante "tablero.ubicar"
     * @param ocupante
     * @throws CeldaOcupadaException
     */
    public void ubicar(Personaje ocupante) throws CeldaOcupadaException {
        var ubicacion = tablero.ubicar(ocupante);
        ocupante.mover(ubicacion[0], ubicacion[1]);
    }

    /**
     * Ubica el personaje en una fila-columna mediante "tablero.mover", pudiendo o no colisionar
     * @param ocupante
     * @throws ColisionConJugadorException
     * @throws CeldaOcupadaException
     */
    public void ubicar(Personaje ocupante, int fila, int columna) throws ColisionConJugadorException, CeldaDesocupadaException, CeldaOcupadaException {
        tablero.mover(ocupante.getFila(), ocupante.getColumna(), fila, columna);
        ocupante.mover(fila, columna);
    }
    /**
     * Ubica el personaje en la celda central mediante "tablero.ubicarEnCentro"
     * @param ocupante
     * @throws CeldaOcupadaException
     */
    public void ubicarEnCentro(Personaje ocupante) throws CeldaOcupadaException {
        tablero.ubicarEnCentro(ocupante);
        ocupante.mover(getFilas() / 2, getColumnas() / 2);
    }

    /**
     * verifica si Personaje se trata de un robot
     * @param personaje
     * @return True en el caso que sea Robot, False en caso contrario
     */
    private boolean esRobot(Personaje personaje) {
        return personaje.getClass() == Robot.class;
    }

    /**
     * verifica si Personaje se trata de un jugador
     * @param personaje
     * @return True en el caso que sea Jugador, False en caso contrario
     */
    private boolean esJugador(Personaje personaje) {
        return personaje.getClass() == Jugador.class;
    }

    /**
     * intenta mover al Personaje en la Direccion indicada; si ocurre una colision robot-robot o personaje-robot se maneja de manera correspondiente
     * @param personaje
     * @param direccion
     * @throws CeldaDesocupadaException
     * @throws ColisionConJugadorException
     * @throws CeldaOcupadaException
     */
    public void mover(Personaje personaje, int[] direccion) throws CeldaDesocupadaException, ColisionConJugadorException, CeldaOcupadaException {
        try {
            tablero.mover(personaje.getFila(), personaje.getColumna(), direccion);
            personaje.mover(personaje.getFila() + direccion[0], personaje.getColumna() + direccion[1]);
        } catch (CeldaOcupadaException e) {
            var ocupanteDestino = tablero.getOcupante(personaje.getFila() + direccion[0], personaje.getColumna() + direccion[1]);
            var filaDestino = ocupanteDestino.getFila();
            var columnaDestino = ocupanteDestino.getColumna();

            if ((esJugador(personaje) && esRobot(ocupanteDestino)) || (esJugador(ocupanteDestino) && esRobot(personaje))) {
                throw new ColisionConJugadorException();
            }

            if (esRobot(personaje) && esRobot(ocupanteDestino)) {
                tablero.forzarOcupacion(personaje.getFila(), personaje.getColumna(), null);
                tablero.forzarOcupacion(filaDestino, columnaDestino, new Robot(INCENDIO, filaDestino, columnaDestino));
                robots.remove((Robot) personaje);
                if (ocupanteDestino.getPuntaje() != 0) robots.remove((Robot) ocupanteDestino);
                jugador.addPuntos(personaje.getPuntaje() + ocupanteDestino.getPuntaje());
            }
        }
    }

    /**
     * verifica si quedan robots en el nivel
     * @return True si no hay Robots, False caso contrario
     */
    public boolean noHayRobots() {
        return robots.isEmpty();
    }

    /**
     * Mueve a los robots en funcion del movimiento del jugador, manjenado todos los casos borde
     * @param ubicacion
     * @throws CeldaDesocupadaException
     * @throws ColisionConJugadorException
     * @throws CeldaOcupadaException
     */
    public void moverRobots(Punto ubicacion) throws CeldaDesocupadaException, ColisionConJugadorException, CeldaOcupadaException {
        //var robotsVivos = new LinkedList<Robot>();
        for (Robot robot : robots) {
            int[] direccion = Direccion.calcularDistancia(robot.getFila(), robot.getColumna(), ubicacion.fila(), ubicacion.columna());
            direccion[0] *= robot.getMultiplicador();
            direccion[1] *= robot.getMultiplicador();
            mover(robot, direccion);
        }
    }

    /**
     * inicia a los PErsonajes en el tablero; al Jugador en el centro, y a los robots en sus celdas
     * @throws CeldaOcupadaException
     */
    public void iniciar() throws CeldaOcupadaException {
        ubicarEnCentro(jugador);

        for (Robot robot : robots) {
            ubicar(robot);
        }
    }

    /**
     * intenta ubicar al jugador en la fila-columna y mueve a los robots en funcion del mismo; manejando las excepciones
     * @param fila
     * @param columna
     * @throws CeldaDesocupadaException
     * @throws ColisionConJugadorException
     */
    public void jugarTurno(int fila, int columna) throws CeldaDesocupadaException, ColisionConJugadorException {
        try {
            ubicar(jugador, fila, columna);
            jugador.mover(fila, columna);
            moverRobots(jugador.getUbicacion());
        } catch (CeldaInvalidaException _) {
        } catch (CeldaOcupadaException _) {
            throw new ColisionConJugadorException();
        }
    }

    public void jugarTurno(int[] direccion) throws CeldaDesocupadaException, ColisionConJugadorException, CeldaOcupadaException {
        mover(jugador, direccion);
        moverRobots(jugador.getUbicacion());
    }

    /**
     *
     * @return filas del tablero
     */
    public int getFilas() {
        return tablero.getFilas();
    }

    /**
     *
     * @return columnas del tablero
     */
    public int getColumnas() {
        return tablero.getColumnas();
    }
    /**
     *
     * @return tpsSeguros del jugador
     */
    public int getTPSeguros() {
        return jugador.getTpSeguros();
    }

    /**
     *
     * @return jugador
     */
    public Jugador getJugador() {
        return jugador;
    }
}