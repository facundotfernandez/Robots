package org.example.vista.componentes;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.Objects;

public class Icono extends StackPane {

    /**
     * Inicializa los componentes del componente con sus estilos aplicados
     *
     * @param ruta   Ruta relativa del archivo
     * @param ancho  Ancho en píxeles
     * @param altura Altura en píxeles
     */
    public Icono(String ruta, double ancho, double altura) {
        Image imagen = new Image(Objects.requireNonNull(getClass().getResourceAsStream(ruta)));

        ImageView imageView = new ImageView(imagen);
        imageView.setFitWidth(ancho);
        imageView.setFitHeight(altura);

        StackPane imageContainer = new StackPane(imageView);
        StackPane.setMargin(imageContainer, new Insets(0, 9, 0, 9));

        getChildren().add(imageContainer);
    }
}
