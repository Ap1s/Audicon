package audicon.gui.login;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import audicon.gui.baseScreens.SmallScreen;
import audicon.gui.forms.LoginForm;

public class LoginScreen extends SmallScreen {
	
	public LoginScreen() {
		super("Login");
		assembleTopPanel(new JLabel("Login"));
		assembleScreen();
		setVisible(true);
	}
	
	private void assembleScreen() {
		topPanel.add(new LoginForm(this));
	}
}
