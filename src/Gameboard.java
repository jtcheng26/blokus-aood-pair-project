import java.util.List;

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
		boolean returnBoolean = false;
		List<Position> pieceCoordinatesList = piece.getPieceCoordinates();
		List<Position> pieceAdjacentCoordinatesList = piece.getAdjacentCoordinates();
		List<Position> pieceCornerCoordinatesList = piece.getCornerCoordinates();
		
		if (player.getPiecesLeft().size() == 21) {
			for (int i=0; i < pieceCoordinatesList.size(); i++) {
				/*
				System.out.println(pieceCoordinatesList.get(i).getX()+","+pieceCoordinatesList.get(i).getY());
				System.out.println("test: "+pieceCoordinatesList.contains(new Position(0,0)));
				*/
				if (pieceCoordinatesList.get(i).getX() < 0 || pieceCoordinatesList.get(i).getX() >= this.gridSize
					|| pieceCoordinatesList.get(i).getY() < 0 || pieceCoordinatesList.get(i).getY() >= this.gridSize) {
					return false;
				}
				/*
				 * checks corner for first piece
				 */
				if (pieceCoordinatesList.get(i).equals(new Position(0,0)) || pieceCoordinatesList.get(i).equals(new Position(0,this.gridSize-1))
				|| pieceCoordinatesList.get(i).equals(new Position(this.gridSize-1,0)) || pieceCoordinatesList.get(i).equals(new Position(this.gridSize-1,this.gridSize-1))) {
					if (grid[piece.getPieceCoordinates().get(i).getX()][piece.getPieceCoordinates().get(i).getY()] != 0) {
						return false;
					} else {
						returnBoolean = true;
					}
				}
			}
			return returnBoolean;
		} else {
			/*
			 * checks if outside board
			 */
			for (int i=0; i < pieceCoordinatesList.size(); i++) {
				if (pieceCoordinatesList.get(i).getX() < 0 || pieceCoordinatesList.get(i).getX() >= this.gridSize
					|| pieceCoordinatesList.get(i).getY() < 0 || pieceCoordinatesList.get(i).getY() >= this.gridSize) {
					return false;
				}
			}
			/*
			 * checks if position is empty
			 */
			for (int i=0; i < pieceCoordinatesList.size(); i++) {
				if (grid[pieceCoordinatesList.get(i).getX()][pieceCoordinatesList.get(i).getY()] != 0) {
					return false;
				}
			}
			/*
			 * adjacent
			 */
			for (int i=0; i < pieceAdjacentCoordinatesList.size(); i++) {
				if (grid[pieceAdjacentCoordinatesList.get(i).getX()][pieceAdjacentCoordinatesList.get(i).getY()] == player.getID()) {
					return false;
				}
			}
			/*
			 * corners of other pieces
			 */
			for (int i=0; i < pieceCornerCoordinatesList.size(); i++) {
				if (grid[pieceCornerCoordinatesList.get(i).getX()][pieceCornerCoordinatesList.get(i).getY()] == player.getID()) {
					returnBoolean = true;
				}
			}
		}
		return returnBoolean;
	}
	
	public boolean playerOut (Player player) {
		boolean returnBoolean = false;
		if (player.getPiecesLeft().size() == 0) {
			returnBoolean = true;
		} else {
			
		}
		return returnBoolean;
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
		Gameboard board = new Gameboard(2);
		Player player1 = new Player("Matthew", 1);
		Player player2 = new Player("Jeffrey", 2);
		board.placePiece(player1.getPiece(0), player1);
		board.placePiece(player2.getPiece(0), player2);
		board.print(board);
	}
}
