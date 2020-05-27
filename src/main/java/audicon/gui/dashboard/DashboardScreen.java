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
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import audicon.db.manager.HistoryManager;
import audicon.functional.bo.Track;
import audicon.functional.bo.User;
import audicon.functional.converter.Converter;
import audicon.functional.extractor.MetaDataExtractor;
import audicon.gui.baseScreens.MainScreen;
import audicon.gui.history.HistoryScreen;
import audicon.gui.baseScreens.*;

public class DashboardScreen extends MainScreen {

	private JPanel userPanel;
	private JPanel actionPanel;
	
	private User user;
	
	private DashboardScreen screen;
	
	public DashboardScreen(final User user) {
		super("AudiCon Dashboard");
		screen = this;
		this.user = user;
		setLayout(new FlowLayout());
		assembleUserPanel();
		assembleActionPanel();
		setVisible(true);
	}
	
	private void assembleUserPanel() {
		userPanel = new JPanel();
		userPanel.setSize(200, 500);
		userPanel.setLayout(new BorderLayout());
		
		JLabel welcomeLabel = new JLabel("<html><h1>Welcome User</h1><p>" + user.getUsername() + "</p></html>");
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
			case ACTION_CONVERT:
				try {
					openConversionFLow();
				} catch (IOException | UnsupportedAudioFileException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} break;
			case ACTION_SEE_HISTORY: openHistoryPage(); break;
			case ACTION_ABOUT: showAboutDialog(); break;
			default: System.err.println("No valid action was choosen");
			}
		}
		
		private void openHistoryPage() {
			screen.dispose();
			new HistoryScreen(user);
		}

		private void openConversionFLow() throws IOException, UnsupportedAudioFileException {
			JFileChooser fileChooser = new JFileChooser();
			int returnVal = fileChooser.showOpenDialog(screen);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				
				//convert file to MP3
				Converter converter = new Converter();
				converter.convertToMp3();
				
				File mp3file = new File("C://Users/Desktop/result.mp3");
				
				// extract meta data
//				String title = MetaDataExtractor.getTrackMetaDataFromByteArray(mp3file).getTitle();
//				String artist = MetaDataExtractor.getTrackMetaDataFromByteArray(mp3file).getArtist();
//				String length = "123";
				
				String title = "test";
				String artist = "testArtist";
				String length = "123";
				
				//write into user history
				Track track = new Track(title, artist, length);
				HistoryManager historyManager = new HistoryManager();
				historyManager.saveHistoryEntry(user.getId(), track);
				
				// give user feedback
				ConversionConfirmPanel.showDialog("C://Users/"+ System.getenv("USERNAME"));
			}
		}

		private void showAboutDialog() {
			String message = 
				"<html><h1>AudiCon v1.0.0</h1>" +
				"<h2>This tool is used to transform WAV files into MO3</h2>" +
				"<h3>Made by Markus Schiller</h3></html>";
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
