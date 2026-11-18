package it.unicam.cs.mpgc.rpg126415.ui;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class VistaPrincipale {

    private final BorderPane root;

    public VistaPrincipale() {
        this.root = new BorderPane();
        inizializzaIntestazione();
        inizializzaContenutoProvvisorio();
    }

    private void inizializzaIntestazione() {
        Label titolo = new Label("LA TORRE DEI TRE SIGILLI");
        titolo.setFont(Font.font("System", FontWeight.BOLD, 24));

        VBox header = new VBox(titolo);
        header.setAlignment(Pos.CENTER);
        header.setStyle("-fx-padding: 20; -fx-background-color: #1e272e;");
        titolo.setStyle("-fx-text-fill: #f5f6fa;");

        root.setTop(header);
    }

    private void inizializzaContenutoProvvisorio() {
        Label sottotitolo = new Label("Preparazione dell'avventura in corso...");
        sottotitolo.setFont(Font.font("System", 16));
        root.setCenter(sottotitolo);
    }

    public Parent getRoot() {
        return root;
    }
}