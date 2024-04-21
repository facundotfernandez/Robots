package org.example.vista.componentes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Region;

public class Eleccion<T> extends ComboBox<T> {

    public Eleccion() {
        super();
        configurarEstilos();
    }

    private void configurarEstilos() {
        setPrefHeight(64);
        setMaxWidth(Double.MAX_VALUE);
        setStyle("-fx-background-color: #E9E9E9; -fx-background-radius: 0px; -fx-font-size: 16px; -fx-text-fill: #343A3B; -fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
        getStyleClass().add("center-aligned");
        setButtonCell(createCenteredCell());
        setCellFactory(param -> createCenteredCell());
    }

    private ListCell<T> createCenteredCell() {
        return new ListCell<>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.toString());
                    setAlignment(Pos.CENTER);
                    setPadding(new Insets(0, 0, 0, 3));

                    setStyle("-fx-background-color: #E9E9E9;  -fx-text-fill: #343A3B; -fx-padding: 5px;");
                    setText(item.toString());
                    setAlignment(Pos.CENTER);
                    setMinHeight(Region.USE_PREF_SIZE);
                    setPrefHeight(Region.USE_COMPUTED_SIZE);
                    setMaxHeight(Region.USE_PREF_SIZE);
                }
            }
        };
    }
}