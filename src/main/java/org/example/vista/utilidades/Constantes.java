package org.example.vista.utilidades;

import javafx.scene.input.KeyCode;
import org.example.modelo.utilidades.Direccion;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.example.modelo.utilidades.Direccion.*;

public class Constantes {
    public static final String TITULO = "Robots";
    public static final String[] ETIQUETAS_BOTONES_JUEGO = {"Teleportación aleatoria", "Teleportación segura\nDisponibles:", "Esperar a los Robots"};
    public static final String[] ETIQUETAS_ESTADO_JUEGO = {"Nivel:", "Puntaje:"};
    public static final String[] ETIQUETAS_CONFIGURACION = {"DIFICULTAD", "FILAS", "COLUMNAS"};
    public static final String ETIQUETA_MENU = "Menú Principal";
    public static final int[] OPCIONES_FILAS = {10, 16, 20};
    public static final int[] OPCIONES_COLUMNAS = {25, 35, 45};
    public static final int ANCHO_VENTANA = 1440;
    public static final int ALTO_VENTANA = 832;
    public static final Map<String, Integer> DIFICULTADES = new LinkedHashMap<>();
    public static final Map<KeyCode, Direccion> CONTROLES = new LinkedHashMap<>();

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
}
