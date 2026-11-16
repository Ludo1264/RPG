package it.unicam.cs.mpgc.rpg126415.domain.model;

public class RicompensaAttacco implements Ricompensa {

    private final String nomeOggetto;
    private final int bonusDanno;

    public RicompensaAttacco(String nomeOggetto, int bonusDanno) {
        if (nomeOggetto == null || nomeOggetto.isBlank()) {
            throw new IllegalArgumentException("Il nome dell'oggetto non può essere vuoto");
        }
        if (bonusDanno <= 0) {
            throw new IllegalArgumentException("Il bonus al danno deve essere maggiore di zero");
        }
        this.nomeOggetto = nomeOggetto;
        this.bonusDanno = bonusDanno;
    }

    @Override
    public String getDescrizione() {
        return nomeOggetto + " (+" + bonusDanno + " Attacco)";
    }

    @Override
    public void applicaA(Eroe eroe) {
        if (eroe == null) {
            throw new IllegalArgumentException("L'eroe non può essere nullo");
        }
        eroe.aumentaDannoAttaccoBase(bonusDanno);
    }
}