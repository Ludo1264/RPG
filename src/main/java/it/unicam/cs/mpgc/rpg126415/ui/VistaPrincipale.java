package it.unicam.cs.mpgc.rpg126415.ui;

import it.unicam.cs.mpgc.rpg126415.domain.model.Eroe;
import it.unicam.cs.mpgc.rpg126415.domain.model.Nemico;
import it.unicam.cs.mpgc.rpg126415.domain.service.Avventura;
import it.unicam.cs.mpgc.rpg126415.domain.model.Incontro;
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

        RadioButton rbGuerriero = new RadioButton("Guerriero");
        RadioButton rbMago = new RadioButton("Mago");
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
                controller.avviaNuovaPartita(txtNome.getText(), rbGuerriero.isSelected());
                mostraSchermataAvventura();
            } catch (IllegalArgumentException ex) {
                lblMessaggio.setStyle("-fx-text-fill: #c23616;");
                lblMessaggio.setText(ex.getMessage());
            }
        });

        btnCaricaPartita.setOnAction(e -> {
            try {
                Optional<Avventura> caricata = controller.caricaPartitaSalvata();
                if (caricata.isPresent()) {
                    mostraSchermataAvventura();
                } else {
                    lblMessaggio.setStyle("-fx-text-fill: #c23616;");
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

    public void mostraSchermataAvventura() {
        VBox boxAvventura = new VBox(16);
        boxAvventura.setPadding(new Insets(25));
        boxAvventura.setAlignment(Pos.TOP_CENTER);

        Label lblStatoTorre = new Label();
        lblStatoTorre.setFont(Font.font("System", FontWeight.BOLD, 16));

        Label lblStatsEroe = new Label();
        lblStatsEroe.setStyle("-fx-font-size: 13px;");

        Label lblNemico = new Label();
        lblNemico.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #b33939;");

        VBox boxSchede = new VBox(8, lblStatoTorre, lblStatsEroe, lblNemico);
        boxSchede.setAlignment(Pos.CENTER);
        boxSchede.setPadding(new Insets(12));
        boxSchede.setStyle("-fx-background-color: #f1f2f6; -fx-background-radius: 8;");

        TextArea areaLog = new TextArea();
        areaLog.setEditable(false);
        areaLog.setPrefHeight(190);
        areaLog.setWrapText(true);

        Label lblFeedback = new Label();
        lblFeedback.setStyle("-fx-font-weight: bold;");

        Button btnAttacca = new Button("Attacca (Gioca Turno)");
        btnAttacca.setStyle("-fx-background-color: #c23616; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 18;");

        Button btnSalva = new Button("Salva Partita");
        btnSalva.setStyle("-fx-background-color: #273c75; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 18;");

        Button btnMenu = new Button("Menu Principale");
        btnMenu.setStyle("-fx-padding: 10 18;");

        Runnable aggiornaStato = () -> {
            Avventura avv = controller.getAvventuraCorrente();
            if (avv == null) {
                return;
            }

            Eroe eroe = avv.getEroe();
            int incontroNumero = Math.min(avv.getIndiceIncontroCorrente() + 1, avv.getTotaleIncontri());
            lblStatoTorre.setText("Incontro " + incontroNumero + " di " + avv.getTotaleIncontri());

            lblStatsEroe.setText(String.format(
                    "%s | Liv. %d (XP: %d) | HP: %d/%d | Attacco: %d",
                    eroe.getNome(),
                    eroe.getLivello(),
                    eroe.getPuntiEsperienza(),
                    eroe.getPuntiVitaAttuali(),
                    eroe.getPuntiVitaMassimi(),
                    eroe.getDannoAttaccoBase()
            ));

            Incontro incontro = avv.getIncontroCorrente();
            if (incontro != null) {
                Nemico nemico = incontro.getNemico();
                lblNemico.setText(String.format(
                        "Nemico: %s — HP: %d/%d",
                        nemico.getNomeNemico(),
                        nemico.getPuntiVitaAttualiNemico(),
                        nemico.getPuntiVitaMassimiNemico()
                ));
            } else if (avv.isCompletata()) {
                lblNemico.setText("Hai conquistato la cima della Torre!");
            } else {
                lblNemico.setText("L'eroe è caduto in battaglia.");
            }

            btnAttacca.setDisable(avv.isTerminata());
        };

        Avventura avventuraIniziale = controller.getAvventuraCorrente();
        if (avventuraIniziale != null) {
            areaLog.appendText("Avventura iniziata per " + avventuraIniziale.getEroe().getNome() + "!\n");
            if (avventuraIniziale.isTerminata()) {
                areaLog.appendText("Questa partita è già terminata.\n");
            }
        }
        aggiornaStato.run();

        btnAttacca.setOnAction(e -> {
            try {
                String esito = controller.giocaTurno();
                areaLog.appendText(esito + "\n");
                aggiornaStato.run();

                Avventura avv = controller.getAvventuraCorrente();
                if (avv != null && avv.isCompletata()) {
                    areaLog.appendText("=== VITTORIA! Tutti i sigilli sono stati superati! ===\n");
                } else if (avv != null && avv.isSconfitta()) {
                    areaLog.appendText("=== SCONFITTA! Torna al menu principale per riprovare. ===\n");
                }
            } catch (IllegalStateException ex) {
                lblFeedback.setStyle("-fx-text-fill: #c23616;");
                lblFeedback.setText(ex.getMessage());
            }
        });

        btnSalva.setOnAction(e -> {
            try {
                controller.salvaPartita();
                lblFeedback.setStyle("-fx-text-fill: #44bd32;");
                lblFeedback.setText("Partita salvata con successo.");
            } catch (IOException | IllegalStateException ex) {
                lblFeedback.setStyle("-fx-text-fill: #c23616;");
                lblFeedback.setText("Errore salvataggio: " + ex.getMessage());
            }
        });

        btnMenu.setOnAction(e -> mostraSchermataIniziale());

        HBox boxAzioni = new HBox(12, btnAttacca, btnSalva, btnMenu);
        boxAzioni.setAlignment(Pos.CENTER);

        boxAvventura.getChildren().addAll(boxSchede, boxAzioni, areaLog, lblFeedback);
        root.setCenter(boxAvventura);
    }

    public Parent getRoot() {
        return root;
    }
}