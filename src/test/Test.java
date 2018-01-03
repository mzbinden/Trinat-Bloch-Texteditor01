package test;

import java.awt.Container;
	import java.awt.GridLayout;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import javax.swing.*;
	
public class Test {

		JTextField textField;
		JTextArea textArea;
		
		public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
		IllegalAccessException, UnsupportedLookAndFeelException {
			// TODO Auto-generated method stub

			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

			new Test().creatFrame();
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

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Button wurde betaetigt.");
			}

		}

		private class SpeichernActionListener implements ActionListener {

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



