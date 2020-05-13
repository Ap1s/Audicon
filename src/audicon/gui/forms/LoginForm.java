package audicon.gui.forms;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginForm extends JPanel {
	private JLabel usernameLabel;
	private JTextField usernameInput;
	
	private JLabel passwordLabel;
	private JPasswordField passwordInput;
	
	private JButton sendButton;
	
	public LoginForm() {
		initForm();
		assembleForm();
	}
	
	public void initForm() {
		usernameLabel = new JLabel("Username");
		usernameInput = new JPasswordField();
		
		passwordLabel = new JLabel("Password");
		passwordInput = new JPasswordField();
		
		sendButton = new JButton("Send");
	}
	
	private void assembleForm() {
		this.setSize(300,400);
		this.setLayout(new GridLayout(5,1));
		
		this.add(usernameLabel);
		this.add(usernameInput);
		this.add(passwordLabel);
		this.add(passwordInput);
		
		this.add(sendButton);
	}

}
