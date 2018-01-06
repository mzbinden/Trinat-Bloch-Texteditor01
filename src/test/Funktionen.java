package test;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Funktionen extends JFrame {

	public String name;
	JTextField textField;
	JTextArea textArea;

	public void speichern(String a) throws URISyntaxException {

		namen();

		// String path =
		// (Funktionen.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath())+name+".txt";

		String path = "src/test/" + name + ".java";
		File fi = new File(path);

		// Neues File schreiben
		try (FileWriter fw = new FileWriter(fi)) {
			fw.append(name + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// try (BufferedWriter buffer = new BufferedWriter(new FileWriter(new
		// File("src/test/" + name +".java")))) {

		try (BufferedWriter buffer = new BufferedWriter(new FileWriter(new File(path)))) {

			buffer.write(a);

		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	public void namen() {

		JFrame frame = new JFrame("Namensgebung");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container contentPane = frame.getContentPane();

		frame.pack();
		frame.setSize(500, 140);
		frame.setVisible(true);

		// Regular text field (Row 1)
		textField = new JTextField(10);
		frame.add(textField);
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.append("\nYou have typed " + textField.getText());
				name = textField.getText();
			}
		});
		name = textField.getText();
		System.out.print(name);
	}

	/**
	 * Sets the default Font Size.
	 * <p>
	 */
	public void setDefaultSize(int size) {

		Set<Object> keySet = UIManager.getLookAndFeelDefaults().keySet();
		Object[] keys = keySet.toArray(new Object[keySet.size()]);

		for (Object key : keys) {

			if (key != null && key.toString().toLowerCase().contains("font")) {

				System.out.println(key);
				Font font = UIManager.getDefaults().getFont(key);
				if (font != null) {
					font = font.deriveFont((float) size);
					UIManager.put(key, font);
				}
			}
		}
	}

}
