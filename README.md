# EPIC ENERGY SERVICES

Occorre realizzare il backend di un sistema CRM per un'azienda fornitrice di energia, denominata "EPIC ENERGY
SERVICES", che vuole gestire i contatti con i propri clienti business.
Il sistema, basato su Web Service REST Spring Boot e database PostgreSQL, deve permettere di gestire un elenco
dei clienti e le relative fatture.

Il sistema, basato su Web Service REST Spring Boot e database PostgreSQL, deve permettere di gestire un elenco dei clienti, che sono caratterizzati dai seguenti dati:
-ragioneSociale
-partitaIva
-email
-dataInserimento
-dataUltimoContatto
-fatturatoAnnuale
-pec
-telefono
-emailContatto
-nomeContatto
-cognomeContatto
-telefonoContatto

Ogni cliente può avere fino a due indirizzi, uno per la sede legale ed uno per la sede operativa.

Un indirizzo è composto da 
-via
-civico
-località
-cap
-comune 

I comuni sono gestiti attraverso un'anagrafica centralizza e sono caratterizzati da un nome e da un riferimento ad una provincia, anch'essa gestita in anagrafica centralizzata e caratterizzata da un nome ed una sigla. 


I clienti possono essere di vario tipo:
PA
SAS
SPA
SRL

Associato ad ogni cliente c'è un insieme di fatture. Le fatture sono caratterizzate dai seguenti dati:
anno : Integer
data : Date
importo : BigDecimal
numero : Integer

Ogni fattura ha uno stato. Gli stati fattura possono essere dinamici, in quanto in base all'evoluzione del business possono essere inseriti nel sistema nuovi stati.

Il back-end deve fornire al front-end tutte le funzioni necessarie a gestire in modo completo (Aggiungere, modificare ed eleiminare)i suddetti elementi.

Deve essere possibile ordinare i clienti per:
Nome
Fatturato annuale
Data di inserimento
Data di ultimo contatto
Provincia della sede legale.

Deve essere possibile filtrare la lista clienti per:
Fatturato annuale
Data di inserimento
Data di ultimo contatto
Parte del nome


Deve essere possibile filtrare le fatture per
Cliente
Stato
Data
Anno
Range di importi

Per gestire in modo efficiente un numero cospicuo di elementi, occorre utilizzare la paginazione.

Prevedere inoltre un sistema di autenticazione e autorizzazione basato su token JWT che permetta a diversi utenti di accedere alle funzioni del backend e di registrarsi al sistema. Un utente è caratterizzato dalle seguenti proprietà :
username
email
password
nome
cognome

Gli utenti possono essere di tipo USER, abilitato alle sole operazioni di lettura, oppure ADMIN, abilitato a tutte le operazioni. Un utente può avere più ruoli.

* Importazione Comuni e Province
Viene fornito un elenco dei comuni in formato CSV (elenco-comuni-italiani.csv), che deve essere importato nel sistema per mezzo di una appositoa procedura Java da eseguire manualmente per popolare il db. Viene fornito inoltre un secondo file (elenco-province-italiane.csv) contenente la corrispondenza tra nome provincia e sigla ed anch'esso deve essere importato ed integrato con le informazioni relative ai comuni.

Contestualmente alla realizzazione del sistema occorre inoltre:
- Realizzare una collection Postman contenente tutte le chiamate alle API del servizio, comprese quelle di autenticazione
- Implementare i principali test con JUnit
