package org.example.vista.contenedores;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.example.vista.componentes.Boton;

import java.util.LinkedList;

public class BloqueDeBotones extends VBox {

    public BloqueDeBotones(LinkedList<Boton> botones) {
        super();
        var contenedor = new HBox();
        getChildren().add(contenedor);
        for (Boton boton : botones) contenedor.getChildren().add(boton);
        configurarEstilos(contenedor, botones);
    }

    private void configurarEstilos(HBox contenedor, LinkedList<Boton> botones) {
        for (Boton boton : botones) {
            HBox.setHgrow(boton, Priority.ALWAYS);
            boton.setMaxWidth(Double.MAX_VALUE);
            VBox.setVgrow(boton, Priority.ALWAYS);
            boton.setMaxHeight(Double.MAX_VALUE);
        }

        VBox.setVgrow(contenedor, Priority.ALWAYS);
        contenedor.setMaxHeight(Double.MAX_VALUE);

        VBox.setVgrow(this, Priority.ALWAYS);
    }
}
