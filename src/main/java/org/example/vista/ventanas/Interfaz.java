package org.example.vista.ventanas;

import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.modelo.jugabilidad.Juego;
import org.example.modelo.utilidades.Direccion;
import org.example.vista.componentes.Boton;

import java.util.Map;
import java.util.Objects;

import static org.example.vista.utilidades.Constantes.*;

public class Interfaz {

    private static Pane root;
    private static Stage escenario;
    private static Juego juego;


    /**
     * Inicializa la interfaz creando un menú principal
     */
    public Interfaz(Stage escenario) {
        Interfaz.escenario = escenario;
        iniciar(escenario);
    }

    /**
     * Se reemplaza el cursor actual por uno personalizado
     */
    public static void setCursor() {
        Image imagenCursor = new Image(Objects.requireNonNull(Interfaz.class.getResourceAsStream("/cursores/cursor.png")));
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
        Interfaz.escenario = new Stage(StageStyle.UNDECORATED);
        iniciarMenuPrincipal();
        escenario.show();
    }

    /**
     * Se configura la ventana para que se pueda mover
     */
    private static void setVentanaMovible() {
        root.setOnMousePressed(pressEvent -> root.setOnMouseDragged(dragEvent -> {
            escenario.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
            escenario.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
        }));
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
        crearAlertaJuegoPerdido(botonReiniciar, botonSalir);
    }

    /**
     * Se crea un alerta dado que el juego está finalizado. Se permite volver a jugar o salir
     *
     * @param botonReiniciar Botón para reiniciar el juego
     * @param botonSalir     Botón para salir del juego
     */
    private static void crearAlertaJuegoPerdido(Boton botonReiniciar, Boton botonSalir) {
        Alert alerta = new Alert(Alert.AlertType.NONE);
        alerta.initStyle(StageStyle.UNDECORATED);
        alerta.setHeaderText("¿Querés comenzar de nuevo?");

        ButtonBar barraBotones = (ButtonBar) alerta.getDialogPane().lookup(".button-bar");
        barraBotones.getButtons().addAll(botonReiniciar, botonSalir);

        botonReiniciar.setOnAction(_ -> {
            alerta.setResult(ButtonType.OK);
            alerta.close();
            reiniciarJuego();
        });

        botonSalir.setOnAction(_ -> {
            alerta.setResult(ButtonType.CANCEL);
            alerta.close();
            escenario.close();
        });

        alerta.showAndWait();
    }

    /**
     * Se crea un alerta para mostrar los controles
     *
     * @param botonCerrar      Botón para cerrar el alerta
     * @param buttonTypeCerrar Contiene la información necesaria para que botonCerrar cierre el alerta
     */
    private static void crearAlertaControles(Boton botonCerrar, ButtonType buttonTypeCerrar) {
        Alert alerta = new Alert(Alert.AlertType.NONE);
        alerta.initStyle(StageStyle.UNDECORATED);
        alerta.setHeaderText("Controles del Juego");

        ButtonBar barraBotones = (ButtonBar) alerta.getDialogPane().lookup(".button-bar");
        barraBotones.getButtons().add(botonCerrar);

        VBox controlesBox = new VBox(5);
        controlesBox.setAlignment(Pos.TOP_CENTER);

        for (Map.Entry<KeyCode, Direccion> tecla : CONTROLES.entrySet()) {
            String etiqueta = String.valueOf(tecla.getValue());
            controlesBox.getChildren().add(new Label(tecla.getKey() + " - " + (Objects.equals(etiqueta, "CENTRO") ? "ESPERAR" : etiqueta)));
        }
        for (Map.Entry<KeyCode, String> tecla : CONTROLES_BOTONES.entrySet()) {
            controlesBox.getChildren().add(new Label(tecla.getKey() + " - " + tecla.getValue()));
        }
        alerta.getDialogPane().setContent(controlesBox);

        botonCerrar.setOnAction(_ -> alerta.setResult(new ButtonType("CERRAR", ButtonBar.ButtonData.OK_DONE)));
        alerta.showAndWait().ifPresent(e -> {
            if (e == buttonTypeCerrar) alerta.close();
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

    private static void mostrarControles() {
        Boton botonCerrar = new Boton("CERRAR");
        ButtonType buttonTypeCerrar = new ButtonType("Salir", ButtonBar.ButtonData.OK_DONE);
        crearAlertaControles(botonCerrar, buttonTypeCerrar);
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
     * Crea una nueva VentanaJuego con el un nuevo estado de Nivel
     *
     * @param escenario Escenario principal
     */
    private void iniciar(Stage escenario) {
        iniciarMenuPrincipal();
        escenario.show();
        mostrarControles();
    }
}
