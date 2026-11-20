# La Torre dei Tre Sigilli

Applicazione RPG a turni in Java 21 con interfaccia grafica JavaFX e persistenza su file JSON, sviluppata per il corso di Metodologie di Programmazione (matricola `126415`, package `it.unicam.cs.mpgc.rpg126415`).

## Funzionalità principali
- Creazione di un eroe (**Guerriero** o **Mago**) e avvio di una nuova avventura o caricamento di una partita salvata.
- Scalata della Torre attraverso incontri a turni con progressione di livello, esperienza, ricompense e scontro finale con il boss.
- Salvataggio e caricamento dello stato di gioco su file JSON.

## Compilazione ed esecuzione
Dalla cartella principale del progetto, esegui in sequenza:

1. `./gradlew build` (o `.\gradlew.bat build` su Windows)
2. `./gradlew run` (o `.\gradlew.bat run` su Windows)

## Dichiarazione sull'uso di strumenti di AI
Durante lo sviluppo ho utilizzato strumenti di AI generativa come supporto per confrontare idee sulle meccaniche di gioco, sui personaggi e sull'interfaccia, oltre che per chiarire concetti di Java e JavaFX e risolvere problemi. Le decisioni finali sul gioco e sulle modifiche da adottare sono state mie. La Wiki descrive nel dettaglio l'uso dell'AI.