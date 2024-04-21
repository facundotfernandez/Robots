package org.example.vista.componentes;

import javafx.stage.Stage;

public class BotonMinimizar extends Boton {

    public BotonMinimizar(Stage escenario) {
        super("_");
        configurarEstilos(escenario);
    }

    private void configurarEstilos(Stage escenario) {
        setOnAction(e -> escenario.setIconified(true));
        setPrefSize(50, 50);
        setLayoutX(1400);
        setLayoutY(10);
    }
}
