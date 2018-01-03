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
| User Storie 1 	|  	|
|-----------------------------------------------------------------------------	|------	|
| Der Benutzer möchte dass sich beim Starten den Programms ein Fenster öffnet 	|  	|
| Aktzeptanzkriterium 	|  	|
| Nach dem Starten des Programms soll sich ein JFrame öffnen 	|  	|
| Storie Points 	| 2 	|
| Priorisierung 	| HOCH 	|

### Ausbaustuffe 2

### Ausbaustuffe 3

### Ausbaustuffe 4


## Release plan mit den Ausbaustufen

## Dokumentation Sprint 1
### Taskliste für die Umsetzung der User Story
### Anreicherung der User Stories für die Umsetzung
### UML Package, Klassen- und Sequenzdiagramm
### Dokumentation wichtiger Code Snippets
package sprint1;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

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

	private class SpeichernActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println("Speichern wurde betaetigt.");
		}
	}
 
 
	public void creatFrame() {
		
		JFrame frame = new JFrame("Mein erstes GUI");
		
		createMenuBar(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container contentPane = frame.getContentPane();

		frame.pack();
		frame.setSize(500, 800);
		frame.setVisible(true);
		
		// Regular text field (Row 1)
			textField = new JTextField(100);
			frame.add(textField);
			textField.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					textArea.append("\nYou have typed " + textField.getText());
				}
			});
	}
 
	private void createMenuBar(JFrame frame) {

		// Menuezeile (JMenuBar) erzeugen und in das Fenster (JFrame) einfuegen
		JMenuBar bar = new JMenuBar();
		frame.setJMenuBar(bar);

		// Menue (JMenu) erzeugen und in die Menuezeile (JMenuBar) eifuegen
		JMenu dateiMenu = new JMenu("Datei");
		bar.add(dateiMenu);

		// Menueeintraege (JMenuItem) erzeugen und dem Menue (JMenu) "Datei"
		// hinzufügen
		JMenuItem speichernItem = new JMenuItem("Speichern");
		speichernItem.addActionListener(new SpeichernActionListener());
		dateiMenu.add(speichernItem);
		
		JMenuItem oeffnenItem = new JMenuItem("Öffnen");
		oeffnenItem.addActionListener(new OpenActionListener());
		dateiMenu.add(oeffnenItem);

		JMenuItem beendenItem = new JMenuItem("Beenden");
		beendenItem.addActionListener(new ExitActionListener());
		dateiMenu.add(beendenItem);
	}
}

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



 
