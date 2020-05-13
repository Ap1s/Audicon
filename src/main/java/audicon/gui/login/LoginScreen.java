package audicon.gui.login;

import javax.swing.JFrame;

import audicon.gui.baseScreens.SmallScreen;
import audicon.gui.forms.LoginForm;

public class LoginScreen extends SmallScreen {
	public LoginScreen() {
		super("Login");
		this.add(new LoginForm());
		setVisible(true);
	}
}
