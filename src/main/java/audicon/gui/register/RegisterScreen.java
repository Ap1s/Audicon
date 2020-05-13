package audicon.gui.register;

import javax.swing.JFrame;

import audicon.gui.baseScreens.SmallScreen;
import audicon.gui.forms.RegisterForm;

public class RegisterScreen extends SmallScreen {
	public RegisterScreen() {
		super("Registration");
		this.add(new RegisterForm());
		setVisible(true);
	}
}
