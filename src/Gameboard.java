
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
		if (isValid(piece,player)) {
			for (int i = 0; i < currentPiece.getPieceCoordinates().size(); i++) {
				grid[currentPiece.getPieceCoordinates().get(i).getX()][currentPiece.getPieceCoordinates().get(i).getY()] = player.getID();
			}
			player.getPiecesLeft().remove(piece);
			player.getPiecesUsed().add(piece);
		}
	}
	
	public boolean isValid (GamePiece piece, Player player) {
		/*
		 * checks if corner for first piece
		 */
		/*
		 * update size after jeffrey puts in all the pieces
		 */
		boolean returnBoolean = false;
		if (player.getPiecesLeft().size() == 7) {
			for (int i=0; i < piece.getPieceCoordinates().size(); i++) {
				if (!piece.getPieceCoordinates().get(i).equals(new Position(0,0)) && !piece.getPieceCoordinates().get(i).equals(new Position(0,this.gridSize))
				&& !piece.getPieceCoordinates().get(i).equals(new Position(this.gridSize,0)) && !piece.getPieceCoordinates().get(i).equals(new Position(this.gridSize,this.gridSize))
				&& grid[piece.getPieceCoordinates().get(i).getX()][piece.getPieceCoordinates().get(i).getY()] == 0) {
					returnBoolean = true;
				}
				if (grid[piece.getPieceCoordinates().get(i).getX()][piece.getPieceCoordinates().get(i).getY()] != 0) {
					returnBoolean = false;
				}
			}
		} else {
			/*
			 * checks if position is empty
			 */
			for (int i=0; i < piece.getPieceCoordinates().size(); i++) {
				if (grid[piece.getPieceCoordinates().get(i).getX()][piece.getPieceCoordinates().get(i).getY()] != 0) {
					return false;
				}
			}
			/*
			 * adjacent
			 */
			for (int i=0; i < piece.getAdjacentCoordinates().size(); i++) {
				if (grid[piece.getAdjacentCoordinates().get(i).getX()][piece.getAdjacentCoordinates().get(i).getY()] == player.getID()) {
					return false;
				}
			}
			/*
			 * corners of other pieces
			 */
			for (int i=0; i < piece.getCornerCoordinates().size(); i++) {
				if (grid[piece.getCornerCoordinates().get(i).getX()][piece.getCornerCoordinates().get(i).getY()] == player.getID()) {
					returnBoolean = true;
				}
			}
		}
		return returnBoolean;
	}
	
	public boolean playerOut (Player player) {
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
		Player player1 = new Player("Matthew", 1);
		board.placePiece(player1.getPiece(0), player1);
		player1.getPiece(0).moveRight();
		player1.getPiece(0).moveRight();
		player1.getPiece(0).moveRight();
		board.placePiece(player1.getPiece(0), player1);
		System.out.println(player1.getScore());
		board.print(board);
	}
}
