# La Torre dei Tre Sigilli

Gioco di ruolo a turni con interfaccia grafica JavaFX e salvataggio su file JSON, sviluppato per il corso di Metodologie di Programmazione. Il progetto appartiene al package it.unicam.cs.mpgc.rpg126415. Il giocatore crea un eroe, scegliendo tra **Guerriero** e **Mago**, e affronta Goblin, Scheletro e il boss finale.

## Funzionalità

- Creazione di un eroe o caricamento di una partita salvata.
- Combattimenti a turni: l’eroe attacca per primo; se il nemico sopravvive, contrattacca.
- Punti esperienza e avanzamento di livello.
- Ricompense: ascia per il Guerriero, sfera di fuoco per il Mago e pozione curativa dopo lo Scheletro.
- Salvataggio e caricamento della partita in formato JSON.

Il salvataggio conserva le statistiche dell’eroe e l’incontro corrente. Se si salva durante uno scontro, al caricamento il nemico riparte dai suoi punti vita massimi.

## Come eseguire il progetto

### Prerequisiti

- JDK 21, indicato dal toolchain configurato nel progetto.
- Git, per clonare il repository.

Il progetto include Gradle Wrapper 9.1.0, quindi non è necessario installare Gradle separatamente.

### Comandi

Clona il repository e apri la cartella del progetto:

git clone https://github.com/Ludo1264/RPG.git
cd RPG

Su Linux e macOS compila ed esegui l’applicazione con:

./gradlew build
./gradlew run

Su Windows PowerShell usa:

.\gradlew.bat build
.\gradlew.bat run

## Uso di strumenti di AI

Ho utilizzato ChatGPT per:
-chiarimenti su errori di compilazione e configurazione
-suggerimenti architetturali
L'intero progetto è stato comunque ideato, strutturato e sviluppato da me in totale autonomia. 
La Wiki descrive nel dettaglio l'uso dell'AI.