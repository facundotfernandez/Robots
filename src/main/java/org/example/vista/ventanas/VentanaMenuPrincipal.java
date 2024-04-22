package org.example.vista.ventanas;

import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.vista.RobotsApp;
import org.example.vista.componentes.BotonComenzar;
import org.example.vista.componentes.Eleccion;
import org.example.vista.contenedores.BarraDeTitulo;
import org.example.vista.contenedores.BloqueDeElecciones;
import org.example.vista.contenedores.Encabezado;

import java.util.Objects;

import static org.example.vista.utilidades.Constantes.*;

public class VentanaMenuPrincipal extends VBox {
    ComboBox<String> eleccionDificultades;
    ComboBox<Integer> eleccionFilas;
    ComboBox<Integer> eleccionColumnas;
    private Encabezado header;
    private VBox main;
    private BloqueDeElecciones footer;

    public VentanaMenuPrincipal(Stage escenario) {
        inicializarComponentes(escenario);
    }

    private void inicializarComponentes(Stage escenario) {
        this.footer = crearBloqueDeElecciones();
        this.header = new Encabezado(new String[]{ETIQUETA_MENU});
        this.main = new VBox();

        BotonComenzar botonComenzarJuego = new BotonComenzar();
        botonComenzarJuego.setOnAction(_ -> {
            if (eleccionDificultades.getValue() != null && eleccionFilas.getValue() != null && eleccionColumnas.getValue() != null) {
                int[] userData = new int[]{DIFICULTADES.get(eleccionDificultades.getValue()), eleccionFilas.getValue(), eleccionColumnas.getValue()};
                botonComenzarJuego.setUserData(userData);
                try {
                    RobotsApp.iniciarJuego(userData[0], userData[1], userData[2]);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });


        setImagenMenu(botonComenzarJuego);
        this.getChildren().addAll(new BarraDeTitulo(escenario), header, main, footer);
        configurarEstilos();
    }

    private void setImagenMenu(BotonComenzar botonComenzarJuego) {
        var bgMenu = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/tablero/menu_principal.png")));
        BackgroundImage bgImagen = new BackgroundImage(bgMenu, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1.0, 1.0, true, true, true, true));
        main.setBackground(new Background(bgImagen));
        main.getChildren().add(botonComenzarJuego);
    }

    private void configurarEstilos() {
        footer.setAlignment(Pos.CENTER);

        setVgrow(main, Priority.ALWAYS);
        main.setAlignment(Pos.CENTER);
        setStyle("-fx-background-color: #DCDAD3");
        setSpacing(0);

        for (Node comboBox : footer.getChildren()) {
            Image imagenCursor = new Image(Objects.requireNonNull(RobotsApp.class.getResourceAsStream("/cursores/cursor.png")));

            comboBox.setOnMouseEntered(event -> {
                comboBox.getScene().setCursor(new ImageCursor(imagenCursor));
                event.consume();
            });
            comboBox.setOnMouseExited(event -> {
                comboBox.setCursor(new ImageCursor(imagenCursor));
                event.consume();
            });
        }
    }

    private BloqueDeElecciones crearBloqueDeElecciones() {
        BloqueDeElecciones bloqueDeElecciones = new BloqueDeElecciones();

        Eleccion<String> eleccionDificultades = new Eleccion<>();
        eleccionDificultades.getItems().addAll(DIFICULTADES.keySet());

        Eleccion<Integer> eleccionFilas = new Eleccion<>();
        for (int opcion : OPCIONES_FILAS) eleccionFilas.getItems().add(opcion);

        Eleccion<Integer> eleccionColumnas = new Eleccion<>();
        for (int opcion : OPCIONES_COLUMNAS) eleccionColumnas.getItems().add(opcion);

        this.eleccionDificultades = eleccionDificultades;
        this.eleccionFilas = eleccionFilas;
        this.eleccionColumnas = eleccionColumnas;
        bloqueDeElecciones.agregarGrupo(ETIQUETAS_CONFIGURACION[0], eleccionDificultades);
        bloqueDeElecciones.agregarGrupo(ETIQUETAS_CONFIGURACION[1], eleccionFilas);
        bloqueDeElecciones.agregarGrupo(ETIQUETAS_CONFIGURACION[2], eleccionColumnas);
        return bloqueDeElecciones;
    }
}
