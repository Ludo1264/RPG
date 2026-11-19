package it.unicam.cs.mpgc.rpg126415.ui;

import it.unicam.cs.mpgc.rpg126415.domain.service.Avventura;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.IOException;
import java.util.Optional;

public class VistaPrincipale {

    private final BorderPane root;
    private final GiocoController controller;

    public VistaPrincipale(GiocoController controller) {
        this.controller = controller;
        this.root = new BorderPane();
        inizializzaIntestazione();
        mostraSchermataIniziale();
    }

    private void inizializzaIntestazione() {
        Label titolo = new Label("LA TORRE DEI TRE SIGILLI");
        titolo.setFont(Font.font("System", FontWeight.BOLD, 24));
        titolo.setStyle("-fx-text-fill: #f5f6fa;");

        VBox header = new VBox(titolo);
        header.setAlignment(Pos.CENTER);
        header.setStyle("-fx-padding: 20; -fx-background-color: #1e272e;");

        root.setTop(header);
    }

    public void mostraSchermataIniziale() {
        VBox boxCentrale = new VBox(18);
        boxCentrale.setAlignment(Pos.CENTER);
        boxCentrale.setPadding(new Insets(40));

        Label lblBenvenuto = new Label("Crea il tuo Eroe per scalare la Torre");
        lblBenvenuto.setFont(Font.font("System", FontWeight.BOLD, 18));

        TextField txtNome = new TextField();
        txtNome.setPromptText("Inserisci il nome dell'eroe...");
        txtNome.setMaxWidth(300);

        RadioButton rbGuerriero = new RadioButton("Guerriero (Alta resistenza e difesa)");
        RadioButton rbMago = new RadioButton("Mago (Attacco magico potente)");
        ToggleGroup gruppoClasse = new ToggleGroup();
        rbGuerriero.setToggleGroup(gruppoClasse);
        rbMago.setToggleGroup(gruppoClasse);
        rbGuerriero.setSelected(true);

        VBox boxClassi = new VBox(10, rbGuerriero, rbMago);
        boxClassi.setAlignment(Pos.CENTER_LEFT);
        boxClassi.setMaxWidth(300);

        Label lblMessaggio = new Label();
        lblMessaggio.setStyle("-fx-text-fill: #c23616;");

        Button btnNuovaPartita = new Button("Inizia Nuova Avventura");
        btnNuovaPartita.setStyle("-fx-background-color: #273c75; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20;");

        Button btnCaricaPartita = new Button("Carica Partita Salvata");
        btnCaricaPartita.setStyle("-fx-background-color: #44bd32; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20;");
        btnCaricaPartita.setDisable(!controller.esisteSalvataggio());

        btnNuovaPartita.setOnAction(e -> {
            try {
                Avventura avv = controller.avviaNuovaPartita(txtNome.getText(), rbGuerriero.isSelected());
                lblMessaggio.setStyle("-fx-text-fill: #44bd32;");
                lblMessaggio.setText("Avventura avviata per " + avv.getEroe().getNome() + "! (Arena in arrivo al prossimo commit)");
            } catch (IllegalArgumentException ex) {
                lblMessaggio.setStyle("-fx-text-fill: #c23616;");
                lblMessaggio.setText(ex.getMessage());
            }
        });

        btnCaricaPartita.setOnAction(e -> {
            try {
                Optional<Avventura> caricata = controller.caricaPartitaSalvata();
                if (caricata.isPresent()) {
                    lblMessaggio.setStyle("-fx-text-fill: #44bd32;");
                    lblMessaggio.setText("Partita caricata: " + caricata.get().getEroe().getNome());
                } else {
                    lblMessaggio.setText("Nessun salvataggio trovato.");
                }
            } catch (IOException ex) {
                lblMessaggio.setStyle("-fx-text-fill: #c23616;");
                lblMessaggio.setText("Errore caricamento: " + ex.getMessage());
            }
        });

        HBox boxPulsanti = new HBox(15, btnNuovaPartita, btnCaricaPartita);
        boxPulsanti.setAlignment(Pos.CENTER);

        boxCentrale.getChildren().addAll(lblBenvenuto, txtNome, boxClassi, boxPulsanti, lblMessaggio);
        root.setCenter(boxCentrale);
    }

    public Parent getRoot() {
        return root;
    }
}