package audicon.gui.forms;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import audicon.db.manager.RegisterManager;
import audicon.functional.bo.User;
import audicon.functional.security.AES;
import audicon.functional.validator.RegisterValidator;
import audicon.gui.register.RegisterScreen;
import audicon.gui.welcome.*;

public class RegisterForm extends JPanel {

	private JLabel usernameLabel;
	private JTextField usernameInput;

	private JLabel passwordLabel;
	private JPasswordField passwordInput;

	private JLabel passwordretypedLabel;
	private JPasswordField passwordretypedInput;

	private JButton sendButton;
	private JButton backButton;
	
	private RegisterScreen screen;

	public RegisterForm(final RegisterScreen screen) {
		this.screen = screen;
		initForm();
		assembleForm();
	}

	private void initForm() {
		usernameLabel = new JLabel("Username");
		usernameInput = new JTextField();

		passwordLabel = new JLabel("Password");
		passwordInput = new JPasswordField();

		passwordretypedLabel = new JLabel("Re-type password");
		passwordretypedInput = new JPasswordField();

		sendButton = new JButton("Send");
		sendButton.addActionListener(new ButtonListener());

		backButton = new JButton("Back");
		backButton.addActionListener(new ButtonListener());
		
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}

	private void assembleForm() {
		this.setLayout(new GridLayout(4, 2));
		this.setSize(300, 400);
		this.add(usernameLabel);
		this.add(usernameInput);
		this.add(passwordLabel);
		this.add(passwordInput);
		this.add(passwordretypedLabel);
		this.add(passwordretypedInput);

		this.add(sendButton);
		this.add(backButton);
	}

	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("Send")) {
				String username = usernameInput.getText();
				String password = new String(passwordInput.getPassword());
				String pw_retpyed = new String(passwordretypedInput.getPassword());

				int validationResult = RegisterValidator.validateRegistration(username, password, pw_retpyed);
				String message = null;

				switch (validationResult) {
					case -1:
						message = "Your username can be only have 50 characters!"; break;
					case -2:
						message = "Your password should have at least 8 characters"; break;
					case -3:
						message = "Your entered password do not match"; break;
					default: {
	
						// encryption goes here
						String encryptedString = AES.encrypt(pw_retpyed.getBytes());
	
						// now save user entry
						RegisterManager registerManager = new RegisterManager();
						User user = new User(username, encryptedString);
						registerManager.insert(user);
						message = "Your account was created";
					}
				}

				JOptionPane.showMessageDialog(null, message);

			} else {
				screen.dispose();
				new WelcomeScreen();
			}
		}
	}
}
