
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
				grid[currentPiece.getPieceCoordinates().get(i).getX()][currentPiece.getPieceCoordinates().get(i).getY()] = player.getID();
				player.getPiecesLeft().remove(piece);
			}
		}
	}
	
	public boolean isValid (GamePiece piece, Player player) {
		//checks for validness based on players' other pieces
		for (int i = 0; i < piece.getCornerCoordinates().size(); i++) {
			if (piece.getCornerCoordinates().get(i).getX() < gridSize-1 
				&& grid[piece.getCornerCoordinates().get(i).getX()+1][piece.getCornerCoordinates().get(i).getY()] == player.getID()) {
				for (int j = 0; j < piece.getPieceCoordinates().size(); j++) {
					if (!piece.getPieceCoordinates().contains(new Position(piece.getCornerCoordinates().get(i).getX()+1, piece.getCornerCoordinates().get(i).getY()))) {
						return false;
					}
				}
			}
			if (piece.getCornerCoordinates().get(i).getX() > 0 
				&& grid[piece.getCornerCoordinates().get(i).getX()-1][piece.getCornerCoordinates().get(i).getY()] == player.getID()) {
				for (int j = 0; j < piece.getPieceCoordinates().size(); j++) {
					if (!piece.getPieceCoordinates().contains(new Position(piece.getCornerCoordinates().get(i).getX()-1, piece.getCornerCoordinates().get(i).getY()))) {
						return false;
					}
				}
			}
			if (piece.getCornerCoordinates().get(i).getY() < gridSize-1 
				&& grid[piece.getCornerCoordinates().get(i).getX()][piece.getCornerCoordinates().get(i).getY()+1] == player.getID()) {
				for (int j = 0; j < piece.getPieceCoordinates().size(); j++) {
					if (!piece.getPieceCoordinates().contains(new Position(piece.getCornerCoordinates().get(i).getX(), piece.getCornerCoordinates().get(i).getY()+1))) {
						return false;
					}
				}
			}
			if (piece.getCornerCoordinates().get(i).getY() > 0 
				&& grid[piece.getCornerCoordinates().get(i).getX()][piece.getCornerCoordinates().get(i).getY()-1] == player.getID()) {
				for (int j = 0; j < piece.getPieceCoordinates().size(); j++) {
					if (!piece.getPieceCoordinates().contains(new Position(piece.getCornerCoordinates().get(i).getX(), piece.getCornerCoordinates().get(i).getY()-1))) {
						return false;
					}
				}
			}
		}
		
		//checks for validness based on other players' pieces
		for (int i = 0; i < piece.getPieceCoordinates().size(); i++) {
			if (grid[piece.getPieceCoordinates().get(i).getX()][piece.getPieceCoordinates().get(i).getY()] != 0
				&& grid[piece.getPieceCoordinates().get(i).getX()][piece.getPieceCoordinates().get(i).getY()] != player.getID()) {
				return false;
			}
		}
		return true;
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
		Player player2 = new Player("jeffrey", 2);
		board.placePiece(player1.getPiece(2), player1);
		board.placePiece(player2.getPiece(1), player2);
		board.print(board);
	}
}
