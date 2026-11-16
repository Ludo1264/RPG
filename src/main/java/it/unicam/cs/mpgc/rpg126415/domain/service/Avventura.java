package it.unicam.cs.mpgc.rpg126415.domain.service;

import it.unicam.cs.mpgc.rpg126415.domain.model.*;

import java.util.List;

public class Avventura {

    private final Eroe eroe;
    private final List<Incontro> incontri;
    private final Combattimento combattimento;
    private int indiceIncontroCorrente;

    public Avventura(Eroe eroe, int indiceIncontroCorrente) {
        if (eroe == null) {
            throw new IllegalArgumentException("L'eroe non può essere nullo");
        }
        this.eroe = eroe;
        this.combattimento = new Combattimento();
        this.incontri = creaIncontriTorre(eroe);

        if (indiceIncontroCorrente < 0 || indiceIncontroCorrente > incontri.size()) {
            throw new IllegalArgumentException("Indice incontro non valido");
        }
        this.indiceIncontroCorrente = indiceIncontroCorrente;
    }

    public Avventura(Eroe eroe) {
        this(eroe, 0);
    }

    private List<Incontro> creaIncontriTorre(Eroe eroe) {
        Ricompensa ricompensaPrimoSigillo = (eroe instanceof Guerriero)
                ? new RicompensaAttacco("Ascia da Guerra", 6)
                : new RicompensaAttacco("Sfera di Fuoco", 8);

        return List.of(
                new Incontro(new Nemico("Goblin", 45, 8, 50), ricompensaPrimoSigillo),
                new Incontro(new Nemico("Scheletro", 75, 13, 70), new RicompensaCura("Pozione Curativa", 40)),
                new Incontro(new Nemico("Signore della Torre (Boss)", 130, 18, 100), null)
        );
    }

    public String giocaTurno() {
        if (isTerminata()) {
            throw new IllegalStateException("L'avventura è già terminata");
        }

        Incontro incontro = getIncontroCorrente();
        Nemico nemico = incontro.getNemico();

        boolean scontroContinua = combattimento.eseguiTurno(eroe, nemico);

        if (!nemico.isVivo()) {
            StringBuilder esito = new StringBuilder();
            esito.append(nemico.getNomeNemico()).append(" sconfitto! (+")
                    .append(nemico.getPuntiEsperienzaRicompensa()).append(" XP)");

            if (incontro.haRicompensa()) {
                incontro.getRicompensa().applicaA(eroe);
                esito.append(" | Ricompensa ottenuta: ")
                        .append(incontro.getRicompensa().getDescrizione());
            }

            indiceIncontroCorrente++;
            return esito.toString();
        }

        if (!scontroContinua) {
            return eroe.getNome() + " è stato sconfitto da " + nemico.getNomeNemico() + ".";
        }

        return eroe.getNome() + " attacca " + nemico.getNomeNemico()
                + " (" + nemico.getPuntiVitaAttualiNemico() + "/" + nemico.getPuntiVitaMassimiNemico() + " HP).";
    }

    public Incontro getIncontroCorrente() {
        if (isCompletata()) {
            return null;
        }
        return incontri.get(indiceIncontroCorrente);
    }

    public boolean isCompletata() {
        return indiceIncontroCorrente >= incontri.size();
    }

    public boolean isSconfitta() {
        return !eroe.isVivo();
    }

    public boolean isTerminata() {
        return isCompletata() || isSconfitta();
    }

    public Eroe getEroe() { return eroe; }
    public int getIndiceIncontroCorrente() { return indiceIncontroCorrente; }
    public int getTotaleIncontri() { return incontri.size(); }
}
