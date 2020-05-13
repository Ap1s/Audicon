package audicon.gui.welcome;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import audicon.gui.baseScreens.SmallScreen;
import audicon.gui.login.LoginScreen;
import audicon.gui.register.RegisterScreen;

public class WelcomeScreen extends SmallScreen {
	
	private JLabel welcomeLabel;
	private JLabel loginLabel;
	private JLabel registerLabel;
	private JButton loginButton;
	private JButton registerButton;
	
	private WelcomeScreen screen;

	
	public WelcomeScreen() {
		super("Welcome to AudiCon");
		welcomeLabel = new JLabel("<html><h1>Welcome to audiCon</h1><br><h2>Login or register ?</h2></html>", SwingConstants.CENTER);
		loginLabel = new JLabel("Login");
		loginButton = new JButton("Login");
		registerLabel = new JLabel("Register");
		registerButton = new JButton("Register");
		
		screen = this;
		
		assembleWelcomeScreen();
		setVisible(true);
	}
	
	private void assembleWelcomeScreen() {
		assembleTopPanel(welcomeLabel);
		assembleBottomPanel(loginLabel, loginButton, registerLabel, registerButton);
		
		registerButton.addActionListener(new ButtonListener());
		loginButton.addActionListener(new ButtonListener());
	}
	
	class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Register")) {
				new RegisterScreen();
			}
			else {
				new LoginScreen();
			}
			screen.dispose();
		}
	}
}
