import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScoreboardScreen extends JPanel {
	private List<Player> playersList;
	private JPanel[][] leaderboard;
	private JLabel[] scores;
	public static final Color BLANK_COLOR = Color.decode("#EEEEEE");
	public static final Color BACKGROUND_COLOR = Color.decode("#AAAAAA");
	public static final Color[] colors = {Color.decode("#4D75B6"), Color.decode("#F5C446"), Color.decode("#4BAC65"), Color.decode("#DF4339")};
	public static final JLabel SCOREBOARD_TITLE = new JLabel("SCOREBOARD");
	
	ScoreboardScreen (Gameboard board, List<Player> players) {
		this.setBackground(BACKGROUND_COLOR);
		playersList = players;
		setUpScoreboardBoard();
	}
	
	private void setUpScoreboardBoard () {
		this.setPreferredSize(new Dimension(200, 80*(playersList.size()+1)));
		this.setLayout(new GridLayout((playersList.size()+1), 1));
		leaderboard = new JPanel[playersList.size()+1][1];
		leaderboard[0][0] = new JPanel();
		leaderboard[0][0].setBackground(Color.WHITE);
		leaderboard[0][0].setBorder(BorderFactory.createLineBorder(Color.white, 1, false));
		leaderboard[0][0].add("Scoreboard", SCOREBOARD_TITLE);
		leaderboard[0][0].add(SCOREBOARD_TITLE);
		
		scores = new JLabel[playersList.size()];
		this.add(leaderboard[0][0]);
		for (int j=0;j<playersList.size();j++) {
			leaderboard[1+j][0] = new JPanel();
			scores[j] = new JLabel();
			leaderboard[1+j][0].setBackground(colors[j]);
			leaderboard[1+j][0].setBorder(BorderFactory.createLineBorder(Color.white, 1, false));
			leaderboard[1+j][0].add("Player "+(j+1)+" score: " + playersList.get(j).getScore(), scores[j]);
			scores[j].setForeground(Color.WHITE);
			scores[j].setText("Player "+(j+1)+" score: " + playersList.get(j).getScore());
			leaderboard[1+j][0].add(scores[j]);
			this.add(leaderboard[1+j][0]);
		}
		this.updateScoreboard();
	}
	
	public void updateScoreboard () {
		for (int i=0; i < playersList.size(); i++) {
			orderScores();
			scores[i].setForeground(Color.WHITE);
			scores[i].setText("Player "+(i+1)+" score: "+playersList.get(i).getScore());
		}
		this.revalidate();
		this.repaint();
	}
	
	private void orderScores () {
		int highestScoreIndex = 0;
		int highestScore = 0;
		JPanel[][] copyLeaderboard = leaderboard;
		for (int i=1; i < playersList.size(); i++) {
			if (playersList.get(i-1).getScore() > highestScore) {
				highestScore = playersList.get(i-1).getScore();
			}
			if (playersList.get(i-1).getScore() < playersList.get(i).getScore() 
				&& playersList.get(i).getScore() > highestScore) {
				highestScoreIndex = i;
				highestScore = playersList.get(i).getScore();
			}
		}
	}
}
