# FruttoDBManager
Java Swing graphical interface to manage with an Sqlite DB using jaxb

### Structure
Into the standard db there're four tables:
- negozi [shops]: ``CREATE TABLE negozi(idNegozio INTEGER PRIMARY KEY AUTOINCREMENT,nome TEXT, sede TEXT);``
- frutto [fruit]: ``CREATE TABLE frutto(id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, stagionalita TEXT, costo INT);``
- dipendenti [employees]:  ``CREATE TABLE dipendenti(idDipendente INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, cognome TEXT, cellulare TEXT, idNegozio INTEGER, FOREIGN KEY (idNegozio) REFERENCES negozi (idNegozio));``
- fruttiNegozi [fruitsShops]: ``CREATE TABLE fruttiNegozi(id INTEGER PRIMARY KEY AUTOINCREMENT, idFrutto INTEGER, idNegozio INTEGER, scaffale TEXT, FOREIGN KEY (idFrutto) REFERENCES frutto (id), FOREIGN KEY (idNegozio) REFERENCES negozi (idNegozio));``

by the menubar the app can open different dbs.

### How to use
There's a default page to manage all the tables that is StartPage.java.

### Windows version
Probably this doesn't work on Windows. Maybe one day i'll do it.
