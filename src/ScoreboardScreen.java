import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ScoreboardScreen extends JPanel {
	private JPanel currentTurnPanel = new JPanel();
	private JPanel scoreboardLabelPanel = new JPanel();
	private JPanel[][] leaderboard;
	private JPanel controlsPanel = new JPanel();
	
	private Player[] playersArray;
	private boolean[] playersOutArray;
	private JLabel[] scores;
	private JLabel controlsLabel = new JLabel("<html>Click to select piece<br/>ENTER: places piece<br/>WASD: moves piece<br/>ARROWS: moves piece<br/>F: flips piece<br/>R: rotates piece</html>");
	private int[] scoreIntArray;
	public static final Color BLANK_COLOR = Color.decode("#EEEEEE");
	public static final Color BACKGROUND_COLOR = Color.decode("#AAAAAA");
	public static final Color[] colors = {Color.decode("#4D75B6"), Color.decode("#F5C446"), Color.decode("#4BAC65"), Color.decode("#DF4339")};
	private static final JLabel SCOREBOARD_TITLE = new JLabel("SCOREBOARD");
	private JLabel currentTurnLabel = new JLabel("Current Turn: ");
	private GameScreen gameScreen;
	private Gameboard gameboard;
	private JFrame mainFrame;
	
	private GridBagConstraints gbc;
	
	ScoreboardScreen (Gameboard board, List<Player> players, GameScreen screen, JFrame frame) {
		this.setBackground(BACKGROUND_COLOR);
		playersArray = new Player[players.size()];
		playersOutArray = new boolean[players.size()];
		for (int i=0; i < playersArray.length; i++) {
			playersArray[i] = players.get(i);
			playersOutArray[i] = false;
		}
		scoreIntArray = new int[playersArray.length];
		scores = new JLabel[playersArray.length];
		gameScreen = screen;
		gameboard = board;
		mainFrame = frame;
		this.setPreferredSize(new Dimension(280, 240+40*(playersArray.length+1)));
		this.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		setUpScoreboardBoard();
	}
	
	private void setUpScoreboardBoard () {
		currentTurnPanel.setBackground(Color.WHITE);
		currentTurnPanel.setBorder((BorderFactory.createLineBorder(Color.BLACK, 1, false)));
		currentTurnPanel.add(currentTurnLabel);
		currentTurnLabel.setText("<html>Current Turn: <br/>"+gameScreen.getCurrentTurn().getName()+"</html>");
		currentTurnLabel.setFont(new Font(currentTurnLabel.getFont().getName(), Font.BOLD, 30));
		currentTurnLabel.setForeground(colors[0]);
		currentTurnPanel.setPreferredSize(new Dimension(280,100));
		gbc.gridy=0;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(currentTurnPanel,gbc);
		
		scoreboardLabelPanel.setBackground(Color.WHITE);
		scoreboardLabelPanel.setBorder((BorderFactory.createLineBorder(Color.WHITE, 1, false)));
		scoreboardLabelPanel.add(SCOREBOARD_TITLE);
		SCOREBOARD_TITLE.setFont(new Font(SCOREBOARD_TITLE.getFont().getName(), Font.BOLD, 20));
		scoreboardLabelPanel.setPreferredSize(new Dimension(280,40));
		gbc.gridy=1;
		this.add(scoreboardLabelPanel,gbc);
		leaderboard = new JPanel[playersArray.length][1];
		for (int j=0;j<playersArray.length;j++) {
			leaderboard[j][0] = new JPanel();
			leaderboard[j][0].setPreferredSize(new Dimension(280,40));
			leaderboard[j][0].setBorder(BorderFactory.createEtchedBorder());
			scores[j] = new JLabel();
			scores[j].setFont(new Font(scores[j].getFont().getName(), Font.PLAIN, 20));
			scores[j].setText(playersArray[j].getName()+" score: "+playersArray[j].getScore());
			leaderboard[j][0].add(scores[j]);
			leaderboard[j][0].setBackground(colors[j]);
			scores[j].setForeground(Color.WHITE);
			gbc.gridy=2+j;
			this.add(leaderboard[j][0],gbc);
		}
		controlsPanel.setBackground(Color.WHITE);
		controlsPanel.setBorder(BorderFactory.createEtchedBorder());
		controlsPanel.add(controlsLabel);
		controlsLabel.setFont(new Font(controlsLabel.getFont().getName(), Font.PLAIN, 16));
		controlsPanel.setPreferredSize(new Dimension(280,140));
		gbc.gridy=3+playersArray.length;
		this.add(controlsPanel,gbc);
		this.updateScoreboard();	
	}
	
	public void updateScoreboard () {
		currentTurnLabel.setForeground(colors[gameScreen.getCurrentTurn().getID()-1]);
		for (int i=0; i < scoreIntArray.length; i++) {
			scoreIntArray[i] = playersArray[i].getScore();
		}
		bubbleSort(scoreIntArray, scores, colors, playersArray);
		currentTurnLabel.setText(("<html>Current Turn: <br/>"+gameScreen.getCurrentTurn().getName()+"</html>"));
		for (int i=0; i < playersArray.length; i++) {
			scores[i].setText(playersArray[i].getName()+" score: "+scoreIntArray[i]);
			scores[i].setForeground(Color.WHITE);
			leaderboard[i][0].add(scores[i]);
			leaderboard[i][0].setBackground(colors[i]);
			leaderboard[i][0].setForeground(Color.WHITE);
			if (playersOutArray[playersArray[i].getID()-1]) {
				scores[i].setText(playersArray[i].getName()+" score (out): "+scoreIntArray[i]);
			}
		}
		this.revalidate();
		this.repaint();
	}
	
	public void setPlayerOut(Player player) {
		playersOutArray[player.getID()-1] = true;
	}
	
	public void gameEnd (List<Player> winners) {
		String winnerString = "";
		for (int i=0; i < winners.size(); i++) {
			winnerString = winnerString + winners.get(i).getName();
		}
		if (winners.size() > 1) {
			currentTurnLabel.setText("TIE!");
		} else {
			currentTurnLabel.setText("<html>Winners: <br/>"+winnerString+"</html>");
			currentTurnLabel.setForeground(colors[0]);
			for (int i=0; i < winners.size(); i++) {
				scores[i].setText(playersArray[i].getName()+ " score: "+scoreIntArray[i]+" WINNER");
			}
		}
		this.setPreferredSize(new Dimension(280, 240+100+40*(playersArray.length+1)));
		JPanel homeButtonPanel = new JPanel();
		JButton homeButton = new JButton();
		homeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mainFrame.setContentPane(new HomeScreen(mainFrame));
				mainFrame.pack();
				mainFrame.revalidate();
				mainFrame.repaint();
			}
		});
		homeButton.setBackground(Color.RED);
		homeButton.setForeground(Color.WHITE);
		homeButton.setFont(new Font(homeButton.getFont().getName(), Font.PLAIN, 20));
		homeButton.setText("QUIT");
		homeButtonPanel.add(homeButton);
		gbc.gridy=4+playersArray.length;
		this.add(homeButtonPanel,gbc);
		
	}
	
	public void bubbleSort(int[] scoreArray, JLabel[] labels, Color[] colorArray, Player[] players) {
		boolean sorted = false;
		int tempScore;
		JLabel tempScoreLabel;
		Color tempColor;
		Player tempPlayer;
		while (!sorted) {
			sorted = true;
			for (int i=0; i < scoreArray.length-1; i++) {
				if (scoreArray[i] < scoreArray[i+1]) {
					tempScore = scoreArray[i];
					scoreArray[i] = scoreArray[i+1];
					scoreArray[i+1] = tempScore;
					
					tempScoreLabel = labels[i];
					labels[i] = labels[i+1];
					labels[i+1] = tempScoreLabel;
					
					tempColor = colorArray[i];
					colorArray[i] = colorArray[i+1];
					colorArray[i+1] = tempColor;
					
					tempPlayer = players[i];
					players[i] = players[i+1];
					players[i+1] = tempPlayer;
					
					sorted = false;
				}
			}
		}
	}
}
