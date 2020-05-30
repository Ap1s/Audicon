# audicon
Tool for conversion of media files

## Build information
Build your run configuration with the maven goal: clean install java:exec -e -U

## Environment

* Eclipse Build 2020-03
* Java JDK 14.0.1
* Java JRE (build 1.8.0_251-b08)
* DB: mariaDB root@localhost / PW: localhost

## Architektur

### DB-Layer ( audicon.db.*)
* Enth�lt Entit�ten und Datenbank Manager Klassen, die f�r die Verbindung zur Datenbank zust�ndig sind

### Functional-Layer ( audicon.functional.* )
* Enh�lt Klassen, die die Hauptfunktionalit�t des Programms abbilden. Zum Beispiel AES Encryption / Decryption, Convertierung oder Validierung des User Inputs

### GUI Layer ( audicon.gui.* ) 
* Enth�lt Klassen, die die Fenster / Frames und die Formulare enhalten und zur grafischen Benutzerinteraktion dienen

## Weitere Informationen 
* N�tzliche Informationen zur Extraktion von Meta Daten aus einem MP3 File: 
* https://stackoverflow.com/questions/1645803/how-to-read-mp3-file-tags
* https://stackoverflow.com/questions/10824027/get-the-metadata-of-a-file

( Kommen aber aus Gr�nden der Einfachheit und Aufgabenstellung in diesem Programm nicht zur Anwendung ) 
