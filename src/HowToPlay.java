import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class HowToPlay extends JPanel {
	private JPanel titlePanel = new JPanel();
	
	private JLabel titleLabel = new JLabel("HOW TO PLAY");
	
	private final JTextArea rules = new JTextArea(10,30);
	private final String rulesLabel = "Objective: Try to fit as many of your squares on the board as you can.\n\n"
			+ "Playing the Game\n1. The first piece placed by each player must be on their corner of the board"
			+ "\n2. Players take turns laying down one piece at a time. Each new piece must touch at least one other"
			+ "other piece of the same color, but only at the corners. Pieces of the same color can never touch along a "
			+ "side. There are no restrictions on how pieces of different colors may contact each other"
			+ "\n3. Whenever a player is unable to place a piece on the board, that player must pass their turn."
			+ "\n4. The game ends when no player can place any more pieces."
			+ "\n\nScoring\nThe player with the highest number of squares covered up wins the game"
			+ "\n\nControls\n1. Use WASD or the arrow keys to move around the pieces."
			+ "\n2. Press on the F key to flip the piece."
			+ "\n3. Press on the R key to rotate the piece.";;
	private final JScrollPane scroll = new JScrollPane(rules, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	GridBagConstraints gbc = new GridBagConstraints();
	
	private JFrame frame;
	HowToPlay(JFrame frame) {
		this.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		setUpHowToPlay();
	}
	
	private void setUpHowToPlay () {
		titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD,60));
		titleLabel.setForeground(Color.BLACK);
		titlePanel.add(titleLabel);
		gbc.gridy=0;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(titlePanel, gbc);
		rules.setText(rulesLabel);
		rules.setFont(new Font(rules.getFont().getName(), Font.PLAIN, 30));
		rules.setLineWrap(true);
		rules.setWrapStyleWord(true);
		gbc.gridy=1;
		this.add(scroll, gbc);
	}
}
