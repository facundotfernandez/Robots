package org.example.vista.ventanas;

import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.vista.componentes.Boton;
import org.example.vista.contenedores.BloqueDeBotones;
import org.example.vista.contenedores.Encabezado;

import java.util.LinkedList;

import static org.example.modelo.utilidades.Constantes.*;

public class VentanaMenuPrincipal {
    private final Stage escenario;
    private final String[] etiquetasDificultad = {"Fácil", "Normal", "Difícil"};
    private final int[] valoresDificultad = {FACIL, NORMAL, DIFICIL};
    private int dificultadElegida = -1;

    public VentanaMenuPrincipal(Stage escenario, String titulo) {
        this.escenario = escenario;
        escenario.setTitle(titulo);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        Encabezado encabezado = new Encabezado("Robots", new String[]{"Nivel: 1", "Puntaje: 0"});
        VBox seccionPrincipal = new VBox();
        BloqueDeBotones containerDificultades = crearBotonesDificultad();

        VentanaDeTresBloques container = new VentanaDeTresBloques(encabezado, seccionPrincipal, containerDificultades);
        escenario.setScene(new Scene(container, 640, 480));
    }

    public int getDificultadElegida() {
        return dificultadElegida;
    }

    private BloqueDeBotones crearBotonesDificultad() {
        LinkedList<Boton> botones = new LinkedList<>();
        for (int i = 0; i < etiquetasDificultad.length; i++) {
            Boton boton = new Boton(etiquetasDificultad[i]);
            final int dificultad = valoresDificultad[i];
            boton.setOnAction(e -> {
                dificultadElegida = dificultad;
                System.out.println(dificultadElegida);
            });
            botones.add(boton);
        }

        return new BloqueDeBotones(botones);
    }

    public void setCursor(Image cursorImage) {
        escenario.getScene().setCursor(new ImageCursor(cursorImage));
    }
}
