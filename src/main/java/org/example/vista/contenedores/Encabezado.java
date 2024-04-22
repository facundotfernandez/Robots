package org.example.vista.contenedores;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.example.vista.componentes.Subtitulo;
import org.example.vista.componentes.Titulo;

import static org.example.vista.utilidades.Constantes.TITULO;

public class Encabezado extends HBox {
    private final VBox containerTitulo;
    private final HBox containerSubtitulos;

    public Encabezado() {
        this.containerTitulo = new VBox();
        containerTitulo.getChildren().add(new Label(TITULO));

        this.containerSubtitulos = new HBox();
        configurarEstilos();
    }

    public Encabezado(String[] subtitulos) {
        this.containerTitulo = new VBox();
        getChildren().add(containerTitulo);

        containerTitulo.getChildren().add(new Titulo(TITULO));

        this.containerSubtitulos = new HBox();
        containerTitulo.getChildren().add(containerSubtitulos);

        for (String subtitulo : subtitulos) {
            containerSubtitulos.getChildren().add(new Subtitulo(subtitulo));
        }
        configurarEstilos();
    }

    private void configurarEstilos() {
        Font.loadFont(getClass().getResourceAsStream("Canterell-Regular.ttf"), 10);

        HBox.setHgrow(containerTitulo, Priority.ALWAYS);
        containerTitulo.setAlignment(Pos.CENTER);
        containerSubtitulos.setAlignment(Pos.CENTER);
        setAlignment(Pos.CENTER);

        setSpacing(5);
        setPadding(new Insets(10));
        setStyle("-fx-background-color: #E9E9E9;");
        setPrefHeight(64);
    }
}