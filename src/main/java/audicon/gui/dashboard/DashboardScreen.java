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
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
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
	
	private JPanel topPanel;
	private JPanel middlePanel;
	private JPanel bottomPanel;
	
	private User user;
	
	private DashboardScreen screen;
	
	public DashboardScreen(final User user) {
		super("AudiCon Dashboard");
		screen = this;
		this.user = user;
		setLayout(new GridLayout(3,1));
		assembleTopPanel();
		assembleMiddlePanel();
		assembleBottomPanel();
		setVisible(true);
	}
	
	private void assembleTopPanel() {
		topPanel = new JPanel();
		topPanel.setSize(800, 100);
		topPanel.setLayout(new FlowLayout());
		
		JLabel welcomeLabel = new JLabel("<html><h1>Welcome user <b>" + user.getUsername() + "</b></h1></html>");
		topPanel.add(welcomeLabel);
		this.add(topPanel);
	}
	
	private void assembleMiddlePanel() {
		middlePanel = new JPanel();
		middlePanel.setLayout(null);
		
		ActionPanel topLeftPanel = new ActionPanel();
		topLeftPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		topLeftPanel.setBounds(150, 0, 200, 100);
		JLabel convert = new JLabel("Convert a file");
		convert.addMouseListener(new MouseListener(DashboardAction.ACTION_CONVERT));
		convert.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		topLeftPanel.add(convert);
		middlePanel.add(topLeftPanel);
		
		ActionPanel topRightPanel = new ActionPanel();
		topRightPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		topRightPanel.setBounds(400,0,200,100);
		JLabel conversionHistory = new JLabel("See conversion history");
		conversionHistory.addMouseListener(new MouseListener(DashboardAction.ACTION_SEE_HISTORY));
		conversionHistory.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		topRightPanel.add(conversionHistory);
		middlePanel.add(topRightPanel);
		
		add(middlePanel);
		
	}
	
	private void assembleBottomPanel() {
		bottomPanel = new JPanel();
		bottomPanel.setLayout(null);
		
		
		JButton logout = new JButton("Logout");
		logout.addActionListener(new LogoutListener());
		ActionPanel bottomLeftPanel = new ActionPanel();
		bottomLeftPanel.setBounds(150,0,200,100);
		bottomLeftPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		bottomLeftPanel.add(logout);
		bottomPanel.add(bottomLeftPanel);
		
		ActionPanel bottomRightPanel = new ActionPanel();
		bottomRightPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		bottomRightPanel.setBounds(400,0,200,100);
		JLabel about = new JLabel("About AudiCon");
		about.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		about.addMouseListener(new MouseListener(DashboardAction.ACTION_ABOUT));
		about.setForeground(Color.BLUE.darker());
		bottomRightPanel.add(about);
		bottomPanel.add(bottomRightPanel);
		
		add(bottomPanel);
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
			case ACTION_CONVERT:openConversionFLow(); break;
			case ACTION_SEE_HISTORY: openHistoryPage(); break;
			case ACTION_ABOUT: showAboutDialog(); break;
			default: System.err.println("No valid action was choosen");
			}
		}
		
		private void openHistoryPage() {
			screen.dispose();
			new HistoryScreen(user);
		}

		private void openConversionFLow()  {
			JFileChooser fileChooser = new JFileChooser();
			int returnVal = fileChooser.showOpenDialog(screen);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				
				File mp3file = fileChooser.getSelectedFile(); 
				String filename = mp3file.getName();
		        Pattern pattern = Pattern.compile("\\w*-\\w*.wav");
		        Matcher matcher = pattern.matcher(filename);
		        
		        if(matcher.matches()) {
					//convert file to MP3
					Converter converter = new Converter();
					try {
						converter.convertToMp3(new FileInputStream(mp3file));
						

						
						String artist = filename.split("-")[0];
						String title = filename.split("\\w*-")[1].split(".wav")[0];
						String length = "123";
						
						//write into user history
						Date date = new Date();
						SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
						String stringDate= DateFor.format(date);
						
						Track track = new Track(title, artist, length, stringDate);
						
						HistoryManager historyManager = new HistoryManager();
						historyManager.saveHistoryEntry(user.getId(), track);
						
						// give user feedback
						ConversionConfirmPanel.showSuccessDialog("C://Users/"+ System.getenv("USERNAME") + "/Desktop/");
					} catch (IOException | UnsupportedAudioFileException e) {
						// send error message to user
						ConversionConfirmPanel.showFailureDialog();
						e.printStackTrace();
					}
		        }
		        else {
		        	JOptionPane.showMessageDialog(null, "<html><h1>Please provide a file named</h1><h2>{artist}-{title}.wav");
		        }
			}
		}

		private void showAboutDialog() {
			String message = 
				"<html><h1>AudiCon v1.0.0</h1>" +
				"<h2>This tool is used to transform WAV files into MP3</h2>" +
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
