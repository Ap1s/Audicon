package audicon.gui.history;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import audicon.gui.baseScreens.MainScreen;
import audicon.gui.dashboard.DashboardScreen;

public class HistoryScreen extends MainScreen {

	private JPanel headPanel;
	private JPanel historyTable;
	private JPanel bottomPanel;
	private JTable userHistory;
	
	private HistoryScreen screen;

	public HistoryScreen() {
		super("Your latest conversions");
		screen = this;
		setLayout(new GridLayout(3, 1));
		assembleHeadPanel();
		assembleHistoryTable();
		assembleBottomPanel();
		setVisible(true);
	}

	private void assembleHeadPanel() {
		headPanel = new JPanel();
		headPanel.setLayout(new FlowLayout());
		JLabel headline = new JLabel("<html><h1>User History</h1></html>");
		headPanel.add(headline);
		add(headPanel);
	}
	
	private void assembleHistoryTable() {
		historyTable = new JPanel();
		userHistory = new JTable();
		historyTable.add(userHistory);
		add(historyTable);
	}
	
	private void assembleBottomPanel() {
		bottomPanel = new JPanel();
		JButton backButton = new JButton("Back to dashboard");
		backButton.addActionListener(new BackButtonListener());
		bottomPanel.add(backButton);
		add(bottomPanel);
	}
	
	class BackButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			screen.dispose();
			new DashboardScreen();
		}
	}
}
