package audicon.gui.baseScreens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SmallScreen extends JFrame {

	protected JPanel topPanel;
	protected JPanel bottomPanel;
	
	private JPanel rightBottomPanel;
	private JPanel leftBottomPanel;
	
	public SmallScreen(final String title) {
		super(title);
		this.setSize(300,400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		this.setResizable(false);
	}
	
	protected void assembleTopPanel(final JLabel pageHeader) {
		topPanel = new JPanel();
		topPanel.setSize(300,200);
		topPanel.setLayout(new BorderLayout());
		topPanel.add(pageHeader, BorderLayout.NORTH);;
		this.add(topPanel, BorderLayout.NORTH);
	}
	
	protected void assembleBottomPanel(
			final JLabel bottomLeftLabel, final JButton bottomLeftButton,
			final JLabel bottomRightLabel, final JButton bottomRightButton
		) {
		bottomPanel = new JPanel();
		bottomPanel.setSize(300,200);
		bottomPanel.setLayout(new BorderLayout());
		
		rightBottomPanel = new JPanel();
		rightBottomPanel.setLayout(new BorderLayout());
		rightBottomPanel.setSize(150, 200);
		rightBottomPanel.add(bottomRightLabel);
		rightBottomPanel.add(bottomRightButton);
		
		leftBottomPanel = new JPanel();
		leftBottomPanel.setSize(150,200);
		leftBottomPanel.setLayout(new BorderLayout());
		leftBottomPanel.add(bottomLeftLabel);
		leftBottomPanel.add(bottomLeftButton);

		bottomPanel.add(leftBottomPanel, BorderLayout.WEST);
		bottomPanel.add(rightBottomPanel, BorderLayout.EAST);
		
		this.add(bottomPanel, BorderLayout.SOUTH);
	}

}
