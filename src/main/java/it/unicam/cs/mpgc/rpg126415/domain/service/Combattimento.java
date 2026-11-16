package it.unicam.cs.mpgc.rpg126415.domain.service;

import it.unicam.cs.mpgc.rpg126415.domain.model.Eroe;
import it.unicam.cs.mpgc.rpg126415.domain.model.Nemico;

public class Combattimento {

    public void attaccoEroe(Eroe eroe, Nemico nemico) {
        if (eroe == null || nemico == null) {
            throw new IllegalArgumentException("Eroe e nemico devono essere presenti");
        }

        if (!eroe.isVivo() || !nemico.isVivo()) {
            throw new IllegalStateException("L'attacco non può essere eseguito");
        }

        nemico.subisciDanno(eroe.attacca());
    }

    public void attaccoNemico(Eroe eroe, Nemico nemico) {
        if (eroe == null || nemico == null) {
            throw new IllegalArgumentException("Eroe e nemico devono essere presenti");
        }

        if (!eroe.isVivo() || !nemico.isVivo()) {
            throw new IllegalStateException("L'attacco non può essere eseguito");
        }

        eroe.subisciDanno(nemico.attacca());
    }

    public boolean eseguiTurno(Eroe eroe, Nemico nemico) {
        attaccoEroe(eroe, nemico);

        if (!nemico.isVivo()) {
            eroe.aggiungiPuntiEsperienza(nemico.getPuntiEsperienzaRicompensa());
            return false;
        }

        attaccoNemico(eroe, nemico);

        return eroe.isVivo();
    }
}