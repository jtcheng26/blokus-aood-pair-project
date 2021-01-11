import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameScreen extends JPanel implements KeyListener {
	private GamePiece selectedPiece;
	private Gameboard board;
	private GameboardScreen gameboardScreen;
	private List<Player> players;
	private boolean[] isOut;
	private int currentTurn;
	GameScreen(List<Player> players) { // for GUI version
		addKeyListener(this);
		this.board = GameTestData.getSmallTestBoard1(players.get(0), players.get(1));//new Gameboard(players.size());
		selectedPiece = players.get(1).piecesLeft.get(0);
		this.players = players;
		this.isOut = new boolean[players.size()];
		this.currentTurn = 1;
		//this.selectedPiece = null;
		this.gameboardScreen = new GameboardScreen(this.board);
		this.add(gameboardScreen);
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
		do {
			currentTurn = (currentTurn + 1) % players.size();
			board.rotateBoard();
			if (players.size() == 2)
				board.rotateBoard();
		} while (isOut[currentTurn] && currentTurn != orig);
		if (orig == currentTurn)
			System.out.println("changeTurns was called with 0 players remaining.");
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
			System.out.println(", and " + winners.get(winners.size() - 1) + " tied for the win!");
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
	    //board.isValid(selectedPiece, players.get(currentTurn));
	    board.followCurrentPiece(selectedPiece, players.get(currentTurn));
	    gameboardScreen.updateBoard();
	}
	public static void main(String[] args) {
		GameScreen game = new GameScreen();
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}