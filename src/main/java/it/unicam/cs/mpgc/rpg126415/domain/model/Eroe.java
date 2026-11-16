package it.unicam.cs.mpgc.rpg126415.domain.model;

public abstract class Eroe {

    private static final int PUNTI_ESPERIENZA_PER_LIVELLO = 50;
    private static final int BONUS_VITA_PER_LIVELLO = 10;
    private static final int BONUS_DANNO_PER_LIVELLO = 2;

    private final String nome;
    private int puntiVitaMassimi;
    private int puntiVitaAttuali;
    private int dannoAttaccoBase;
    private int livello;
    private int puntiEsperienza;

    protected Eroe(String nome, int puntiVitaMassimi, int dannoAttaccoBase) {
        this(nome, puntiVitaMassimi, puntiVitaMassimi, dannoAttaccoBase, 1, 0);
    }

    protected Eroe(String nome, int puntiVitaMassimi, int puntiVitaAttuali,
                   int dannoAttaccoBase, int livello, int puntiEsperienza) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Il nome dell'eroe non può essere vuoto");
        }
        if (puntiVitaMassimi <= 0 || puntiVitaAttuali < 0 || puntiVitaAttuali > puntiVitaMassimi) {
            throw new IllegalArgumentException("Valori dei punti vita non validi");
        }
        if (dannoAttaccoBase <= 0 || livello < 1 || puntiEsperienza < 0) {
            throw new IllegalArgumentException("Statistiche dell'eroe non valide");
        }

        this.nome = nome;
        this.puntiVitaMassimi = puntiVitaMassimi;
        this.puntiVitaAttuali = puntiVitaAttuali;
        this.dannoAttaccoBase = dannoAttaccoBase;
        this.livello = livello;
        this.puntiEsperienza = puntiEsperienza;
    }

    public void subisciDanno(int numeroDanni) {
        if (numeroDanni < 0) {
            throw new IllegalArgumentException("Il danno non può essere negativo");
        }
        puntiVitaAttuali = Math.max(0, puntiVitaAttuali - numeroDanni);
    }

    public boolean isVivo() {
        return puntiVitaAttuali > 0;
    }

    public void aumentaDannoAttaccoBase(int bonusDanno) {
        if (bonusDanno <= 0) {
            throw new IllegalArgumentException("Il bonus al danno deve essere maggiore di zero");
        }
        dannoAttaccoBase += bonusDanno;
    }

    public void ripristinaPuntiVita(int puntiDaRecuperare) {
        if (puntiDaRecuperare <= 0) {
            throw new IllegalArgumentException("I punti da recuperare devono essere maggiori di zero");
        }
        puntiVitaAttuali = Math.min(puntiVitaMassimi, puntiVitaAttuali + puntiDaRecuperare);
    }

    public void aggiungiPuntiEsperienza(int puntiEsperienzaOttenuti) {
        if (puntiEsperienzaOttenuti < 0) {
            throw new IllegalArgumentException("I punti esperienza non possono essere negativi");
        }
        puntiEsperienza += puntiEsperienzaOttenuti;

        while (puntiEsperienza >= PUNTI_ESPERIENZA_PER_LIVELLO) {
            puntiEsperienza -= PUNTI_ESPERIENZA_PER_LIVELLO;
            livello++;
            puntiVitaMassimi += BONUS_VITA_PER_LIVELLO;
            puntiVitaAttuali += BONUS_VITA_PER_LIVELLO;
            dannoAttaccoBase += BONUS_DANNO_PER_LIVELLO;
        }
    }

    public int attacca() {
        return dannoAttaccoBase;
    }

    public String getNome() { return nome; }
    public int getPuntiVitaAttuali() { return puntiVitaAttuali; }
    public int getPuntiVitaMassimi() { return puntiVitaMassimi; }
    public int getDannoAttaccoBase() { return dannoAttaccoBase; }
    public int getLivello() { return livello; }
    public int getPuntiEsperienza() { return puntiEsperienza; }
}
