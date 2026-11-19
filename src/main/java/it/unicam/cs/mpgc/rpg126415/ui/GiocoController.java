package it.unicam.cs.mpgc.rpg126415.ui;

import it.unicam.cs.mpgc.rpg126415.domain.model.Eroe;
import it.unicam.cs.mpgc.rpg126415.domain.model.Guerriero;
import it.unicam.cs.mpgc.rpg126415.domain.model.Mago;
import it.unicam.cs.mpgc.rpg126415.domain.service.ArchivioPartita;
import it.unicam.cs.mpgc.rpg126415.domain.service.Avventura;

import java.io.IOException;
import java.util.Optional;

public class GiocoController {

    private final ArchivioPartita archivio;
    private Avventura avventuraCorrente;

    public GiocoController(ArchivioPartita archivio) {
        if (archivio == null) {
            throw new IllegalArgumentException("L'archivio partita non può essere nullo");
        }
        this.archivio = archivio;
    }

    public Avventura avviaNuovaPartita(String nomeEroe, boolean isGuerriero) {
        if (nomeEroe == null || nomeEroe.isBlank()) {
            throw new IllegalArgumentException("Inserisci un nome valido per l'eroe");
        }

        Eroe eroe = isGuerriero
                ? new Guerriero(nomeEroe.trim())
                : new Mago(nomeEroe.trim());

        this.avventuraCorrente = new Avventura(eroe);
        return this.avventuraCorrente;
    }

    public Optional<Avventura> caricaPartitaSalvata() throws IOException {
        Optional<Avventura> caricata = archivio.carica();
        caricata.ifPresent(avv -> this.avventuraCorrente = avv);
        return caricata;
    }

    public void salvaPartita() throws IOException {
        if (avventuraCorrente == null) {
            throw new IllegalStateException("Nessuna partita attiva da salvare");
        }
        archivio.salva(avventuraCorrente);
    }

    public boolean esisteSalvataggio() {
        return archivio.esisteSalvataggio();
    }

    public Avventura getAvventuraCorrente() {
        return avventuraCorrente;
    }
}