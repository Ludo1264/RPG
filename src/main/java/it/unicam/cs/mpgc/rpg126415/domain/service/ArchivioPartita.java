package it.unicam.cs.mpgc.rpg126415.domain.service;

import java.io.IOException;
import java.util.Optional;

public interface ArchivioPartita {

    void salva(Avventura avventura) throws IOException;

    Optional<Avventura> carica() throws IOException;

    boolean esisteSalvataggio();
}