import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class GameScreen {
	private GamePiece selectedPiece;
	private boolean valid;
	private Gameboard board;
	private List<Player> players;
	private boolean[] isOut;
	private int currentTurn;
	GameScreen(List<Player> players) { // for GUI version
		this.board = new Gameboard(players.size());
		this.players = players;
		this.isOut = new boolean[players.size()];
		this.currentTurn = 0;
		this.selectedPiece = null;
		this.valid = false;
	}
	GameScreen() { // for console game (console version exists to test backend separately)
		System.out.println("How many players?");
		Scanner sc = new Scanner(System.in);
		int numPlayers = sc.nextInt();
		this.board = new Gameboard(numPlayers);
		this.players = new ArrayList<Player>();
		for (int i=1;i<=numPlayers;i++) {
			System.out.println("Enter Player " + i + "'s name: ");
			players.add(new Player(sc.next(), i));
		}
		this.isOut = new boolean[players.size()];
		this.currentTurn = 0;
		this.selectedPiece = null;
		this.valid = false;
		this.PLAY_CONSOLE_GAME();
		sc.close();
	}
	private void PLAY_CONSOLE_GAME() {
		int in = players.size();
		Player currentPlayer;
		Scanner sc = new Scanner(System.in);
		while (in > 0) {
			currentPlayer = players.get(currentTurn);
			board.print(board);
			System.out.println("Choose a piece: ");
			List<GamePiece> piecesLeft = currentPlayer.getPiecesLeft();
			HashMap<String, GamePiece> pieces = new HashMap<String, GamePiece>();
			for (GamePiece piece : piecesLeft) {
				System.out.println("    " + piece.getName());
				pieces.put(piece.getName(), piece);
			}
			String choice = sc.nextLine();
			while (!pieces.containsKey(choice)) {
				System.out.println("Invalid piece, choose again:");
				choice = sc.nextLine();
			}
			onSelect(pieces.get(choice));
			System.out.println("WASD to move piece, R to rotate, F to flip, and E to place");
			boolean done = false;
			while (!done) {
				choice = sc.next();
				switch (choice) {
					case "W": onUpArrow();
					case "A": onLeftArrow();
					case "S": onDownArrow();
					case "D": onRightArrow();
					case "R": onRotate();
					case "F": onFlip();
					case "E": 
						if (onEnter()) {
							done = true;
							break;
						} else {
							System.out.println("Invalid");
						}
				}
			}
			changeTurns();
		}
		sc.close();
	}
	private void changeTurns() {
		int orig = currentTurn; // method should only be called when at least one player is still in
		selectedPiece = null;
		valid = false;
		do {
			currentTurn = (currentTurn + 1) % players.size();
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
		this.valid = board.isValid(selectedPiece, this.players.get(currentTurn));
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
		if (valid) {
			board.placePiece(selectedPiece, players.get(currentTurn));
			return true;
		}
		return false;
	}
	public static void main(String[] args) {
		GameScreen game = new GameScreen();
	}
}