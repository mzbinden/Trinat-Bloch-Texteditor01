package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import java.net.URISyntaxException;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Element;
import javax.swing.text.ElementIterator;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.undo.UndoManager;

public class Test {

	private File file__;
	private JFrame frame__;
	private JTextPane editor__;
	private UndoManager undoMgr__;
	private String pictureButtonName__;
	private JTextField textField;
	private JTextArea textArea;
	private String eingegebenerText;
	private Funktionen buttonaction = new Funktionen();

	private static final String MAIN_TITLE = "Text Editor Bloch - ";
	private static final String ELEM = AbstractDocument.ElementNameAttribute;
	private static final String COMP = StyleConstants.ComponentElementName;

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException, URISyntaxException {
		// TODO Auto-generated method stub

		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		new Test().creatFrame();
	}

	private class ExitActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			System.out.println("Beenden wurde ausgewählt.");
			System.exit(0);
		}
	};

	/*
	 * private class ButtonListener implements ActionListener {
	 * 
	 * public void actionPerformed(ActionEvent e) { // TODO Auto-generated
	 * method stub System.out.println("Button wurde betaetigt."); }
	 * 
	 * }
	 */

	
	public void creatFrame() {

		frame__ = new JFrame(MAIN_TITLE);

		createMenuBar(frame__);
		

		//Container contentPane = frame__.getContentPane();

		frame__.pack();
		frame__.setSize(500, 800);
		frame__.setLocation(80,80);
		frame__.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Regular text field (Row 1)
		/*
		 * textField = new JTextField(10); 
		 * frame__.add(textField);
		 * textField.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { textArea.append("\nYou have typed "
		 * + textField.getText());
		 * 
		 * } }); eingegebenerText = textField.getText();
		 */

		// Try to include a Textpane instead

		editor__ = new JTextPane();
		JScrollPane editorScrollPane = new JScrollPane(editor__);

	
		
		editor__.setDocument(getNewDocument());
		editor__.setEditable(true);
		
		frame__.add(editorScrollPane, BorderLayout.CENTER);
		
		editor__.requestFocusInWindow();
	}

	private StyledDocument getNewDocument() {

		StyledDocument doc = new DefaultStyledDocument();
		doc.addUndoableEditListener(new UndoEditListener());
		return doc;
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
		oeffnenItem.addActionListener(new OpenFileListener());
		dateiMenu.add(oeffnenItem);

		JMenuItem beendenItem = new JMenuItem("Beenden");
		beendenItem.addActionListener(new ExitActionListener());
		dateiMenu.add(beendenItem);

	}

	// Versuch das Speicher Frame aus mas-swing-samples zu übernehmen

	private class SpeichernActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (file__ == null) {

				file__ = chooseFile();

				if (file__ == null) {

					return;
				}
			}

			DefaultStyledDocument doc = (DefaultStyledDocument) getEditorDocument();

			try (OutputStream fos = new FileOutputStream(file__);
					ObjectOutputStream oos = new ObjectOutputStream(fos)) {

				oos.writeObject(doc);
			} catch (IOException ex) {

				throw new RuntimeException(ex);
			}

			setFrameTitleWithExtn(file__.getName());
		}

		private File chooseFile() {

			JFileChooser chooser = new JFileChooser();

			if (chooser.showSaveDialog(frame__) == JFileChooser.APPROVE_OPTION) {

				return chooser.getSelectedFile();
			} else {
				return null;
			}
		}
		// Alter Teil von Matthias durch aufruf von Klasse Funktionen
		/*
		 * public void actionPerformed(ActionEvent e) { // TODO Auto-generated
		 * method stub String a = eingegebenerText; try {
		 * buttonaction.speichern(a); } catch (URISyntaxException e1) { // TODO
		 * Auto-generated catch block e1.printStackTrace(); }
		 * System.out.println("Speichern wurde betaetigt."); }
		 */

	}

	private class OpenFileListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			file__ = chooseFile();

			if (file__ == null) {

				return;
			}

			readFile(file__);
			setFrameTitleWithExtn(file__.getName());
		}

		private File chooseFile() {

			JFileChooser chooser = new JFileChooser();

			if (chooser.showOpenDialog(frame__) == JFileChooser.APPROVE_OPTION) {

				return chooser.getSelectedFile();
			} else {
				return null;
			}
		}

		private void readFile(File file) {

			StyledDocument doc = null;

			try (InputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis)) {

				doc = (DefaultStyledDocument) ois.readObject();
			} catch (FileNotFoundException ex) {

				JOptionPane.showMessageDialog(frame__, "Input file was not found!");
				return;
			} catch (ClassNotFoundException | IOException ex) {

				throw new RuntimeException(ex);
			}

			editor__.setDocument(doc);
			doc.addUndoableEditListener(new UndoEditListener());
			// applyFocusListenerToPictures(doc);
		}
		/*
		 * private void applyFocusListenerToPictures(StyledDocument doc) {
		 * 
		 * ElementIterator iterator = new ElementIterator(doc); Element element;
		 * 
		 * while ((element = iterator.next()) != null) {
		 * 
		 * AttributeSet attrs = element.getAttributes();
		 * 
		 * if (attrs.containsAttribute(ELEM, COMP)) {
		 * 
		 * JButton picButton = (JButton) StyleConstants.getComponent(attrs);
		 * picButton.addFocusListener(new PictureFocusListener()); } } }
		 */
	}

	private void setFrameTitleWithExtn(String titleExtn) {

		frame__.setTitle(MAIN_TITLE + titleExtn);
	}

	private class UndoEditListener implements UndoableEditListener {

		@Override
		public void undoableEditHappened(UndoableEditEvent e) {

			undoMgr__.addEdit(e.getEdit()); // remember the edit
		}
	}

	// Unimportant at the moment !
	/*
	 * private class PictureFocusListener implements FocusListener {
	 * 
	 * @Override public void focusGained(FocusEvent e) {
	 * 
	 * JButton button = (JButton) e.getComponent(); button.setBorder(new
	 * LineBorder(Color.GRAY)); pictureButtonName__ = button.getName(); }
	 * 
	 * @Override public void focusLost(FocusEvent e) {
	 * 
	 * ((JButton) e.getComponent()).setBorder(new LineBorder(Color.WHITE)); } }
	 */

	private StyledDocument getEditorDocument() {

		StyledDocument doc = (DefaultStyledDocument) editor__.getDocument();
		return doc;
	}
}
