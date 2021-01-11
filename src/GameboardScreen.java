import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
class GameboardScreen extends JPanel {
	private Gameboard board;
	private JPanel[][] squares;
	public static final Color[] colors = {Color.decode("#4D75B6"), Color.decode("#F5C446"), Color.decode("#4BAC65"), Color.decode("#DF4339")};
	private int SIZE;
	GameboardScreen(Gameboard board) {
		this.board = board;
		this.SIZE = board.getGridSize();
		this.setBorder(BorderFactory.createLineBorder(Color.white, 3, false));
		setupBoardPanel();
	}
	private void setupBoardPanel() {
		this.setPreferredSize(new Dimension(800, 800));
		this.setLayout(new GridLayout(SIZE, SIZE));
		squares = new JPanel[SIZE][SIZE];
		for (int i=0;i<SIZE;i++) {
			for (int j=0;j<SIZE;j++) {
				squares[i][j] = new JPanel();
				squares[i][j].setBackground(Color.decode("#EEEEEE"));
				squares[i][j].setBorder(BorderFactory.createLineBorder(Color.white, 1, false));
				this.add(squares[i][j]);
			}
		}
		this.updateBoard();
	}
	private void updateBoard() {
		int[][] grid = board.getGameboardGrid();
		int[][] stagingGrid = board.getInstantaneousGameboardGrid();
		for (int i=0;i<SIZE;i++) {
			for (int j=0;j<SIZE;j++) {
				if (stagingGrid[j][SIZE - i - 1] == 5 || stagingGrid[j][SIZE - i - 1] == 6) {
					squares[i][j].setBackground(stagingGrid[j][SIZE - i - 1] == 5 ? Color.decode("#00FF00") : Color.decode("#FF0000"));
				} else if (grid[j][SIZE - i - 1] != 0) {
					squares[i][j].setBackground(colors[grid[j][SIZE - i - 1] - 1]);
				}
			}
		}
		//squares[0][0].setBackground(colors[0]);
	}
	public static void main(String[] args) {
		new GameboardScreen(GameTestData.getSmallTestBoard1());
	}
}