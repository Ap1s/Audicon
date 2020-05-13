package audicon.gui.baseScreens;

import javax.swing.JOptionPane;

public class ConversionConfirmPanel extends JOptionPane {
	
	public static void showDialog(final String path) {
		String message = "<html><h1>Process finished successfully</h2>"
				+ "<p>Please find your file here</p>"
				+ "<b>" + path + "</b></html>";
		showConfirmDialog(null, message);
	}

}
