package org.example.vista.ventanas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.vista.RobotsApp;
import org.example.vista.componentes.Boton;
import org.example.vista.componentes.Eleccion;
import org.example.vista.contenedores.BarraDeTitulo;
import org.example.vista.contenedores.BloqueDeBotones;
import org.example.vista.contenedores.BloqueDeElecciones;
import org.example.vista.contenedores.Encabezado;

import java.util.Objects;

import static org.example.modelo.utilidades.Constantes.*;

public class VentanaMenuPrincipal extends VBox {
    private final String[] etiquetasDificultad = {"Facil", "Normal", "Dificil"};
    private final int[] valoresDificultad = {FACIL, NORMAL, DIFICIL};
    private final String titulo;
    Stage escenario;
    Boton botonComenzarJuego;
    private Encabezado header;
    private VBox seccionPrincipal;
    private BloqueDeBotones footer;

    public VentanaMenuPrincipal(Stage escenario, String titulo, Boton botonComenzarJuego) {
        this.titulo = titulo;
        this.escenario = escenario;
        this.botonComenzarJuego = botonComenzarJuego;
        escenario.setTitle(titulo);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        Encabezado header = new Encabezado(titulo, new String[]{"Menu Principal"});
        VBox seccionPrincipal = new VBox();

        BloqueDeElecciones bloqueDeElecciones = new BloqueDeElecciones();
        Eleccion<String> dificultades = new Eleccion<>();
        dificultades.getItems().addAll("Facil", "Normal", "Dificil");
        bloqueDeElecciones.agregarGrupo("Dificultad", dificultades);

        Eleccion<Integer> filas = new Eleccion<>();
        filas.getItems().addAll(10, 15, 20);
        bloqueDeElecciones.agregarGrupo("Filas", filas);

        Eleccion<Integer> columnas = new Eleccion<>();
        columnas.getItems().addAll(25, 35, 45);
        bloqueDeElecciones.agregarGrupo("Columnas", columnas);
        bloqueDeElecciones.setAlignment(Pos.CENTER);

        ObservableMap<String, Integer> mapaDificultades = FXCollections.observableHashMap();
        for (int i = 0; i < etiquetasDificultad.length; i++) {
            mapaDificultades.put(etiquetasDificultad[i], valoresDificultad[i]);
        }

        botonComenzarJuego.setOnAction(_ -> {
            if (dificultades.getValue() != null && filas.getValue() != null && columnas.getValue() != null) {
                int[] userData = new int[]{mapaDificultades.get(dificultades.getValue()), filas.getValue(), columnas.getValue()};
                botonComenzarJuego.setUserData(userData);
                try {
                    RobotsApp.iniciarJuego(userData[0], userData[1], userData[2]);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        var bgMenu = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/tablero/menu_principal.png")));
        BackgroundImage bgImagen = new BackgroundImage(bgMenu, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1.0, 1.0, true, true, true, true));
        seccionPrincipal.setBackground(new Background(bgImagen));
        seccionPrincipal.getChildren().add(botonComenzarJuego);

        setVgrow(seccionPrincipal, Priority.ALWAYS);
        seccionPrincipal.setAlignment(Pos.CENTER);
        setStyle("-fx-background-color: #DCDAD3");
        setSpacing(0);

        this.header = header;
        this.seccionPrincipal = seccionPrincipal;
        this.getChildren().addAll(new BarraDeTitulo(escenario), header, seccionPrincipal, bloqueDeElecciones);

    }

    public Encabezado getHeader() {
        return header;
    }

    public VBox getSeccionPrincipal() {
        return seccionPrincipal;
    }

    public BloqueDeBotones getFooter() {
        return footer;
    }

}
