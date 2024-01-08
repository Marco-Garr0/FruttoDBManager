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
There's a default page to manage all the tables that is **StartPage.java**.
in the menubar, under **"file"** the **"open..."** item opens a .db file; the **"save in file..."** item writes 
on a selected file or on a new file in six different formats: csv, json, xml, xls, ods, pdf.

### Windows version
Probably this doesn't work on Windows. Maybe one day i'll do it.

### Troubleshooting on Linux
If there is the following error:

``java.sql.SQLException: No suitable driver found for jdbc:sqlite:<db/path>``

try to add before the GenericDAO.connect() ``Class.forName("org.sqlite.JDBC")`` 
the forName() method can cause a **ClassNotFoundException** so remember to catch it.
