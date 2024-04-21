package org.example.vista.contenedores;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import org.example.vista.componentes.Boton;

import java.util.LinkedList;

public class BloqueDeBotones extends HBox {

    public BloqueDeBotones(LinkedList<Boton> botones) {
        super();
        for (Boton boton : botones) this.getChildren().add(boton);
        configurarEstilos(botones);
    }

    private void configurarEstilos(LinkedList<Boton> botones) {
        for (Boton boton : botones) {
            setHgrow(boton, Priority.ALWAYS);
            boton.setMaxWidth(Double.MAX_VALUE);
        }

        setPrefHeight(64);
        setStyle("-fx-background-color: #E9E9E9;");
    }


}
