package org.example.vista.ventanas;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.vista.componentes.Boton;
import org.example.vista.contenedores.BloqueDeBotones;
import org.example.vista.contenedores.Encabezado;

import java.util.LinkedList;
import java.util.Objects;

import static org.example.modelo.utilidades.Constantes.*;

public class VentanaMenuPrincipal extends VBox {
    private final String[] etiquetasDificultad = {"Fácil", "Normal", "Difícil"};
    private final int[] valoresDificultad = {FACIL, NORMAL, DIFICIL};
    private final String titulo;
    Stage escenario;
    private Encabezado header;
    private VBox seccionPrincipal;
    private BloqueDeBotones footer;

    public VentanaMenuPrincipal(Stage escenario, String titulo) {
        this.titulo = titulo;
        this.escenario = escenario;
        escenario.setTitle(titulo);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        Encabezado header = new Encabezado(escenario, titulo, new String[]{"Nivel: 1", "Puntaje: 0"});
        VBox seccionPrincipal = new VBox();
        BloqueDeBotones footer = crearBotonesDificultad();

        var bgMenu = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/tablero/menu_principal.png")));
        BackgroundImage bgImagen = new BackgroundImage(bgMenu, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1.0, 1.0, true, true, true, true));
        seccionPrincipal.setBackground(new Background(bgImagen));

        setVgrow(seccionPrincipal, Priority.ALWAYS);
        setStyle("-fx-background-color: #DCDAD3");
        setSpacing(0);

        this.header = header;
        this.seccionPrincipal = seccionPrincipal;
        this.footer = footer;
        this.getChildren().addAll(header, seccionPrincipal, footer);
    }

    private BloqueDeBotones crearBotonesDificultad() {
        LinkedList<Boton> botones = new LinkedList<>();
        for (int i = 0; i < etiquetasDificultad.length; i++) {
            Boton boton = new Boton(etiquetasDificultad[i]);
            var dificultad = valoresDificultad[i];
            boton.setUserData(dificultad);
            botones.add(boton);
        }

        return new BloqueDeBotones(botones);
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
