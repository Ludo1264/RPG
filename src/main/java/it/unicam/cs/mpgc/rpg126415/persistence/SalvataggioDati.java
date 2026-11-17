package it.unicam.cs.mpgc.rpg126415.persistence;

public class SalvataggioDati {

    private String tipoEroe;
    private String nomeEroe;
    private int puntiVitaMassimi;
    private int puntiVitaAttuali;
    private int dannoAttaccoBase;
    private int livello;
    private int puntiEsperienza;
    private int indiceIncontroCorrente;

    public SalvataggioDati() {
        // Costruttore vuoto richiesto da Gson
    }

    public SalvataggioDati(String tipoEroe, String nomeEroe, int puntiVitaMassimi,
                           int puntiVitaAttuali, int dannoAttaccoBase, int livello,
                           int puntiEsperienza, int indiceIncontroCorrente) {
        this.tipoEroe = tipoEroe;
        this.nomeEroe = nomeEroe;
        this.puntiVitaMassimi = puntiVitaMassimi;
        this.puntiVitaAttuali = puntiVitaAttuali;
        this.dannoAttaccoBase = dannoAttaccoBase;
        this.livello = livello;
        this.puntiEsperienza = puntiEsperienza;
        this.indiceIncontroCorrente = indiceIncontroCorrente;
    }

    public String getTipoEroe() { return tipoEroe; }
    public String getNomeEroe() { return nomeEroe; }
    public int getPuntiVitaMassimi() { return puntiVitaMassimi; }
    public int getPuntiVitaAttuali() { return puntiVitaAttuali; }
    public int getDannoAttaccoBase() { return dannoAttaccoBase; }
    public int getLivello() { return livello; }
    public int getPuntiEsperienza() { return puntiEsperienza; }
    public int getIndiceIncontroCorrente() { return indiceIncontroCorrente; }
}