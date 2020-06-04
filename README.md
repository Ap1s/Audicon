# audicon
Tool for conversion of media files

## Build information
Maven Build management. 
Via Maven werden zur Laufzeit des Programms ¸ber das lokale Maven Repository die Abh‰ngigkeiten zu Hibernate und Mysql geladen und anschlieﬂend zu einer JAR kompiliert. 

__Zus‰tzliche Informationen__ : 
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
<<<<<<< HEAD
* Enth‰lt Entit‰ten und Datenbank Manager Klassen, die f¸r die Verbindung zur Datenbank zust‰ndig sind
* JPA: Java Persistency API : Eine API zum persistieren ( dauerhaft speichern ) von Entit‰ten
* Hibernate Framework als Implementierung der JPA.
* Konfiguration sind zu finden in /src/main/resources/hibernate.cfg.xml
=======
* Enth√§lt Entit√§ten und Datenbank Manager Klassen, die f√ºr die Verbindung zur Datenbank zust√§ndig sind
>>>>>>> branch 'master' of https://github.com/neo4share/audicon.git

### Functional-Layer ( audicon.functional.* )
* Enh√§lt Klassen, die die Hauptfunktionalit√§t des Programms abbilden. Zum Beispiel AES Encryption / Decryption, Convertierung oder Validierung des User Inputs

### GUI Layer ( audicon.gui.* ) 
<<<<<<< HEAD
* Enth‰lt Klassen, die die Fenster / Frames und die Formulare enhalten und zur grafischen Benutzerinteraktion dienen
* Formulare sind von ihren Fenstern technisch getrennt
=======
* Enth√§lt Klassen, die die Fenster / Frames und die Formulare enhalten und zur grafischen Benutzerinteraktion dienen
>>>>>>> branch 'master' of https://github.com/neo4share/audicon.git

## Weitere Informationen 
* N√ºtzliche Informationen zur Extraktion von Meta Daten aus einem MP3 File: 
* https://stackoverflow.com/questions/1645803/how-to-read-mp3-file-tags
* https://stackoverflow.com/questions/10824027/get-the-metadata-of-a-file

<<<<<<< HEAD
( Kommen aber aus Gr¸nden der Einfachheit und Aufgabenstellung in diesem Programm nicht zur Anwendung ) 
=======
( Kommen aber aus Gr√ºnden der Einfachheit und Aufgabenstellung in diesem Programm nicht zur Anwendung ) 


>>>>>>> branch 'master' of https://github.com/neo4share/audicon.git
