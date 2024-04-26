package org.example.vista.componentes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Region;

public class Eleccion<T> extends ComboBox<T> {

    /**
     * Inicializa los componentes del componente
     */
    public Eleccion() {
        super();
        configurarEstilos();
    }

    /**
     * Configuración de estilos básicos del componente
     */
    private void configurarEstilos() {
        setPrefHeight(64);
        setMaxWidth(Double.MAX_VALUE);
        setStyle("-fx-background-color: #E9E9E9; -fx-background-radius: 0px; -fx-font-size: 16px; -fx-text-fill: #343A3B; -fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
        getStyleClass().add("center-aligned");
        setButtonCell(crearOpcionCentrada());
        setCellFactory(_ -> crearOpcionCentrada());
    }

    /**
     * Crea una celda de opción centrada, con los estilos aplicados
     */
    private ListCell<T> crearOpcionCentrada() {
        return new ListCell<>() {
            @Override
            protected void updateItem(T elemento, boolean estaVacio) {
                super.updateItem(elemento, estaVacio);
                if (estaVacio || elemento == null) {
                    setText(null);
                } else {
                    setText(elemento.toString());
                    setAlignment(Pos.CENTER);
                    setPadding(new Insets(0, 0, 0, 3));

                    setStyle("-fx-background-color: #E9E9E9;  -fx-text-fill: #343A3B; -fx-padding: 5px;");
                    setText(elemento.toString());
                    setAlignment(Pos.CENTER);
                    setMinHeight(Region.USE_PREF_SIZE);
                    setPrefHeight(Region.USE_COMPUTED_SIZE);
                    setMaxHeight(Region.USE_PREF_SIZE);
                }
            }
        };
    }
}