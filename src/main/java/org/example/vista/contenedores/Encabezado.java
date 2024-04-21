package org.example.vista.contenedores;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import org.example.vista.componentes.Boton;
import org.example.vista.componentes.Subtitulo;
import org.example.vista.componentes.Titulo;

public class Encabezado extends HBox {
    private final VBox containerTitulo;
    private final Label titulo;
    private final HBox containerSubtitulos;

    public Encabezado(String titulo) {
        this.containerTitulo = new VBox();
        this.titulo = new Label(titulo);
        getChildren().add(this.titulo);

        this.containerSubtitulos = new HBox();
        configurarEstilos();
    }

    public Encabezado(String titulo, String[] subtitulos) {
        this.containerTitulo = new VBox();
        HBox.setHgrow(containerTitulo, Priority.ALWAYS);
        getChildren().add(containerTitulo);

        this.titulo = new Titulo(titulo);
        containerTitulo.getChildren().add(this.titulo);

        this.containerSubtitulos = new HBox();
        containerTitulo.getChildren().add(containerSubtitulos);

        for (String subtitulo : subtitulos) {
            containerSubtitulos.getChildren().add(new Subtitulo(subtitulo));
        }



        configurarEstilos();
    }

    private void configurarEstilos() {
        Font.loadFont(getClass().getResourceAsStream("Canterell-Regular.ttf"), 10);

        containerTitulo.setAlignment(Pos.CENTER);
        containerSubtitulos.setAlignment(Pos.CENTER);
        setAlignment(Pos.CENTER);

        setSpacing(5);
        setPadding(new Insets(10));
        setStyle("-fx-background-color: #E9E9E9;");
        setPrefHeight(64);
    }
}