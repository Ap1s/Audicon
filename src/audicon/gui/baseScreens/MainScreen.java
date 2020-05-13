package audicon.gui.baseScreens;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MainScreen extends JFrame {

	public MainScreen(final String title) {
		super(title);
		this.setSize(800,500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}

}
