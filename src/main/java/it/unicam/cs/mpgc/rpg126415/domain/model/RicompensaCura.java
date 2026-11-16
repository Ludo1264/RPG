package it.unicam.cs.mpgc.rpg126415.domain.model;

public class RicompensaCura implements Ricompensa {

    private final String nomeOggetto;
    private final int puntiCura;

    public RicompensaCura(String nomeOggetto, int puntiCura) {
        if (nomeOggetto == null || nomeOggetto.isBlank()) {
            throw new IllegalArgumentException("Il nome dell'oggetto non può essere vuoto");
        }
        if (puntiCura <= 0) {
            throw new IllegalArgumentException("I punti cura devono essere maggiori di zero");
        }
        this.nomeOggetto = nomeOggetto;
        this.puntiCura = puntiCura;
    }

    @Override
    public String getDescrizione() {
        return nomeOggetto + " (+" + puntiCura + " HP)";
    }

    @Override
    public void applicaA(Eroe eroe) {
        if (eroe == null) {
            throw new IllegalArgumentException("L'eroe non può essere nullo");
        }
        eroe.ripristinaPuntiVita(puntiCura);
    }
}