package it.unicam.cs.mpgc.rpg126415.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import it.unicam.cs.mpgc.rpg126415.domain.model.Eroe;
import it.unicam.cs.mpgc.rpg126415.domain.model.Guerriero;
import it.unicam.cs.mpgc.rpg126415.domain.model.Mago;
import it.unicam.cs.mpgc.rpg126415.domain.service.ArchivioPartita;
import it.unicam.cs.mpgc.rpg126415.domain.service.Avventura;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class ArchivioPartitaJson implements ArchivioPartita {

    private final Path percorsoFile;
    private final Gson gson;

    public ArchivioPartitaJson(Path percorsoFile) {
        if (percorsoFile == null) {
            throw new IllegalArgumentException("Il percorso del file di salvataggio non può essere nullo");
        }
        this.percorsoFile = percorsoFile;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public void salva(Avventura avventura) throws IOException {
        if (avventura == null) {
            throw new IllegalArgumentException("L'avventura da salvare non può essere nulla");
        }

        Eroe eroe = avventura.getEroe();
        String tipoEroe = (eroe instanceof Guerriero) ? "GUERRIERO" : "MAGO";

        SalvataggioDati dati = new SalvataggioDati(
                tipoEroe,
                eroe.getNome(),
                eroe.getPuntiVitaMassimi(),
                eroe.getPuntiVitaAttuali(),
                eroe.getDannoAttaccoBase(),
                eroe.getLivello(),
                eroe.getPuntiEsperienza(),
                avventura.getIndiceIncontroCorrente()
        );

        if (percorsoFile.getParent() != null) {
            Files.createDirectories(percorsoFile.getParent());
        }
        Files.writeString(percorsoFile, gson.toJson(dati));
    }

    @Override
    public Optional<Avventura> carica() throws IOException {
        if (!esisteSalvataggio()) {
            return Optional.empty();
        }

        String json = Files.readString(percorsoFile);
        SalvataggioDati dati;
        try {
            dati = gson.fromJson(json, SalvataggioDati.class);
        } catch (JsonSyntaxException e) {
            throw new IOException("Il file di salvataggio JSON è corrotto", e);
        }

        if (dati == null || dati.getTipoEroe() == null) {
            throw new IOException("Dati di salvataggio mancanti o non validi");
        }

        Eroe eroe = ricostruisciEroe(dati);
        return Optional.of(new Avventura(eroe, dati.getIndiceIncontroCorrente()));
    }

    @Override
    public boolean esisteSalvataggio() {
        return Files.exists(percorsoFile);
    }

    private Eroe ricostruisciEroe(SalvataggioDati dati) throws IOException {
        try {
            return switch (dati.getTipoEroe().toUpperCase()) {
                case "GUERRIERO" -> new Guerriero(
                        dati.getNomeEroe(),
                        dati.getPuntiVitaMassimi(),
                        dati.getPuntiVitaAttuali(),
                        dati.getDannoAttaccoBase(),
                        dati.getLivello(),
                        dati.getPuntiEsperienza()
                );
                case "MAGO" -> new Mago(
                        dati.getNomeEroe(),
                        dati.getPuntiVitaMassimi(),
                        dati.getPuntiVitaAttuali(),
                        dati.getDannoAttaccoBase(),
                        dati.getLivello(),
                        dati.getPuntiEsperienza()
                );
                default -> throw new IOException("Tipo eroe sconosciuto nel salvataggio: " + dati.getTipoEroe());
            };
        } catch (IllegalArgumentException e) {
            throw new IOException("Valori dell'eroe non validi nel file di salvataggio", e);
        }
    }
}