package org.robots.vista.utilidades;

import javafx.scene.input.KeyCode;
import org.robots.modelo.utilidades.Direccion;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.robots.modelo.utilidades.Direccion.*;

public class Constantes {

    /**
     * Información básica de las ventanas
     */
    public static final String TITULO = "Robots";
    public static final String ETIQUETA_MENU = "Menú Principal";

    /**
     * Información ventana del juego
     */
    public static final String[] ETIQUETAS_BOTONES_JUEGO = {"Teleportación aleatoria", "Teleportación segura\nDisponibles:", "Esperar a los Robots"};
    public static final String[] ETIQUETAS_ESTADO_JUEGO = {"Nivel:", "Puntaje:"};

    /**
     * Información elecciones del usuario en el Menú principal
     */
    public static final String[] ETIQUETAS_CONFIGURACION = {"DIFICULTAD", "FILAS", "COLUMNAS"};
    public static final int[] OPCIONES_FILAS = {10, 16, 20};
    public static final int[] OPCIONES_COLUMNAS = {25, 35, 45};
    public static final Map<String, Integer> DIFICULTADES = new LinkedHashMap<>();

    /**
     * Dimensión ventana
     */
    public static final int ANCHO_VENTANA = 1440;
    public static final int ALTO_VENTANA = 832;

    /**
     * Controles del Juego
     */
    public static final Map<KeyCode, Direccion> CONTROLES = new LinkedHashMap<>();
    public static final Map<KeyCode, String> CONTROLES_BOTONES = new LinkedHashMap<>();

    static {
        DIFICULTADES.put("Fácil", 2);
        DIFICULTADES.put("Normal", 3);
        DIFICULTADES.put("Difícil", 4);
    }

    static {
        CONTROLES.put(KeyCode.X, CENTRO);
        CONTROLES.put(KeyCode.E, ARRIBA_DER);
        CONTROLES.put(KeyCode.W, ARRIBA);
        CONTROLES.put(KeyCode.Q, ARRIBA_IZQ);
        CONTROLES.put(KeyCode.A, IZQUIERDA);
        CONTROLES.put(KeyCode.Z, ABAJO_IZQ);
        CONTROLES.put(KeyCode.S, ABAJO);
        CONTROLES.put(KeyCode.C, ABAJO_DER);
        CONTROLES.put(KeyCode.D, DERECHA);
    }

    static {
        CONTROLES_BOTONES.put(KeyCode.T, "TELEPORTACIÓN");
        CONTROLES_BOTONES.put(KeyCode.R, "TELEPORTACIÓN SEGURA");
    }
}
