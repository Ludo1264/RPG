package it.unicam.cs.mpgc.rpg126415;

import it.unicam.cs.mpgc.rpg126415.ui.VistaPrincipale;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        VistaPrincipale vistaPrincipale = new VistaPrincipale();
        Scene scene = new Scene(vistaPrincipale.getRoot(), 800, 600);

        primaryStage.setTitle("La Torre dei Tre Sigilli - RPG");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}