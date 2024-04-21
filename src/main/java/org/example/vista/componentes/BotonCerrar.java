package org.example.vista.componentes;

import javafx.stage.Stage;

public class BotonCerrar extends Boton {

    public BotonCerrar(Stage escenario) {
        super("X");
        configurarEstilos(escenario);
    }

    private void configurarEstilos(Stage escenario) {
        setOnAction(e -> escenario.close());
        setPrefSize(50, 50);
    }
}
