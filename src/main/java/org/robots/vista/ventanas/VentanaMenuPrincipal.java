package org.example.vista.ventanas;

import javafx.geometry.Pos;
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
    private final VBox main;
    private final BloqueDeElecciones footer;
    ComboBox<String> eleccionDificultades;
    ComboBox<Integer> eleccionFilas;
    ComboBox<Integer> eleccionColumnas;

    /**
     * Inicializa los componentes de la Ventana
     *
     * @param escenario Escenario principal
     */
    public VentanaMenuPrincipal(Stage escenario) {
        this.main = crearSeccionPrincipal();
        this.footer = crearBloqueDeElecciones();
        this.getChildren().addAll(new BarraDeTitulo(escenario), new Encabezado(new String[]{ETIQUETA_MENU}), main, footer);
        configurarEstilos();
    }

    /**
     * @return Seccion principal con el botonComenzarJuego y la imágen principal configurados
     */
    private VBox crearSeccionPrincipal() {
        var seccionPrincipal = new VBox();

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

        setImagenMenu(seccionPrincipal, botonComenzarJuego);
        return seccionPrincipal;
    }

    /**
     * Se inserta una imagen en la sección principal con el botonComenzarJuego en el centro
     *
     * @param seccion            Sección de la ventana en la que se inserta la imágen y el botonComenzarJuego
     * @param botonComenzarJuego Boton que permite comenzar el juego y contiene las
     *                           elecciones (Dificultad, filas, columnas) del usuario
     */
    private void setImagenMenu(Pane seccion, BotonComenzar botonComenzarJuego) {
        var bgMenu = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/tablero/menu_principal.png")));
        BackgroundImage bgImagen = new BackgroundImage(bgMenu, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1.0, 1.0, true, true, true, true));
        seccion.setBackground(new Background(bgImagen));
        seccion.getChildren().add(botonComenzarJuego);
    }

    /**
     * Configuración de estilos básicos del escenario y la ventana
     */
    private void configurarEstilos() {
        footer.setAlignment(Pos.CENTER);

        VBox.setVgrow(main, Priority.ALWAYS);
        setVgrow(main, Priority.ALWAYS);
        main.setAlignment(Pos.CENTER);
        setStyle("-fx-background-color: #DCDAD3");
        setSpacing(0);
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
