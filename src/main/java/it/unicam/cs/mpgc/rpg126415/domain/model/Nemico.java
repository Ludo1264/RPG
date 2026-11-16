package it.unicam.cs.mpgc.rpg126415.domain.model;

public class Nemico {

    private int puntiVitaAttualiNemico;
    private int puntiVitaMassimiNemico;
    private String nomeNemico;
    private int dannoAttaccoBaseNemico;
    private int puntiEsperienzaRicompensa;

    public Nemico(String nomeNemico, int puntiVitaMassimiNemico, int dannoAttaccoBaseNemico, int puntiEsperienzaRicompensa){
        if (puntiVitaMassimiNemico <= 0) {
            throw new IllegalArgumentException("I punti vita massimi devono essere maggiori di zero");}

        if (dannoAttaccoBaseNemico <= 0) {
            throw new IllegalArgumentException("Il danno base deve essere maggiore di zero");}

        if (puntiEsperienzaRicompensa < 0) {
            throw new IllegalArgumentException("I punti esperienza della ricompensa non possono essere negativi");}

        if (nomeNemico == null) {
            throw new IllegalArgumentException("Il nome del nemico è nullo");}

        this.nomeNemico = nomeNemico;
        this.puntiVitaMassimiNemico = puntiVitaMassimiNemico;
        this.puntiVitaAttualiNemico = puntiVitaMassimiNemico;
        this.dannoAttaccoBaseNemico = dannoAttaccoBaseNemico;
        this.puntiEsperienzaRicompensa = puntiEsperienzaRicompensa;
    }

    public void subisciDanno(int numeroDanni){
        if (numeroDanni < 0) {
            throw new IllegalArgumentException("Il danno non può essere negativo");
        }
        if (puntiVitaAttualiNemico > numeroDanni) {
            puntiVitaAttualiNemico -= numeroDanni;
        } else {
            puntiVitaAttualiNemico = 0;
        }
    }

    public boolean isVivo() {
        return puntiVitaAttualiNemico > 0;
    }

    public int getPuntiVitaAttualiNemico() {return puntiVitaAttualiNemico;}

    public int getPuntiVitaMassimiNemico() {return puntiVitaMassimiNemico;}

    public String getNomeNemico() {return nomeNemico;}

    public int getDannoAttaccoBaseNemico() {return dannoAttaccoBaseNemico;}

    public int getPuntiEsperienzaRicompensa() {return puntiEsperienzaRicompensa;}

    public int attacca() { return dannoAttaccoBaseNemico;}

}
