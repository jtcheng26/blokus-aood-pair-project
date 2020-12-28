
public class Gameboard {
	private int[][] grid;
	private int gridSize;
	Gameboard (int numPlayers) {
		if (numPlayers == 2) {
			grid = new int[15][15];
			for (int hor=0; hor < 15; hor++) {
				for (int vert=0; vert < 15; vert++) {
					grid[hor][vert] = 0;
				}
			}
			gridSize = 15;
		}
		if (numPlayers == 3) {
			grid = new int [18][18];
			for (int hor=0; hor < 18; hor++) {
				for (int vert=0; vert < 18; vert++) {
					grid[hor][vert] = 0;
				}
			}
			gridSize = 18;
		}
		if (numPlayers == 4) {
			grid = new int [20][20];
			for (int hor=0; hor < 20; hor++) {
				for (int vert=0; vert < 20; vert++) {
					grid[hor][vert] = 0;
				}
			}
			gridSize = 20;
		}
	}
	public void placePiece (GamePiece piece, Player player) {
		GamePiece currentPiece = player.getPiece(player.getPiecesLeft().indexOf(piece));
		for (int i = 0; i < currentPiece.getPieceCoordinates().size(); i++) {
			if (isValid(piece,player)) {
				
			}
		}
	}
	
	public boolean isValid (GamePiece piece, Player player) {
		for (int i = 0; i < piece.getCornerCoordinates().size(); i++) {
			if (grid[piece.getCornerCoordinates().get(i).getX()+1][piece.getPieceCoordinates().get(i).getY()] == player.getID()) {
				
			}
		}
		return false;
	}
	
	public void print(Gameboard board) {
		for (int i=0; i < board.gridSize; i++) {
			for (int j=0; j < board.gridSize; j++) {
				System.out.printf("%-3s", board.grid[i][j]);
			}
			System.out.println();
		}
	}
	
	public static void main (String[] args) {
		Gameboard board = new Gameboard(4);
		Player player1 = new Player("matthew", 1);
		board.placePiece(player1.getPiece(2), player1);
		board.print(board);
	}
}
