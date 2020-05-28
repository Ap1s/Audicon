package audicon.gui.baseScreens;

import javax.swing.JOptionPane;

public class ConversionConfirmPanel extends JOptionPane {
	
	public static void showSuccessDialog(final String path) {
		String message = "<html><h1>Process finished successfully</h2>"
				+ "<p>Please find your file here</p>"
				+ "<b>" + path + "</b></html>";
		showMessageDialog(null, message);
	}
	
	public static void showFailureDialog() {
		String message = "<html><h1>Process finished with failures</h2>"
				+ "<p>Please try again</p></html>";
		showMessageDialog(null, message);
	}

}
