package org.example.vista;

import javafx.application.Application;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.modelo.jugabilidad.Juego;
import org.example.vista.componentes.Boton;
import org.example.vista.ventanas.VentanaJuego;
import org.example.vista.ventanas.VentanaMenuPrincipal;

import java.util.Objects;
import java.util.Optional;

import static org.example.vista.utilidades.Constantes.ALTO_VENTANA;
import static org.example.vista.utilidades.Constantes.ANCHO_VENTANA;


public class RobotsApp extends Application {

    private static Pane root;
    private static Stage escenario;
    private static Juego juego;

    /**
     * Se lanza la aplicación
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Se reemplaza el cursor actual por uno personalizado
     */
    public static void setCursor() {
        Image imagenCursor = new Image(Objects.requireNonNull(RobotsApp.class.getResourceAsStream("/cursores/cursor.png")));
        escenario.getScene().setCursor(new ImageCursor(imagenCursor));
    }

    /**
     * Se crea una nueva instancia de Juego según las elecciones del jugador
     * Se crea una nueva escena con una nueva instancia de VentanaJuego
     *
     * @param dificultad Dificultad elegida por el jugador
     * @param filas      Cantidad de filas elegida por el jugador
     * @param columnas   Cantidad de columnas elegida por el jugador
     * @throws Exception Error inesperado
     */
    public static void iniciarJuego(int dificultad, int filas, int columnas) throws Exception {
        juego = new Juego(dificultad, filas, columnas);
        root = new VentanaJuego(escenario, juego.getNivel());
        escenario.setScene(new Scene(root, ANCHO_VENTANA, ALTO_VENTANA));
        configurarVentana();
    }

    /**
     * Configuración de estilos básicos de la ventana
     */
    private static void configurarVentana() {
        setCursor();
        setVentanaMovible();
    }

    /**
     * Cierra el escenario actual y crea uno nuevo volviendo al menú principal
     */
    private static void reiniciarJuego() {
        var escenarioAnterior = escenario;
        escenarioAnterior.close();
        RobotsApp.escenario = new Stage(StageStyle.UNDECORATED);
        iniciarMenuPrincipal();
        escenario.show();
    }

    /**
     * Se configura la ventana para que se pueda mover
     */
    private static void setVentanaMovible() {
        root.setOnMousePressed(pressEvent -> {
            root.setOnMouseDragged(dragEvent -> {
                escenario.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                escenario.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
            });
        });
    }

    /**
     * Se juega un turno en base a la dirección de movimiento del jugador
     *
     * @param direccion Dirección de movimiento del jugador
     * @throws Exception Error inesperado
     */
    public static void jugarTurno(int[] direccion) throws Exception {
        juego.jugarTurno(direccion);
        actualizarPantalla();
    }

    /**
     * Se crea un alerta dado que el juego está finalizado. Se permite volver a jugar o salir
     */
    private static void mostrarJuegoPerdido() {
        Boton botonSalir = new Boton("SALIR");
        Boton botonReiniciar = new Boton("REINICIAR");

        ButtonType buttonTypeReiniciar = new ButtonType("REINICIAR", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeSalir = new ButtonType("SALIR", ButtonBar.ButtonData.NO);

        crearAlerta(botonReiniciar, buttonTypeReiniciar, botonSalir, buttonTypeSalir);
    }

    /**
     * Se crea un alerta dado que el juego está finalizado. Se permite volver a jugar o salir
     *
     * @param botonReiniciar      Botón para reiniciar el juego
     * @param buttonTypeReiniciar Contiene la información necesaria para que botonReiniciar ejecute un reinicio
     * @param botonSalir          Botón para salir del juego
     * @param buttonTypeSalir     Contiene la información necesaria para que botonReiniciar ejecute la salida
     */
    private static void crearAlerta(Boton botonReiniciar, ButtonType buttonTypeReiniciar, Boton botonSalir, ButtonType buttonTypeSalir) {
        Alert alerta = new Alert(Alert.AlertType.NONE);
        alerta.initStyle(StageStyle.UNDECORATED);
        alerta.setHeaderText("¿Querés comenzar de nuevo?");

        ButtonBar barraBotones = (ButtonBar) alerta.getDialogPane().lookup(".button-bar");
        barraBotones.getButtons().addAll(botonReiniciar, botonSalir);

        botonReiniciar.setOnAction(_ -> {
            alerta.setResult(buttonTypeReiniciar);
            alerta.close();
        });

        botonSalir.setOnAction(_ -> {
            alerta.setResult(buttonTypeSalir);
            alerta.close();
        });

        Optional<ButtonType> result = alerta.showAndWait();
        result.ifPresent(buttonType -> {
            if (buttonType == buttonTypeSalir) {
                escenario.close();
            } else if (buttonType == buttonTypeReiniciar) {
                RobotsApp.reiniciarJuego();
            }
        });
    }

    /**
     * Se inicia una nueva escena de menú principal
     */
    public static void iniciarMenuPrincipal() {
        root = new VentanaMenuPrincipal(escenario);
        escenario.setScene(new Scene(root, ANCHO_VENTANA, ALTO_VENTANA));
        configurarEstilos();
    }

    /**
     * Configuración de estilos básicos del escenario y la ventana
     */
    private static void configurarEstilos() {
        escenario.setResizable(false);
        escenario.initStyle(StageStyle.UNDECORATED);
        configurarVentana();
    }

    /**
     * Se juega un turno según un TP a una ubicación aleatoria
     */
    public static void usarTP() {
        try {
            juego.usarTP();
            actualizarPantalla();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Se juega un turno de TP Seguro en base a la elección del jugador
     *
     * @param fila Fila elegida por el jugador en TP Seguro
     * @param col  Columna elegida por el jugador en TP Seguro
     */
    public static void usarTP(int fila, int col) {
        try {
            juego.usarTPSeguro(fila, col);
            actualizarPantalla();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Crea una nueva VentanaJuego con el un nuevo estado de Nivel
     * Si el juego finalizó, muestra el estado de JuegoPerdido
     */
    private static void actualizarPantalla() {
        root = new VentanaJuego(escenario, juego.getNivel());
        escenario.setScene(new Scene(root, ANCHO_VENTANA, ALTO_VENTANA));
        configurarVentana();
        if (!juego.estaEnJuego()) mostrarJuegoPerdido();
    }

    /**
     * Inicia el menú principal y hace visible el escenario
     *
     * @param escenario Escenario principal
     */
    @Override
    public void start(Stage escenario) {
        RobotsApp.escenario = escenario;
        iniciarMenuPrincipal();
        escenario.show();
    }

}