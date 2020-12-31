
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
				//System.out.println("valid position");
			} else {
				//System.out.println("invalid position");
			}
		}
	}
	
	public boolean isValid (GamePiece piece, Player player) {
		//checks if corner for first piece
		if (player.getPiecesLeft().size() == 7) {
			System.out.println("sds");
			if (!piece.getCornerCoordinates().contains(new Position(0,0)) && !piece.getCornerCoordinates().contains(new Position(0,this.gridSize))
				&& !piece.getCornerCoordinates().contains(new Position(this.gridSize,0)) && !piece.getCornerCoordinates().contains(new Position(this.gridSize,this.gridSize))) {
				return false;
			}
		} else {
			//checks for validness based on players' other pieces
			for (int i = 0; i < piece.getPieceCoordinates().size(); i++) {
				//checks if directly right square is a player's piece
				if (piece.getPieceCoordinates().get(i).getX() < gridSize-1 
					&& grid[piece.getPieceCoordinates().get(i).getX()+1][piece.getPieceCoordinates().get(i).getY()] == player.getID()) {
					for (int j = 0; j < piece.getPieceCoordinates().size(); j++) {
						if (!piece.getPieceCoordinates().contains(new Position(piece.getPieceCoordinates().get(j).getX()+1, piece.getPieceCoordinates().get(j).getY()))) {
							return false;
						}
					}
				}
				//checks if directly left square is a player's piece
				if (piece.getPieceCoordinates().get(i).getX() > 0 
					&& grid[piece.getPieceCoordinates().get(i).getX()-1][piece.getPieceCoordinates().get(i).getY()] == player.getID()) {
					for (int j = 0; j < piece.getPieceCoordinates().size(); j++) {
						if (!piece.getPieceCoordinates().contains(new Position(piece.getPieceCoordinates().get(j).getX()-1, piece.getPieceCoordinates().get(j).getY()))) {
							return false;
						}
					}
				}
				//checks if directly below square is a player's piece
				if (piece.getPieceCoordinates().get(i).getY() < gridSize-1 
					&& grid[piece.getPieceCoordinates().get(i).getX()][piece.getPieceCoordinates().get(i).getY()+1] == player.getID()) {
					for (int j = 0; j < piece.getPieceCoordinates().size(); j++) {
						if (!piece.getPieceCoordinates().contains(new Position(piece.getPieceCoordinates().get(j).getX(), piece.getPieceCoordinates().get(j).getY()+1))) {
							return false;
						}
					}
				}
				//checks if directly above square is a player's piece
				if (piece.getPieceCoordinates().get(i).getY() > 0 
					&& grid[piece.getPieceCoordinates().get(i).getX()][piece.getPieceCoordinates().get(i).getY()-1] == player.getID()) {
					for (int j = 0; j < piece.getPieceCoordinates().size(); j++) {
						if (!piece.getPieceCoordinates().contains(new Position(piece.getPieceCoordinates().get(j).getX(), piece.getPieceCoordinates().get(j).getY()-1))) {
							return false;
						}
					}
				}
				//checks if corners are player's piece
				if (player.getPiecesLeft().size() < 7) {
					boolean returnBoolean = false;
					//all pieces excluding pieces on border
					if (piece.getPieceCoordinates().get(i).getY() > 0 && piece.getPieceCoordinates().get(i).getY() < this.gridSize) {
						if (piece.getPieceCoordinates().get(i).getX() > 0 && piece.getPieceCoordinates().get(i).getX() < this.gridSize) {
							if (grid[piece.getPieceCoordinates().get(i).getX()-1][piece.getPieceCoordinates().get(i).getY()-1] == player.getID()
								|| grid[piece.getPieceCoordinates().get(i).getX()+1][piece.getPieceCoordinates().get(i).getY()-1] == player.getID()
								|| grid[piece.getPieceCoordinates().get(i).getX()-1][piece.getPieceCoordinates().get(i).getY()+1] == player.getID()
								|| grid[piece.getPieceCoordinates().get(i).getX()+1][piece.getPieceCoordinates().get(i).getY()+1] == player.getID()) {
								returnBoolean = true;
							}
						}
					}
					//first column
					if (piece.getPieceCoordinates().get(i).getX() == 0) {
						//top left
						if (piece.getPieceCoordinates().get(i).getY() == 0) {
							if (grid[piece.getPieceCoordinates().get(i).getX()+1][piece.getPieceCoordinates().get(i).getY()+1] == player.getID()) {
								returnBoolean = true;
							}
						} else
						//bottom left
						if (piece.getPieceCoordinates().get(i).getY() == this.gridSize) {
							if (grid[piece.getPieceCoordinates().get(i).getX()+1][piece.getPieceCoordinates().get(i).getY()-1] == player.getID()) {
								returnBoolean = true;
							}
						} else {
							//in between
							if (grid[piece.getPieceCoordinates().get(i).getX()+1][piece.getPieceCoordinates().get(i).getY()-1] == player.getID()) {
								returnBoolean = true;
							}
							if (grid[piece.getPieceCoordinates().get(i).getX()+1][piece.getPieceCoordinates().get(i).getY()+1] == player.getID()) {
								returnBoolean = true;
							}
						}
					}
					//last column
					if (piece.getPieceCoordinates().get(i).getX() == this.gridSize) {
						//top right
						if (piece.getPieceCoordinates().get(i).getY() == 0) {
							if (grid[piece.getPieceCoordinates().get(i).getX()-1][piece.getPieceCoordinates().get(i).getY()+1] == player.getID()) {
								returnBoolean = true;
							}
						} else
						//bottom right
						if (piece.getPieceCoordinates().get(i).getY() == this.gridSize) {
							if (grid[piece.getPieceCoordinates().get(i).getX()-1][piece.getPieceCoordinates().get(i).getY()-1] == player.getID()) {
								returnBoolean = true;
							}
						} else {
							//in between
							if (grid[piece.getPieceCoordinates().get(i).getX()-1][piece.getPieceCoordinates().get(i).getY()-1] == player.getID()) {
								returnBoolean = true;
							}
							if (grid[piece.getPieceCoordinates().get(i).getX()-1][piece.getPieceCoordinates().get(i).getY()+1] == player.getID()) {
								returnBoolean = true;
							}
						}
					}
					return returnBoolean;
				}
			}
			return false;
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
		Player player1 = new Player("Matthew", 1);
		board.placePiece(player1.getPiece(0), player1);
		board.print(board);
	}
}
