# trinat-bloch-texteditor

Das Projekt wurde durch EyHoCk, felixleitlein, G3r0m3, mzbinden und Flip87 erstellt.


## Inhaltsverzeichnis
- [Einleitung](#einleitung)
- [Build-Anleitung](#build-anleitung)
- [Kurze Bedienungsanleitung ](#kurze-bedienungsanleitung)
- [User Stories](#user-stories)
- [Release plan mit den Ausbaustufen](#release-plan-mit-den-ausbaustufen)
- [Dokumentation Sprint 1](#dokumentation-sprint-1)
  * [Taskliste für die Umsetzung der User Story](#tasklistet-für-die-umsetzun-der-user-story)
  * [Anreicherung der User Stories für die Umsetzung](#anreicherung-der-user-stories-für-die-umsetzung)
  * [UML Package, Klassen und Sequenzdiagramm](#uml-package,-klassen-und-sequenzdiagramm)
  * [Dokumentation wichtiger Code Snippets](#dokumentation-wichtiger-code-snippets)
  * [Herleitung der Testfälle aus den Akzeptanzkriterien der User Stories](#herleitung-der-testfälle-aus-den-azeptanzkriterien-der-user-stories)
- [Dokumentation Sprint 2](#dokumentation-sprint-2)
  * [Taskliste für die Umsetzung der User Story](#tasklistetfür-die-Umsetzun-der-User-Story)
  * [Anreicherung der User Stories für die Umsetzung](#anreicherung-der-user-stories-für-die-umsetzung)
  * [UML Package, Klassen und Sequenzdiagramm](#uml-package,-klassen-und-sequenzdiagramm)
  * [Dokumentation wichtiger Code Snippets](#dokumentation-wichtiger-code-snippets)
  * [Herleitung der Testfälle aus den Akzeptanzkriterien der User Stories](#herleitung-der-testfälle-aus-den-azeptanzkriterien-der-user-stories)
## Einleitung

Hierbei handelt es sich um eine Gruppenarbeit welche im Rahmen der Kurse Programmieren II – JAVA und Software Engineering unseres Studiengangs Mechatronik Trinational, im dritten Semester anzufertigen war.

Zweck dieses Projekts ist es die erlernten Programmierkenntnisse und die Prinzipien des Software Engineering anzuwenden.

### Ziele
Es soll eine kleines Programm erstellt werden mit welchem eine einfache Textverarbeitung möglich ist. Die Verwendung des Programms soll auf dem Desktop eines MAC, Windows oder Linux Computer ausgeführt werden können. 
### Randbedingungen
 * Die Programmierung der Anwendung soll in JAVA erfolgen.
 * Die Klassen des Programms sollen über die entsprechenden Unit-Tests verfügen.
 * Das Projekt soll auf GitHub veröffentlich werden.
 * Die Buildautomatisierung soll mit MAVEN erfolgen.
 * Das Programm soll mittels einer dafür erstellten JAR-Datei und dem Befehl java-jar xxxxx.jar ausgeführt werden können.

 
## Build-Anleitung 
### Vorausgesetzungen
* Korrekte Installation von Eclipse.
* Richtig gesetztes Java Home Verzeichnis.
* Korrekte Installation von Maven.
* Korrekte Installation von Git.


## Kurze Bedienungsanleitung 

## User Stories 
### Ausbaustuffe 1
| User Story 1 	| Priorisierung 	| Story Point 	| Aktzeptanzkriterium 	|
|----------------------------------------------------------------------------	|:-------------:	|------------:	|-------------------------------------------------------	|
|  	| HOCH 	| 2 	|  	|
| Der Benutzer möchte das sich beim starten des Programms ein Fenster öffnet 	|  	|  	| Nach dem Starten des Programms öffnet sich ein JFrame 	|

### Ausbaustuffe 2

### Ausbaustuffe 3

### Ausbaustuffe 4


## Release plan mit den Ausbaustufen

## Dokumentation Sprint 1
### Taskliste für die Umsetzung der User Story
### Anreicherung der User Stories für die Umsetzung
### UML Package, Klassen- und Sequenzdiagramm
### Dokumentation wichtiger Code Snippets
#### Main Klasse :-)
##### Bibliotheken Imports
```
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
```
#### Erstellen des Textfields, Action und ButtonListener
```
public class Sprint {

	JTextField textField;
	JTextArea textArea;
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
	IllegalAccessException, UnsupportedLookAndFeelException {
		// TODO Auto-generated method stub

		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		new Sprint().creatFrame();
	}



	private class OpenActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			System.out.println("Oeffnen wurde ausgewählt.");

		}
	};

	private class ExitActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			System.out.println("Beenden wurde ausgewählt.");
			System.exit(0);
		}
	};

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println("Button wurde betaetigt.");
		}

	}
```

### Herleitung der Testfälle aus den Akzeptanzkriterien der User Stories

## Dokumentation Sprint 2 
### Taskliste für die Umsetzung der User Story
### Anreicherung der User Stories für die Umsetzung
### UML Package, Klassen- und Sequenzdiagramm
### Dokumentation wichtiger Code Snippets
### Herleitung der Testfälle aus den Akzeptanzkriterien der User Stories

## Optional: Dokumentation Sprint 3 
### Taskliste für die Umsetzung der User Story
### Anreicherung der User Stories für die Umsetzung
### UML Package, Klassen- und Sequenzdiagramm
### Dokumentation wichtiger Code Snippets
### Herleitung der Testfälle aus den Akzeptanzkriterien der User Stories

## Optional: Dokumentation Sprint 4
### Taskliste für die Umsetzung der User Story
### Anreicherung der User Stories für die Umsetzung
### UML Package, Klassen- und Sequenzdiagramm
### Dokumentation wichtiger Code Snippets
### Herleitung der Testfälle aus den Akzeptanzkriterien der User Stories



 
