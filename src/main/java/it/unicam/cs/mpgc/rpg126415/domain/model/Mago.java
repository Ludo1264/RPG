package it.unicam.cs.mpgc.rpg126415.domain.model;

public class Mago extends Eroe {

    public Mago(String nome) {
        super(nome, 70, 20);
    }

    public Mago(String nome, int puntiVitaMassimi, int puntiVitaAttuali,
                int dannoAttaccoBase, int livello, int puntiEsperienza) {
        super(nome, puntiVitaMassimi, puntiVitaAttuali, dannoAttaccoBase, livello, puntiEsperienza);
    }
}