package audicon.gui.forms;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import audicon.db.entity.UserEntity;
import audicon.db.manager.LoginManager;
import audicon.functional.bo.User;
import audicon.gui.dashboard.DashboardScreen;

public class LoginForm extends JPanel {
	private JLabel usernameLabel;
	private JTextField usernameInput;
	
	private JLabel passwordLabel;
	private JPasswordField passwordInput;
	
	private JButton sendButton;
	private LoginForm screen;
	
	public LoginForm() {
		initForm();
		assembleForm();
		screen = this;
	}
	
	public void initForm() {
		usernameLabel = new JLabel("Username");
		usernameInput = new JTextField();
		
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
		
		sendButton.addActionListener(new SendButtonListener());
		this.add(sendButton);
	}
	
	class SendButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			LoginManager loginManager = new LoginManager();
			
			String username = usernameInput.getText();
			String password = new String(passwordInput.getPassword());
			UserEntity validationResult = loginManager.login(username, password);
			
			
			if(validationResult != null) {
				new DashboardScreen(new User(validationResult.getId(), validationResult.getUsername()));
			}
			else {
				JOptionPane.showMessageDialog(null, "<html><h1>Username or password is wrong.</h1><p>Please try again</p></html>");
			}
		}
	}

}
