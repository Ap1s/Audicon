package audicon.gui.dashboard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import audicon.gui.baseScreens.MainScreen;
import audicon.gui.conversion.ConversionProcessScreen;
import audicon.gui.history.HistoryScreen;

public class DashboardScreen extends MainScreen {

	private JPanel userPanel;
	private JPanel actionPanel;
	
	private DashboardScreen screen;
	
	public DashboardScreen() {
		super("AudiCon Dashboard");
		screen = this;
		setLayout(new FlowLayout());
		assembleUserPanel();
		assembleActionPanel();
		setVisible(true);
	}
	
	private void assembleUserPanel() {
		userPanel = new JPanel();
		userPanel.setSize(200, 500);
		userPanel.setLayout(new BorderLayout());
		
		JLabel welcomeLabel = new JLabel("<html><h1>Welcome User</h1></html>");
		userPanel.add(welcomeLabel, BorderLayout.NORTH);
		
		JButton logoutButton = new JButton("Logout");
		logoutButton.addActionListener(new LogoutListener());
		userPanel.add(logoutButton);
		
		add(userPanel);
	}
	
	private void assembleActionPanel() {
		actionPanel = new JPanel();
		actionPanel.setSize(600,500);
		actionPanel.setLayout(new GridLayout(2,2));
		
		ActionPanel topLeftPanel = new ActionPanel();
		JLabel convert = new JLabel("Convert a file");
		convert.addMouseListener(new MouseListener(DashboardAction.ACTION_CONVERT));
		convert.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		topLeftPanel.add(convert);
		actionPanel.add(topLeftPanel);
		
		ActionPanel topRightPanel = new ActionPanel();
		JLabel conversionHistory = new JLabel("See conversion history");
		conversionHistory.addMouseListener(new MouseListener(DashboardAction.ACTION_SEE_HISTORY));
		conversionHistory.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		topRightPanel.add(conversionHistory);
		actionPanel.add(topRightPanel);
		
		ActionPanel bottomLeftPanel = new ActionPanel();
		JLabel empty = new JLabel("");
		bottomLeftPanel.add(empty);
		actionPanel.add(bottomLeftPanel);
		
		ActionPanel bottomRightPanel = new ActionPanel();
		JLabel about = new JLabel("About AudiCon");
		about.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		about.addMouseListener(new MouseListener(DashboardAction.ACTION_ABOUT));
		about.setForeground(Color.BLUE.darker());
		bottomRightPanel.add(about);
		actionPanel.add(bottomRightPanel);
		
		add(actionPanel);
		
	}
	
	class ActionPanel extends JPanel {
		public ActionPanel() {
			setSize(300,250);
			setLayout(new FlowLayout());
		}
	}
	
	class MouseListener extends MouseAdapter {
		
		private DashboardAction clickedAction;
		
		public MouseListener(final DashboardAction action) {
			this.clickedAction = action;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			switch(clickedAction) {
			case ACTION_CONVERT: openConversionFLow(); break;
			case ACTION_SEE_HISTORY: openHistoryPage(); break;
			case ACTION_ABOUT: showAboutDialog(); break;
			default: System.err.println("No valid action was choosen");
			}
		}
		
		private void openHistoryPage() {
			screen.dispose();
			new HistoryScreen();
		}

		private void openConversionFLow() {
			JFileChooser fileChooser = new JFileChooser();
			int returnVal = fileChooser.showOpenDialog(screen);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				new ConversionProcessScreen(file.getAbsolutePath());
			}
		}

		private void showAboutDialog() {
			String message = 
				"<html><h1>AudiCon v1.0.0</h1>" +
				"<h2>This tool is used to transform MP3 files into WAV</h2>" +
				"<h3>Mady by Markus Schiller</h3></html>";
			JOptionPane.showMessageDialog(null, message);
		}
	}
	
	class LogoutListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			screen.dispose();
		}
		
	}
}
