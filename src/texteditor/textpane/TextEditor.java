package texteditor.textpane;

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import javax.swing.text.StyledDocument;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

public class TextEditor {

	private JFrame frame__;
	private JTextPane editor__;
	private File file__;
	private String Text;

	private static final String MAIN_TITLE = "Texteditor test - ";
	private static final String DEFAULT_FONT_FAMILY = "SansSerif"; // !eventuell
																	// nicht
																	// möglich!
	private static final int DEFAULT_FONT_SIZE = 18;

	public static void main(String[] args) throws Exception {

		UIManager.put("TextPane.font", new Font(DEFAULT_FONT_FAMILY, Font.PLAIN, DEFAULT_FONT_SIZE));
		UIManager.setLookAndFeel(new NimbusLookAndFeel()); // ändern des Look
															// and Feels

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {

				new TextEditor().createAndShowGUI();
			}
		});
	}

	private void createAndShowGUI() {

		frame__ = new JFrame();
		setFrameTitleWithExtn("Neue Datei");
		editor__ = new JTextPane();
		editor__.setContentType("text/plain");

		JScrollPane editorScrollPane = new JScrollPane(editor__);

		editor__.setDocument(getNewDocument());

		new EditButtonActionListener();

		frame__.add(editorScrollPane, BorderLayout.CENTER);

		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("Datei");
		fileMenu.setMnemonic(KeyEvent.VK_D);

		JMenuItem newItem = new JMenuItem("Neu");
		newItem.setMnemonic(KeyEvent.VK_N);
		newItem.addActionListener(new NewFileListener());
		JMenuItem openItem = new JMenuItem("Oeffnen...");
		openItem.setMnemonic(KeyEvent.VK_O);
		openItem.addActionListener(new OpenFileListener());
		JMenuItem saveItem = new JMenuItem("Speichern (...)");
		saveItem.setMnemonic(KeyEvent.VK_S);
		saveItem.addActionListener(new SaveFileListener());
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.setMnemonic(KeyEvent.VK_X);
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(Text);
				System.exit(0);
			}
		});

		fileMenu.add(newItem);
		fileMenu.addSeparator();
		fileMenu.add(openItem);
		fileMenu.add(saveItem);

		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		menuBar.add(fileMenu);
		frame__.setJMenuBar(menuBar);

		frame__.setSize(900, 600);
		frame__.setLocation(80, 80);
		frame__.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame__.setVisible(true);

		editor__.requestFocusInWindow();

		Text = editor__.getText();

	}

	private void setFrameTitleWithExtn(String titleExtn) {

		frame__.setTitle(MAIN_TITLE + titleExtn);
	}

	private StyledDocument getNewDocument() {

		StyledDocument doc = new DefaultStyledDocument();
		// doc.addUndoableEditListener(new UndoEditListener());
		return doc;
	}

	/*
	 * Returns a collection of Font names that are available from the system
	 * fonts and are matched with the desired font list (FONT_LIST).
	 */

	private class EditButtonActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			editor__.requestFocusInWindow();
		}
	}

	private StyledDocument getEditorDocument() {

		StyledDocument doc = (DefaultStyledDocument) editor__.getDocument();
		return doc;
	}

	private class NewFileListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			initEditorAttributes();
			editor__.setDocument(getNewDocument());
			file__ = null;
			setFrameTitleWithExtn("New file");
		}

		private void initEditorAttributes() {

			AttributeSet attrs1 = editor__.getCharacterAttributes();
			SimpleAttributeSet attrs2 = new SimpleAttributeSet(attrs1);
			attrs2.removeAttributes(attrs1);
			editor__.setCharacterAttributes(attrs2, true);
		}
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
			// doc.addUndoableEditListener(new UndoEditListener());
			// applyFocusListenerToPictures(doc);
		}

	}

	private class SaveFileListener implements ActionListener {

		@Override
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
	}

	/*
	 * create a function to get the text from a text file and set it into a
	 * JTextPane
	 */
	public static void TextFromFile(JTextPane tp) {
		try {
			// the file path
			String path = "C:\\Users\\samsng\\Desktop\\TextFile.txt";
			File file = new File(path);
			FileReader fr = new FileReader(file);
			while (fr.read() != -1) {
				tp.read(fr, null);
			}
			fr.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}