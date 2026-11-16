package it.unicam.cs.mpgc.rpg126415.domain.model;

public class Guerriero extends Eroe {

    public Guerriero(String nome) {
        super(nome, 100, 15);
    }

    public Guerriero(String nome, int puntiVitaMassimi, int puntiVitaAttuali,
                     int dannoAttaccoBase, int livello, int puntiEsperienza) {
        super(nome, puntiVitaMassimi, puntiVitaAttuali, dannoAttaccoBase, livello, puntiEsperienza);
    }
}