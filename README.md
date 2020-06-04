# audicon
Tool for conversion of media files

## Build information
Maven Build management. 
Via Maven werden zur Laufzeit des Programms über das lokale Maven Repository die Abhängigkeiten zu Hibernate und Mysql geladen und anschließend zu einer JAR kompiliert. 

__Zusätzliche Informationen__ : 
Build your run configuration with the maven goal: clean install java:exec -e -U

## Environment

* Eclipse Build 2020-03
* Java JDK 14.0.1
* Java JRE (build 1.8.0_251-b08)
* DB: mariaDB root@localhost / PW: localhost
* Application-type: Fat-Client
* Used languages: Java, SQL, XML, HTML
* Buildmanagament: Maven
* Object-Relational-Mapper: Hibernate
* GUI-Library: Swing / AWT
* Operating-Systems: Linux , MacOS, Windows, *


## Architektur

### DB-Layer ( audicon.db.*)
* Enthält Entitäten und Datenbank Manager Klassen, die für die Verbindung zur Datenbank zuständig sind
* JPA: Java Persistency API : Eine API zum persistieren ( dauerhaft speichern ) von Entitäten
* Hibernate Framework als Implementierung der JPA.
* Konfiguration sind zu finden in /src/main/resources/hibernate.cfg.xml


### Functional-Layer ( audicon.functional.* )
* EnhÃ¤lt Klassen, die die HauptfunktionalitÃ¤t des Programms abbilden. Zum Beispiel AES Encryption / Decryption, Convertierung oder Validierung des User Inputs

### GUI Layer ( audicon.gui.* ) 
* Enthält Klassen, die die Fenster / Frames und die Formulare enhalten und zur grafischen Benutzerinteraktion dienen
* Formulare sind von ihren Fenstern technisch getrennt

## Weitere Informationen 
* NÃ¼tzliche Informationen zur Extraktion von Meta Daten aus einem MP3 File: 
* https://stackoverflow.com/questions/1645803/how-to-read-mp3-file-tags
* https://stackoverflow.com/questions/10824027/get-the-metadata-of-a-file

( Kommen aber aus Gründen der Einfachheit und Aufgabenstellung in diesem Programm nicht zur Anwendung ) 

