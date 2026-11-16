package it.unicam.cs.mpgc.rpg126415.domain.model;

public class Incontro {

    private final Nemico nemico;
    private final Ricompensa ricompensa;

    public Incontro(Nemico nemico, Ricompensa ricompensa) {
        if (nemico == null) {
            throw new IllegalArgumentException("Il nemico dell'incontro non può essere nullo");
        }
        this.nemico = nemico;
        this.ricompensa = ricompensa; // Può essere null (es. Boss finale)
    }

    public Nemico getNemico() {
        return nemico;
    }

    public Ricompensa getRicompensa() {
        return ricompensa;
    }

    public boolean haRicompensa() {
        return ricompensa != null;
    }
}