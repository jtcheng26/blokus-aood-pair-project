
public class Gameboard {
	private int[][] grid;
	Gameboard (int numPlayers) {
		if (numPlayers == 2) {
			grid = new int[15][15];
		}
		if (numPlayers == 3) {
			grid = new int [18][18];
		}
		if (numPlayers == 4) {
			grid = new int [20][20];
		}
	}
	public void placePiece (GamePiece piece, Player player) {
		GamePiece currentPiece = player.getPiece(player.getPiecesLeft().indexOf(piece));
		for (int i = 0; i < currentPiece.getPieceCoordinates().size(); i++) {
			grid[currentPiece.getPieceCoordinates().get(i).getX()][currentPiece.getPieceCoordinates().get(i).getY()] = player.getID();
		}
	}
	
	public void isValid (GamePiece piece, Player player) {
		
	}
	
	public void print(Gameboard board) {
		
	}
}
