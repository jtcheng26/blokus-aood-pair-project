import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
class GameboardScreen extends JPanel {
	private Gameboard board;
	private JPanel[][] squares;
	public static final Color[] colors = {Color.decode("#4D75B6"), Color.decode("#F5C446"), Color.decode("#4BAC65"), Color.decode("#DF4339")};
	public static final Color[] lights_colors = {Color.decode("#809dcb"), Color.decode("#fae09e"), Color.decode("#83c996"), Color.decode("#ed9791")};
	public static final Color BLANK_COLOR = Color.decode("#EEEEEE");
	public static final Color BACKGROUND_COLOR = Color.decode("#AAAAAA");
	private int SIZE;
	GameboardScreen(Gameboard board) {
		this.board = board;
		this.SIZE = board.getGridSize();
		this.setBorder(BorderFactory.createLineBorder(Color.decode("#9A9A9A"), 3, false));
		this.setBackground(BACKGROUND_COLOR);
		setupBoardPanel();
	}
	private void setupBoardPanel() {
		this.setPreferredSize(new Dimension(800, 800));
		this.setLayout(new GridLayout(SIZE, SIZE));
		squares = new JPanel[SIZE][SIZE];
		for (int i=0;i<SIZE;i++) {
			for (int j=0;j<SIZE;j++) {
				squares[i][j] = new JPanel();
				squares[i][j].setBackground(BLANK_COLOR);
				squares[i][j].setBorder(BorderFactory.createLineBorder(Color.white, 1, false));
				this.add(squares[i][j]);
			}
		}
		this.updateBoard();
	}
	public void updateBoard() {
		int[][] grid = board.getGameboardGrid();
		int[][] stagingGrid = board.getInstantaneousGameboardGrid();
		for (int i=0;i<SIZE;i++) {
			for (int j=0;j<SIZE;j++) {
				if (stagingGrid[j][SIZE - i - 1] == 5 || stagingGrid[j][SIZE - i - 1] == 6) {
					squares[i][j].setBackground(stagingGrid[j][SIZE - i - 1] == 5 ? Color.decode("#00FF00") : Color.decode("#FF7733"));
					//squares[i][j].setBorder(BorderFactory.createLineBorder(stagingGrid[j][SIZE - i - 1] == 5 ? Color.decode("#00FF00") : Color.decode("#FF0000"), 1, false));
				} else if (grid[j][SIZE - i - 1] != 0) {
					squares[i][j].setBackground(colors[grid[j][SIZE - i - 1] - 1]);
					squares[i][j].setBorder(BorderFactory.createLineBorder(Color.white, 1, false));
					//squares[i][j].setBorder(BorderFactory.createLineBorder(colors[grid[j][SIZE - i - 1] - 1], 1, false));
				} else {
					squares[i][j].setBackground(BLANK_COLOR);
					squares[i][j].setBorder(BorderFactory.createLineBorder(Color.white, 1, false));
				}
			}
		}
		this.revalidate();
		this.repaint();
		//squares[0][0].setBackground(colors[0]);
	}
}