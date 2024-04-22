package org.example.vista.utilidades;

import java.util.LinkedHashMap;
import java.util.Map;

public class Constantes {
    public static final String TITULO = "Robots";
    public static final String[] ETIQUETAS_BOTONES_JUEGO = {"Teleportación aleatoria", "Teleportación segura\nDisponibles:", "Esperar a los Robots"};
    public static final String[] ETIQUETAS_ESTADO_JUEGO = {"Nivel:", "Puntaje:"};
    public static final String[] ETIQUETAS_CONFIGURACION = {"DIFICULTAD", "FILAS", "COLUMNAS"};
    public static final String ETIQUETA_MENU = "Menú Principal";
    public static final int[] OPCIONES_FILAS = {10, 15, 20};
    public static final int[] OPCIONES_COLUMNAS = {25, 35, 45};
    public static final int ANCHO_VENTANA = 1440;
    public static final int ALTO_VENTANA = 818;
    public static final Map<String, Integer> DIFICULTADES = new LinkedHashMap<>();

    static {
        DIFICULTADES.put("Fácil", 2);
        DIFICULTADES.put("Normal", 3);
        DIFICULTADES.put("Difícil", 4);
    }
}
