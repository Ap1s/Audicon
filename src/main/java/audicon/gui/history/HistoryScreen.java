package audicon.gui.history;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import audicon.db.entity.TrackEntity;
import audicon.db.manager.HistoryManager;
import audicon.functional.bo.Track;
import audicon.functional.bo.User;
import audicon.gui.baseScreens.MainScreen;
import audicon.gui.dashboard.DashboardScreen;

public class HistoryScreen extends MainScreen {

	private JPanel headPanel;
	private JScrollPane historyTable;
	private JPanel bottomPanel;
	private JTable userHistory;

	private User user;

	private HistoryScreen screen;

	public HistoryScreen(final User user) {
		super("Your latest conversions");
		screen = this;
		this.user = user;
		setLayout(new GridLayout(3, 1));
		assembleHeadPanel();
		assembleHistoryTable();
		assembleBottomPanel();
		setVisible(true);
	}

	private void assembleHeadPanel() {
		headPanel = new JPanel();
		headPanel.setLayout(new FlowLayout());
		JLabel headline = new JLabel("<html><h1>User History of user <b>" + user.getUsername() + "</b></h1></html>");
		headPanel.add(headline);
		add(headPanel);
	}

	private void assembleHistoryTable() {
		String[] columns = { "title", "artist", "length", "date" };
		String[][] data = fillHistory();
		userHistory = new JTable(data, columns);
		historyTable = new JScrollPane(userHistory);
		add(historyTable);
	}

	private void assembleBottomPanel() {
		bottomPanel = new JPanel();
		JButton backButton = new JButton("Back to dashboard");
		backButton.addActionListener(new BackButtonListener());
		bottomPanel.add(backButton);
		add(bottomPanel);
	}

	private String[][] fillHistory() {
		List<TrackEntity> historyEntriesDB = new HistoryManager().getTrackHistoryByUsername(user.getId());
		int size = historyEntriesDB.size();
		String users[][] = new String[size][];

		for (int i = 0; i < size; i++) {
			TrackEntity entity = historyEntriesDB.get(i);
			String[] userArr = new String[] { entity.getTitle(), entity.getArtist(), entity.getLength(), entity.getConversionDate() };
			users[i] = userArr;
		}
		return users;
	}

	class BackButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			screen.dispose();
			new DashboardScreen(user);
		}
	}
}
