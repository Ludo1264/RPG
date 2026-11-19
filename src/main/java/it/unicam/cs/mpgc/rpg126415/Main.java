package it.unicam.cs.mpgc.rpg126415;

import it.unicam.cs.mpgc.rpg126415.domain.service.ArchivioPartita;
import it.unicam.cs.mpgc.rpg126415.persistence.ArchivioPartitaJson;
import it.unicam.cs.mpgc.rpg126415.ui.GiocoController;
import it.unicam.cs.mpgc.rpg126415.ui.VistaPrincipale;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.nio.file.Path;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        ArchivioPartita archivio = new ArchivioPartitaJson(Path.of("salvataggi", "partita.json"));
        GiocoController controller = new GiocoController(archivio);
        VistaPrincipale vistaPrincipale = new VistaPrincipale(controller);

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