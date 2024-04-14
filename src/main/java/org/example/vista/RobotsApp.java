package org.example.vista;

import javafx.application.Application;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Objects;


public class RobotsApp extends Application {

    private static final int CELL_SIZE = 32;
    private static final int NUM_ROWS = 11;
    private static final int NUM_COLS = 15;
    private static final String VACIO = "/celdas/vacio.bmp";
    private static final String CENTER = "/celdas/centro.bmp";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        StackPane root = new StackPane(gridPane);
        Scene scene = new Scene(root, NUM_COLS * CELL_SIZE, NUM_ROWS * CELL_SIZE);
        primaryStage.setResizable(false);

        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                ImageView cellBackground = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(VACIO))));
                ImageView cellCenter = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(CENTER))));
                cellBackground.setFitWidth(CELL_SIZE);
                cellBackground.setFitHeight(CELL_SIZE);
                cellCenter.setFitWidth(CELL_SIZE);
                cellCenter.setFitHeight(CELL_SIZE);

                gridPane.add((row == 5 && col == 7) ? cellCenter : cellBackground, col, row);
            }
        }

        // Escuchar los cambios de posición del cursor
        gridPane.setOnMouseMoved(event -> {
            double mouseX = event.getX();
            double mouseY = event.getY();
            int row = (int) (mouseY / CELL_SIZE);
            int col = (int) (mouseX / CELL_SIZE);

            int centerRow = NUM_ROWS / 2;
            int centerCol = NUM_COLS / 2;

            int octant = getOctant(row, col, centerRow, centerCol);
            Image cursorImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/cursores/cursor-" + octant + ".png")));

            // Establecer la imagen del cursor
            scene.setCursor(new ImageCursor(cursorImage, 16, 16));
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private int getOctant(int row, int col, int centerRow, int centerCol) {
        if (row == centerRow && col == centerCol) return 0;
        else if (row < centerRow && col == centerCol) return 2;
        else if (row == centerRow && col < centerCol) return 4;
        else if (row > centerRow && col == centerCol) return 6;
        else if (row == centerRow && col > centerCol) return 8;
        else if (row < centerRow && col > centerCol) return 1;
        else if (row < centerRow && col < centerCol) return 3;
        else if (row > centerRow && col < centerCol) return 5;
        else return 7;
    }
}