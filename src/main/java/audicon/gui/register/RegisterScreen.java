package audicon.gui.register;

import javax.swing.JFrame;
import javax.swing.JLabel;

import audicon.gui.baseScreens.SmallScreen;
import audicon.gui.forms.LoginForm;
import audicon.gui.forms.RegisterForm;

public class RegisterScreen extends SmallScreen {
	public RegisterScreen() {
		super("Registration");
		assembleTopPanel(new JLabel("Registration"));
		assembleScreen();
		setVisible(true);
	}
	
	private void assembleScreen() {
		topPanel.add(new RegisterForm(this));
	}
}
