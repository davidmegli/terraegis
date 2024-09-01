# TerrAegis - Backend

TerrAegis è una piattaforma di crowdfunding progettata per supportare progetti innovativi e sostenibili. Questo repository contiene il codice sorgente del backend dell'applicazione, sviluppato con **Spring Boot** e altre tecnologie Java, con un'architettura basata su API RESTful.

Il frontend del progetto è stato sviluppato separatamente utilizzando **ReactJS**, **TypeScript**, e altre librerie moderne per la creazione di un'interfaccia utente dinamica e responsiva.

## Funzionalità Principali

Il backend di TerrAegis fornisce tutte le funzionalità principali necessarie per la gestione di un'applicazione di crowdfunding, tra cui:

- **Gestione utenti**: Registrazione, login, aggiornamento profilo e gestione delle campagne finanziate dagli utenti.
- **Gestione progetti**: Creazione, modifica, eliminazione e visualizzazione di progetti.
- **Gestione campagne**: Creazione e gestione di campagne di crowdfunding collegate ai progetti.
- **Gestione finanziamenti**: Registrazione dei finanziamenti fatti dagli utenti verso le campagne.
- **Gestione obiettivi di sostenibilità**: Associazione di obiettivi di sostenibilità a progetti specifici.

## Struttura del Progetto

La struttura del progetto segue un'architettura MVC (Model-View-Controller) con l'uso di servizi per la logica di business. Di seguito viene riportata una panoramica delle cartelle principali:

- **/src/main/java/com/terraegis**: Contiene il codice sorgente dell'applicazione.
   - **/controllers**: Contiene i controller REST che gestiscono le richieste HTTP e rispondono con dati JSON.
   - **/models**: Contiene le entità di dominio (User, Project, Campaign, Funding, etc.) che mappano i dati del database.
   - **/repositories**: Contiene le interfacce repository che interagiscono con il database.
   - **/services**: Contiene i servizi che implementano la logica di business.

## Tecnologie Utilizzate

- **Java 17**: Linguaggio di programmazione utilizzato per il backend.
- **Spring Boot**: Framework per la creazione di applicazioni standalone basate su Spring.
- **Spring Data JPA**: Per l'interazione con il database relazionale.
- **H2 Database**: Database relazionale in-memory utilizzato per i test.
- **Maven**: Sistema di gestione dei progetti e delle dipendenze.
- **JUnit**: Framework di testing per il testing unitario.

## API Endpoints

Le API fornite dal backend permettono la gestione completa dei progetti e delle campagne, l'autenticazione degli utenti e molto altro. Gli endpoint principali sono:

- `/api/users/register` - Registra un nuovo utente.
- `/api/users/login` - Effettua il login di un utente esistente.
- `/api/projects/create` - Crea un nuovo progetto.
- `/api/projects/{id}` - Recupera i dettagli di un progetto specifico.
- `/api/campaigns/active` - Ottiene tutte le campagne attive.
- `/api/fundings/create` - Registra un nuovo finanziamento.

Per ulteriori dettagli, consultare la documentazione nella folder 'documents/'.