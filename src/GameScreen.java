import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.swing.JPanel;

public class GameScreen extends JPanel implements KeyListener {
	private GamePiece selectedPiece;
	private Gameboard board;
	private GameboardScreen gameboardScreen;
	private List<Player> players;
	private List<PieceInventory> inventories;
	private JPanel inventoryPanel;
	private boolean[] isOut;
	private int currentTurn;
	GameScreen(List<Player> players) { // for GUI version
		addKeyListener(this);
		//this.board = GameTestData.getSmallTestBoard1(players.get(0), players.get(1));
		this.board = new Gameboard(players.size());
		this.inventories = new ArrayList<PieceInventory>();
		for (Player player : players) {
			inventories.add(new PieceInventory(player));
		}
		//selectedPiece = players.get(1).getPiecesLeft().get(0);
		this.players = players;
		this.isOut = new boolean[players.size()];
		this.currentTurn = 0;
		this.selectedPiece = null;
		inventoryPanel = new JPanel();
		inventoryPanel.add(inventories.get(currentTurn));
		this.add(inventoryPanel);
		this.gameboardScreen = new GameboardScreen(this.board);
		this.add(gameboardScreen, BorderLayout.CENTER);
	}
	GameScreen() { // for console game (console version exists to test backend separately)
		System.out.println("How many players?");
		Scanner sc = new Scanner(System.in);
		int numPlayers = sc.nextInt();
		this.board = new Gameboard(numPlayers);
		this.players = new ArrayList<Player>();
		for (int i=1;i<=numPlayers;i++) {
			System.out.println("Enter Player " + i + "'s name: ");
			players.add(new Player(sc.next(), i, 0));
		}
		this.isOut = new boolean[players.size()];
		this.currentTurn = 0;
		this.selectedPiece = null;
		this.PLAY_CONSOLE_GAME();
		sc.close();
	}
	public void addNotify() {
        super.addNotify();
        requestFocus();
    }
	private void PLAY_CONSOLE_GAME() {
		int in = players.size();
		Player currentPlayer;
		Scanner sc = new Scanner(System.in);
		while (in > 0) {
			currentPlayer = players.get(currentTurn);
			board.print(board);
			List<GamePiece> piecesLeft = currentPlayer.getPiecesLeft();
			HashMap<String, GamePiece> pieces = new HashMap<String, GamePiece>();
			for (int i=0;i<piecesLeft.size();i++) {
				System.out.println("    " + piecesLeft.get(i).getName());
				pieces.put(piecesLeft.get(i).getName(), piecesLeft.get(i));
			}
			System.out.println("Current turn: " + currentPlayer.getName());
			System.out.println("Choose a piece: ");
			String choice = sc.nextLine();
			while (!pieces.containsKey(choice)) {
				System.out.println("Invalid piece. Choose again: ");
				choice = sc.nextLine();
			}
			onSelect(pieces.get(choice));
			System.out.println("WASD to move piece, R to rotate, F to flip, and E to place");
			boolean done = false;
			while (!done) {
				board.followCurrentPiece(selectedPiece, currentPlayer);
				board.printInstantaneousGrid(board);
				choice = sc.next();
				switch (choice) {
					case "W": 
						System.out.println("Up");
						onUpArrow();
						break;
					case "A": 
						System.out.println("Left");
						onLeftArrow();
						break;
					case "S": 
						System.out.println("Down");
						onDownArrow();
						break;
					case "D": 
						System.out.println("Right");
						onRightArrow();
						break;
					case "R": 
						System.out.println("Rotate");
						onRotate();
						break;
					case "F": 
						System.out.println("Flip");
						onFlip();
						break;
					case "E": 
						if (onEnter()) {
							done = true;
						} else {
							System.out.println("Invalid");
						}
						break;
					default:
						System.out.println("Invalid command");
				}
			}
			changeTurns();
			sc.nextLine();
		}
		sc.close();
		endGame();
	}
	private void changeTurns() {
		int orig = currentTurn; // method should only be called when at least one player is still in
		selectedPiece = null;
		inventoryPanel.removeAll();
		do {
			currentTurn = (currentTurn + 1) % players.size();
			board.rotateBoard();
			if (players.size() == 2)
				board.rotateBoard();
			if (board.playerOut(players.get(currentTurn))) {
				endPlayer(players.get(currentTurn));
			}
		} while (isOut[currentTurn] && currentTurn != orig);
		if (players.get(currentTurn).getDifficultyLevel() != 0) {
			ComputerPlayer p = (ComputerPlayer) players.get(currentTurn);
			if (p.getDifficultyLevel() == Player.EASY_AI) {
				board.placePiece(p.easyAI(board), players.get(currentTurn));
			}
			if (players.get(currentTurn).getDifficultyLevel() == Player.MEDIUM_AI) {
				board.placePiece(p.mediumAI(board), players.get(currentTurn));
			}
			if (players.get(currentTurn).getDifficultyLevel() == Player.HARD_AI) {
				board.placePiece(p.hardAI(board), players.get(currentTurn));
			}
		}
		inventoryPanel.add(inventories.get(currentTurn));
		inventoryPanel.revalidate();
		inventoryPanel.repaint();
		updateBoard();
		if (orig == currentTurn) {
			if (board.playerOut(players.get(currentTurn)))
				endGame();
		}
	}
	private void endPlayer(Player player) {
		System.out.println(player.getName() + " is out.");
		isOut[players.indexOf(player)] = true;
	}
	private void endGame() {
		ArrayList<Player> winners = new ArrayList<Player>();
		int winningScore = 0;
		for (Player player : players) {
			if (player.getScore() > winningScore) {
				winningScore = player.getScore();
				winners.clear();
				winners.add(player);
			} else if (player.getScore() == winningScore) {
				winners.add(player);
			}
		}
		if (winners.size() == 1)
			System.out.println(winners.get(0).getName() + " won the game!");
		else {
			for (int i=0;i<winners.size()-1;i++) {
				System.out.print(winners.get(i) + ", ");
			}
			System.out.println(", and " + winners.get(winners.size() - 1).getName() + " tied for the win!");
		}
	}
	private void onSelect(GamePiece selectedPiece) {
		System.out.println("Selected " + selectedPiece.getName());
		this.selectedPiece = selectedPiece;
	}
	private void onLeftArrow() {
		selectedPiece.moveLeft();
	}
	private void onRightArrow() {
		selectedPiece.moveRight();
	}
	private void onUpArrow() {
		selectedPiece.moveUp();
	}
	private void onDownArrow() {
		selectedPiece.moveDown();
	}
	private void onRotate() {
		selectedPiece.rotatePiece();
	}
	private void onFlip() {
		selectedPiece.flipPiece();
	}
	private boolean onEnter() {
		if (board.isValid(selectedPiece, players.get(currentTurn))) {
			board.placePiece(selectedPiece, players.get(currentTurn));
			return true;
		}
		return false;
	}
	public void keyPressed(KeyEvent e) {
		if (selectedPiece != null) {
		    int key = e.getKeyCode();
		    
		    if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
		        onLeftArrow();
		        if (!board.isPieceOnBoard(selectedPiece))
		        	onRightArrow();
		    }
	
		    if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
		        onRightArrow();
		        if (!board.isPieceOnBoard(selectedPiece))
		        	onLeftArrow();
		    }
	
		    if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
		        onUpArrow();
		        if (!board.isPieceOnBoard(selectedPiece))
		        	onDownArrow();
		    }
	
		    if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
		        onDownArrow();
		        if (!board.isPieceOnBoard(selectedPiece))
		        	onUpArrow();
		    }
		    
		    if (key == KeyEvent.VK_R) {
		    	onRotate();
		    	if (!board.isPieceOnBoard(selectedPiece)) {
		        	onRotate();
		        	onRotate();
		        	onRotate();
		    	}
		    }
		    
		    if (key == KeyEvent.VK_F) {
		    	onFlip();
		    	if (!board.isPieceOnBoard(selectedPiece))
		        	onFlip();
		    }
		    
		    if (key == KeyEvent.VK_ENTER) {
		    	if (onEnter()) {
		    		inventories.get(currentTurn).placedPiece(selectedPiece);
		    		selectedPiece = null;
		    		changeTurns();
		    		// next turn
		    	}
		    }
		    //board.isValid(selectedPiece, players.get(currentTurn));
		    updateBoard();
		}
	}
	private void updateBoard() {
		if (selectedPiece != null) {
			board.followCurrentPiece(selectedPiece, players.get(currentTurn));
		}
	    gameboardScreen.updateBoard();
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	class PieceInventory extends JPanel {
		private Player player;
		private GamePiece[][] grid;
		private JPanel[][] visualGrid;
		private static final int CELL_WIDTH = 4;
		private static final int CELL_HEIGHT = 5;
		PieceInventory(Player player) {
			this.player = player;
			this.setLayout(new GridLayout(CELL_HEIGHT * 7, CELL_WIDTH * 3));
			grid = new GamePiece[CELL_HEIGHT * 7][CELL_WIDTH * 3];
			visualGrid = new JPanel[CELL_HEIGHT * 7][CELL_WIDTH * 3];
			List<GamePiece> pieces = player.getPiecesLeft();
			int row = 0;
			int col = 0;
			for (int i=0;i<pieces.size();i++) {
				List<Position> pieceDeltas = pieces.get(i).getGamePieceDeltas();
				for (Position p : pieceDeltas) {
					grid[p.getY() + row][col + p.getX() - 1] = pieces.get(i);
				}
				col += CELL_WIDTH;
				if (col == CELL_WIDTH * 3) {
					row = row + CELL_HEIGHT;
					col = 0;
				}
			}
			for (int r=0;r<CELL_HEIGHT * 7;r++) {
				for (int c=0;c<CELL_WIDTH * 3;c++) {
					visualGrid[r][c] = new JPanel();
					final int i = r;
					final int j = c;
					visualGrid[r][c].addMouseListener(new MouseAdapter() {
						public void mousePressed(MouseEvent e) {
							if (grid[i][j] != null) {
								selectedPiece = grid[i][j];
								updateBoard();
								handleClick(grid[i][j]);
							}
						}
					});
					visualGrid[r][c].setPreferredSize(new Dimension(24, 24));
					this.add(visualGrid[r][c]);
				}
			}
			updateInventory();
		}
		public void updateInventory() {
			for (int i=0;i<CELL_HEIGHT * 7;i++) {
				for (int j=0;j<CELL_WIDTH * 3;j++) {
					if (grid[i][j] != null) {
						visualGrid[i][j].setBackground(GameboardScreen.colors[player.getID() - 1]);
					}
				}
			}
		}
		public void placedPiece(GamePiece piece) {
			for (int i=0;i<CELL_HEIGHT * 7;i++) {
				for (int j=0;j<CELL_WIDTH * 3;j++) {
					if (grid[i][j] != null && grid[i][j] == piece) {
						grid[i][j] = null;
						visualGrid[i][j].setBackground(GameboardScreen.BLANK_COLOR);
					}
				}
			}
		}
		public void handleClick(GamePiece piece) {
			for (int i=0;i<CELL_HEIGHT * 7;i++) {
				for (int j=0;j<CELL_WIDTH * 3;j++) {
					if (grid[i][j] != null && grid[i][j] == piece) {
						visualGrid[i][j].setBackground(GameboardScreen.colors[player.getID() - 1]);
					}
				}
			}
		}
	}
}