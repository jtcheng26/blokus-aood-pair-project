import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ScoreboardScreen extends JPanel {
	private Player[] playersArray;
	private JPanel[][] leaderboard;
	private JLabel[] scores;
	private int[] scoreIntArray;
	public static final Color BLANK_COLOR = Color.decode("#EEEEEE");
	public static final Color BACKGROUND_COLOR = Color.decode("#AAAAAA");
	public static final Color[] colors = {Color.decode("#4D75B6"), Color.decode("#F5C446"), Color.decode("#4BAC65"), Color.decode("#DF4339")};
	private static final JLabel SCOREBOARD_TITLE = new JLabel("SCOREBOARD");
	private JLabel currentTurnLabel = new JLabel("Current Turn: ");
	private GameScreen gameScreen;
	
	ScoreboardScreen (Gameboard board, List<Player> players, GameScreen screen) {
		this.setBackground(BACKGROUND_COLOR);
		playersArray = new Player[players.size()];
		for (int i=0; i < playersArray.length; i++) {
			playersArray[i] = players.get(i);
		}
		scoreIntArray = new int[playersArray.length];
		scores = new JLabel[playersArray.length];
		gameScreen = screen;
		setUpScoreboardBoard();
	}
	
	private void setUpScoreboardBoard () {
		this.setPreferredSize(new Dimension(280, 120+80*(playersArray.length+1)));
		this.setLayout(new GridLayout((playersArray.length+2), 1));
		leaderboard = new JPanel[playersArray.length+2][1];
		leaderboard[0][0] = new JPanel();
		leaderboard[0][0].setBackground(Color.WHITE);
		leaderboard[0][0].setBorder(BorderFactory.createLineBorder(Color.white, 1, false));
		leaderboard[0][0].add(currentTurnLabel);
		currentTurnLabel.setText("<html>Current Turn: <br/>"+gameScreen.getCurrentTurn().getName()+"</html>");
		currentTurnLabel.setFont(new Font(currentTurnLabel.getFont().getName(), Font.BOLD, 30));
		currentTurnLabel.setForeground(colors[0]);
		
		leaderboard[1][0] = new JPanel();
		leaderboard[1][0].setBackground(Color.WHITE);
		leaderboard[1][0].setBorder(BorderFactory.createLineBorder(Color.white, 1, false));
		leaderboard[1][0].add(SCOREBOARD_TITLE);
		SCOREBOARD_TITLE.setFont(new Font(SCOREBOARD_TITLE.getFont().getFontName(), Font.BOLD, 20));
		SCOREBOARD_TITLE.setText("SCOREBOARD");
		this.add(leaderboard[0][0]);
		this.add(leaderboard[1][0]);
		
		for (int j=0;j<playersArray.length;j++) {
			leaderboard[2+j][0] = new JPanel();
			leaderboard[2+j][0].setPreferredSize(new Dimension(200,80));
			scores[j] = new JLabel();
			scores[j].setFont(new Font(scores[j].getFont().getName(), Font.PLAIN, 20));
			scores[j].setText("Player "+playersArray[j].getID()+" score: "+playersArray[j].getScore());
			leaderboard[2+j][0].add(scores[j]);
			leaderboard[2+j][0].setBackground(colors[j]);
			scores[j].setForeground(Color.WHITE);
			this.add(leaderboard[2+j][0]);
		}
		
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
			scores[i].setText("Player "+playersArray[i].getID()+" score: "+scoreIntArray[i]);
			scores[i].setForeground(Color.WHITE);
			leaderboard[2+i][0].add(scores[i]);
			leaderboard[2+i][0].setBackground(colors[i]);
			leaderboard[2+i][0].setForeground(Color.WHITE);
		}
		this.revalidate();
		this.repaint();
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
