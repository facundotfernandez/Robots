package org.example.vista.ventanas;

import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.modelo.jugabilidad.Nivel;
import org.example.modelo.tablero.CeldaDesocupadaException;
import org.example.modelo.tablero.Tablero;
import org.example.modelo.unidades.Jugador;
import org.example.modelo.unidades.Personaje;
import org.example.modelo.unidades.Robot;
import org.example.modelo.utilidades.Direccion;
import org.example.vista.componentes.Boton;
import org.example.vista.contenedores.BarraDeTitulo;
import org.example.vista.contenedores.BloqueDeBotones;
import org.example.vista.contenedores.Encabezado;

import java.util.LinkedList;
import java.util.Objects;

import static org.example.modelo.utilidades.Direccion.CENTRO;
import static org.example.vista.utilidades.Constantes.*;

public class VentanaJuego extends VBox {
    private static final int DIMENSION_CELDA = 32; // En píxeles
    private final Tablero<Personaje> tablero;
    private final Jugador jugador;
    private final int filasTablero;
    private final int columnasTablero;
    private final int tpSeguros;
    private final Stage escenario;
    private GridPane seccionPrincipal;
    private int orientacionCursor;

    /**
     * Permite determinar que se espera que el usuario elija una celda
     */
    private boolean esperaEleccionCelda;

    /**
     * Inicializa los componentes de la Ventana
     *
     * @param escenario Escenario principal
     * @param nivel     Estado del Nivel actual
     */
    public VentanaJuego(Stage escenario, Nivel nivel) {
        this.escenario = escenario;
        this.tablero = nivel.getTablero();
        this.filasTablero = tablero.getFilas();
        this.columnasTablero = tablero.getColumnas();
        this.jugador = nivel.getJugador();
        this.tpSeguros = nivel.getTPSeguros();
        this.esperaEleccionCelda = false;
        inicializarComponentes(nivel.getId(), nivel.getPuntaje(), tpSeguros);
    }

    /**
     * Agrega una imagen en la posición del contenedor dado
     *
     * @param contenedor Contenedor en el que se debe insertar la imagen
     * @param ruta       Ruta relativo del archivo
     * @param x          Ubicación horizontal en píxeles
     * @param y          Ubicación vertical en píxeles
     */
    private void agregarImagenCelda(GridPane contenedor, String ruta, int x, int y) {
        ImageView imagen = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(ruta))));
        imagen.setFitHeight(DIMENSION_CELDA);
        imagen.setFitWidth(DIMENSION_CELDA);
        contenedor.add(imagen, x, y);
    }

    /**
     * @param personaje Personaje del juego
     * @return Nombre del personaje
     */
    private String getTipoOcupante(Personaje personaje) {
        if (personaje instanceof Jugador) return "jugador";
        else if (personaje instanceof Robot robot) return "robot-" + robot.getMultiplicador();
        else return "";
    }

    /**
     * Inicializa los componentes de la Ventana
     *
     * @param nivel     ID Nivel
     * @param puntaje   Puntaje acumulado
     * @param tpSeguros Cantidad de TPSeguros disponibles
     */
    private void inicializarComponentes(int nivel, int puntaje, int tpSeguros) {
        Encabezado header = new Encabezado(new String[]{ETIQUETAS_ESTADO_JUEGO[0] + " " + nivel, ETIQUETAS_ESTADO_JUEGO[1] + " " + puntaje});
        GridPane seccionPrincipal = crearTablero();
        BloqueDeBotones footer = crearBotonesAcciones(tpSeguros);

        this.seccionPrincipal = seccionPrincipal;
        this.getChildren().addAll(new BarraDeTitulo(escenario), header, seccionPrincipal, footer);
        configurarControladores();
        configurarEstilos();
    }


    /**
     * @return Panel generado con imágenes de Celdas, Robots o Jugador
     * según el ocupante de la posición
     */
    private GridPane crearTablero() {
        GridPane bloqueTablero = new GridPane();
        for (int fila = 0; fila < filasTablero; fila++) {
            for (int col = 0; col < columnasTablero; col++) {
                agregarImagenCelda(bloqueTablero, "/tablero/fondo_vacio.png", col, fila);
                try {
                    agregarImagenCelda(bloqueTablero, "/tablero/" + getTipoOcupante(tablero.getOcupante(fila, col)) + ".png", col, fila);
                } catch (CeldaDesocupadaException _) {
                }
            }
        }
        return bloqueTablero;
    }

    /**
     * Configuración de estilos básicos de la ventana
     */
    private void configurarEstilos() {
        VBox.setVgrow(seccionPrincipal, Priority.ALWAYS);
        seccionPrincipal.setAlignment(Pos.CENTER);
        seccionPrincipal.setStyle("-fx-background-color: #7D9598");
        setSpacing(0);
    }

    /**
     * @param event Evento del Mouse
     * @return Distancia entre la (Fila, Columna) actual y la posición Central
     * La posición central se define en base a la ubicación actual del jugador y
     * el corrimiento dado por el tamaño del tablero
     */
    private int[] calcularDistancia(MouseEvent event) {
        int[] ubicacion = getUbicacionElegida(event);
        int[] corrimiento = getCorrimiento();

        int fila = ubicacion[0];
        int col = ubicacion[1];
        int dY = corrimiento[0];
        int dX = corrimiento[1];

        int filaCentro = jugador.getFila() + dY;
        int colCentro = jugador.getColumna() + dX;

        return Direccion.calcularDistancia(filaCentro, colCentro, fila, col);
    }

    /**
     * Configura los controladores de la Ventana
     */
    private void configurarControladores() {
        configurarControladorJugarTurno();
        configurarControladorCambioCursor();
    }

    /**
     * Se reemplaza el cursor actual por uno orientado según la posición del cursor
     * con respecto a la posición del jugador
     */
    private void configurarControladorCambioCursor() {
        seccionPrincipal.setOnMouseMoved(event -> {
            int orientacion = Direccion.getDireccion(calcularDistancia(event));
            if (orientacion != orientacionCursor) {
                setCursor(orientacion);
                this.orientacionCursor = orientacion;
            }
        });
    }

    /**
     * @return (dY, dX) Corrimiento por tamaño de tablero en Y e X
     */
    private int[] getCorrimiento() {
        int dY = (int) ((seccionPrincipal.getHeight() - filasTablero * DIMENSION_CELDA) / (2 * DIMENSION_CELDA));
        int dX = (int) ((getWidth() - columnasTablero * DIMENSION_CELDA) / (2 * DIMENSION_CELDA));
        return new int[]{dY, dX};
    }

    /**
     * @param event Evento del Mouse
     * @return (Fila, Columna) en base a la ubicación del cursor
     */
    private int[] getUbicacionElegida(MouseEvent event) {
        int fila = (int) (event.getY() / DIMENSION_CELDA);
        int col = (int) (event.getX() / DIMENSION_CELDA);
        return new int[]{fila, col};
    }

    /**
     * @param fila Fila elegida
     * @param col  Columna elegida
     * @param dY   Corrimiento por tamaño de tablero en Y
     * @param dX   Corrimiento por tamaño de tablero en X
     * @return Verifica que la ubicación pertenezca al tablero
     */
    private boolean validarEstaDentroConCorrimiento(int fila, int col, int dY, int dX) {
        return (fila - dY) >= 0 && (fila - dY) < filasTablero && (col - dX) >= 0 && (col - dX) < columnasTablero;
    }

    /**
     * Configura los controladores que permiten determinar que un jugador quiere jugar un turno
     *
     * @throws RuntimeException Error inesperado
     */
    private void configurarControladorJugarTurno() throws RuntimeException {
        seccionPrincipal.setOnMouseClicked(event -> {
            try {
                if (esperaEleccionCelda) {
                    esperaEleccionCelda = false;

                    int[] ubicacion = getUbicacionElegida(event);
                    int[] corrimiento = getCorrimiento();

                    int fila = ubicacion[0];
                    int col = ubicacion[1];
                    int dY = corrimiento[0];
                    int dX = corrimiento[1];

                    if (validarEstaDentroConCorrimiento(fila, col, dY, dX)) Interfaz.usarTP(fila - dY, col - dX);
                } else Interfaz.jugarTurno(calcularDistancia(event));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        setOnKeyReleased(event -> {
            KeyCode tecla = event.getCode();
            esperaEleccionCelda = false;
            if (CONTROLES.containsKey(tecla)) {
                try {
                    Interfaz.jugarTurno(CONTROLES.get(tecla).getDireccion());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else if (CONTROLES_BOTONES.containsKey(tecla)) {
                if (CONTROLES_BOTONES.keySet().iterator().next() == tecla) Interfaz.usarTP();
                else if (tpSeguros > 0) esperaEleccionCelda = true;
            }
        });
    }

    /**
     * @param tpSeguros Cantidad de TPSeguros disponibles actualmente
     * @return Bloque de botones (TP, TPSeguro, Esperar) con sus controladores configurados
     */
    private BloqueDeBotones crearBotonesAcciones(int tpSeguros) {
        LinkedList<Boton> botones = new LinkedList<>();
        for (int i = 0; i < ETIQUETAS_BOTONES_JUEGO.length; i++) {
            Boton boton = new Boton(ETIQUETAS_BOTONES_JUEGO[i] + ((i == 1) ? (" " + tpSeguros) : ""));
            setControladorBoton(boton, i);
            botones.add(boton);
        }
        return new BloqueDeBotones(botones);
    }

    /**
     * Configura los controladores del BloqueDeBotones (TP, TPSeguro, Esperar)
     *
     * @param boton Boton de acción
     * @param i     Indice del Botón en el BloqueDeBotones
     */
    private void setControladorBoton(Boton boton, int i) {
        boton.setOnAction(_ -> {
            if (i == 0) Interfaz.usarTP();
            else if (i == 1) esperaEleccionCelda = true;
            else {
                try {
                    Interfaz.jugarTurno(CENTRO.getDireccion());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        if (i == 1 && tpSeguros == 0) boton.setDisable(true);
    }

    /**
     * Se reemplaza el cursor en el tablero, según la orientación actual
     *
     * @param tipo Orientación actual del cursor
     */
    private void setCursor(int tipo) {
        Image imagenCursor = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/cursores/cursor-" + tipo + ".png")));
        seccionPrincipal.setCursor(new ImageCursor(imagenCursor, 16, 16));
    }

}
