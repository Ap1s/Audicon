package audicon.gui.conversion;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import audicon.gui.baseScreens.ConversionConfirmPanel;
import audicon.gui.baseScreens.SmallScreen;
import audicon.gui.dashboard.DashboardScreen;

public class ConversionProcessScreen extends SmallScreen{

	private JPanel progressBarPanel;
	private JPanel bottomPanel;
	private String filePath;
	
	private ConversionProcessScreen screen;
	
	public ConversionProcessScreen(final String path) {
		super("Conversion is in progress");
		screen = this;
		filePath = path;
		setLayout(new BorderLayout(3,1));
		assembleConversionProcessScreen();
		setVisible(true);
	}
	
	private void assembleConversionProcessScreen() {
		assembleTopPanel(new JLabel("<html><h1>Conversion is in progress</h1></html>"));
		assembleProgressBarPanel();
		assembleBottomPanel();
	}
	
	private void assembleProgressBarPanel() {
		progressBarPanel = new JPanel();
		JProgressBar progressBar = new JProgressBar();
		progressBarPanel.add(progressBar);
		add(progressBarPanel);
	}
	
	private void assembleBottomPanel() {
		bottomPanel = new JPanel();
		JButton abortButton = new JButton("Abort");
		abortButton.addActionListener(new ButtonListener());
		JButton continueButton = new JButton("Continue");
		continueButton.addActionListener(new ButtonListener());
		continueButton.setEnabled(false);
		bottomPanel.add(abortButton);
		bottomPanel.add(continueButton);
		add(bottomPanel);
	}
	
	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			screen.dispose();
			if(e.getActionCommand().equals("Continue")) {
				ConversionConfirmPanel.showDialog(filePath);
			}
		}
		
	}

}
