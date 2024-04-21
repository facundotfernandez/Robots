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

public class Encabezado extends HBox {
    private final VBox containerTitulo;
    private final Label titulo;
    private final HBox containerSubtitulos;
    private final Stage escenario;

    public Encabezado(Stage escenario, String titulo) {
        this.escenario = escenario;

        this.containerTitulo = new VBox();
        this.titulo = new Label(titulo);
        getChildren().add(this.titulo);

        this.containerSubtitulos = new HBox();
        configurarEstilos();
    }

    public Encabezado(Stage escenario, String titulo, String[] subtitulos) {
        this.escenario = escenario;

        this.containerTitulo = new VBox();
        HBox.setHgrow(containerTitulo, Priority.ALWAYS);
        getChildren().add(containerTitulo);

        this.titulo = new Label(titulo);
        containerTitulo.getChildren().add(this.titulo);

        this.containerSubtitulos = new HBox();
        containerTitulo.getChildren().add(containerSubtitulos);

        for (String subtitulo : subtitulos) {
            containerSubtitulos.getChildren().add(new Subtitulo(subtitulo));
        }

        Boton closeButton = new Boton("X");
        closeButton.setOnAction(e -> escenario.close());
        closeButton.setPrefHeight(50);
        closeButton.setPrefWidth(50);
        getChildren().add(closeButton);

        configurarEstilos();
    }

    private void configurarEstilos() {
        Font.loadFont(getClass().getResourceAsStream("Canterell-Regular.ttf"), 10);

        titulo.setFont(Font.font("Canterell", FontWeight.BOLD, 18));
        titulo.setStyle("-fx-text-fill: #343A3B;");
        titulo.setPadding(new Insets(0, 10, 0, 0));

        containerTitulo.setAlignment(Pos.CENTER);
        titulo.setAlignment(Pos.CENTER);
        containerSubtitulos.setAlignment(Pos.CENTER);
        setAlignment(Pos.CENTER);


        setSpacing(5);
        setPadding(new Insets(10));
        setStyle("-fx-background-color: #E9E9E9;");
        setPrefHeight(64);
    }
}