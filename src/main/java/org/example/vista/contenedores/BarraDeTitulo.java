package org.example.vista.contenedores;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import org.example.vista.componentes.Boton;
import org.example.vista.componentes.BotonCerrar;
import org.example.vista.componentes.BotonMinimizar;
import org.example.vista.componentes.Icono;

public class BarraDeTitulo extends HBox {

    public BarraDeTitulo(Stage escenario) {
        Icono icono = new Icono("/tablero/icono.png", 32, 32);
        BotonCerrar botonCerrar = new BotonCerrar(escenario);
        BotonMinimizar botonMinimizar = new BotonMinimizar(escenario);
        configurarEstilos(new HBox(), icono, botonCerrar, botonMinimizar);
    }

    private void configurarEstilos(HBox container, Icono icono, Boton botonCerrar, Boton botonMinimizar) {
        setHgrow(container, Priority.ALWAYS);
        setStyle("-fx-background-color: #E9E9E9");
        setSpacing(0);

        getChildren().addAll(icono, container, botonMinimizar, botonCerrar);
    }
}
