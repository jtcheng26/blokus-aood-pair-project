import java.util.List;

public class Gameboard {
	private int[][] grid;
	private int[][] instantaneousGrid;
	private int gridSize;
	
	Gameboard (int numPlayers) {
		if (numPlayers == 2) {
			grid = new int[15][15];
			instantaneousGrid = new int[15][15];
			for (int hor=0; hor < 15; hor++) {
				for (int vert=0; vert < 15; vert++) {
					grid[hor][vert] = 0;
					instantaneousGrid[hor][vert] = 0;
				}
			}
			gridSize = 15;
		}
		if (numPlayers == 3) {
			grid = new int [18][18];
			instantaneousGrid = new int[18][18];
			for (int hor=0; hor < 18; hor++) {
				for (int vert=0; vert < 18; vert++) {
					grid[hor][vert] = 0;
					instantaneousGrid[hor][vert] = 0;
				}
			}
			gridSize = 18;
		}
		if (numPlayers == 4) {
			grid = new int [20][20];
			instantaneousGrid = new int[20][20];
			for (int hor=0; hor < 20; hor++) {
				for (int vert=0; vert < 20; vert++) {
					grid[hor][vert] = 0;
					instantaneousGrid[hor][vert] = 0;
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
				instantaneousGrid[currentPiece.getPieceCoordinates().get(i).getX()][currentPiece.getPieceCoordinates().get(i).getY()] = player.getID();
			}
			player.getPiecesLeft().remove(piece);
			player.getPiecesUsed().add(piece);
		}
		for (int i = 0; i < player.getPiecesUsed().size(); i++) {
			for (int j = 0 ; j < player.getPiecesUsed().get(i).getCornerCoordinates().size(); j++) {
				if (grid[player.getPiecesUsed().get(i).getCornerCoordinates().get(j).getX()][player.getPiecesUsed().get(i).getCornerCoordinates().get(j).getY()] == 0) {
					if (!player.getCornerPositions().contains(player.getPiecesUsed().get(i).getCornerCoordinates().get(j))) {
						player.getCornerPositions().add(player.getPiecesUsed().get(i).getCornerCoordinates().get(j));
					}
				} else {
					player.getCornerPositions().remove(player.getPiecesUsed().get(i).getCornerCoordinates().get(j));
				}
			}
		}
	}
	
	public void followCurrentPiece (GamePiece currentPiece, Player player ) {
		for (int i=0; i < this.gridSize; i++) {
			for (int j=0; j < this.gridSize; j++) {
				instantaneousGrid[j][this.gridSize-i-1] = grid[j][this.gridSize-i-1];
			}
		}
		/*
		 * 5 is valid
		 * 6 is invalid
		 */
		for (int i = 0; i < currentPiece.getPieceCoordinates().size(); i++) {
			if (isValid(currentPiece, player)) {
				instantaneousGrid[currentPiece.getPieceCoordinates().get(i).getX()][currentPiece.getPieceCoordinates().get(i).getY()] = 5;
			} else {
				instantaneousGrid[currentPiece.getPieceCoordinates().get(i).getX()][currentPiece.getPieceCoordinates().get(i).getY()] = 6;
			}
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
			for (int i = 0; i < player.getPiecesLeft().size(); i++) {
				for (int j = 0; j < player.getPiecesLeft().get(i).getPieceCoordinates().size(); j++) {
					for (int k = 0; k < player.getCornerPositions().size(); k++) {
						player.getPiecesLeft().get(i).setPieceCoordinateLocation(j, player.getCornerPositions().get(k));
						if (!isValid(player.getPiecesLeft().get(i), player)) {
							player.getPiecesLeft().get(i).rotatePiece();
							if (!isValid(player.getPiecesLeft().get(i), player)) {
								player.getPiecesLeft().get(i).rotatePiece();
								if (!isValid(player.getPiecesLeft().get(i), player)) {
									player.getPiecesLeft().get(i).rotatePiece();
									if (!isValid(player.getPiecesLeft().get(i), player)) {
										player.getPiecesLeft().get(i).rotatePiece();
										player.getPiecesLeft().get(i).flipPiece();
										if (!isValid(player.getPiecesLeft().get(i), player)) {
											player.getPiecesLeft().get(i).rotatePiece();
											if (!isValid(player.getPiecesLeft().get(i), player)) {
												player.getPiecesLeft().get(i).rotatePiece();
												if (!isValid(player.getPiecesLeft().get(i), player)) {
													player.getPiecesLeft().get(i).rotatePiece();
													if (!isValid(player.getPiecesLeft().get(i), player)) {
														returnBoolean = true;
													} else {
														player.getPiecesLeft().get(i).rotatePiece();
														player.getPiecesLeft().get(i).flipPiece();
														player.getPiecesLeft().get(i).setPieceCoordinateLocation(j, new Position(0,0));
														returnBoolean = false;
													}
												} else {
													player.getPiecesLeft().get(i).rotatePiece();
													player.getPiecesLeft().get(i).rotatePiece();
													player.getPiecesLeft().get(i).flipPiece();
													player.getPiecesLeft().get(i).setPieceCoordinateLocation(j, new Position(0,0));
													returnBoolean = false;
												}
											} else {
												player.getPiecesLeft().get(i).rotatePiece();
												player.getPiecesLeft().get(i).rotatePiece();
												player.getPiecesLeft().get(i).rotatePiece();
												player.getPiecesLeft().get(i).flipPiece();
												player.getPiecesLeft().get(i).setPieceCoordinateLocation(j, new Position(0,0));
												returnBoolean = false;
											}
										} else {
											player.getPiecesLeft().get(i).flipPiece();
											player.getPiecesLeft().get(i).setPieceCoordinateLocation(j, new Position(0,0));
											returnBoolean = false;
										}
									} else {
										player.getPiecesLeft().get(i).rotatePiece();
										player.getPiecesLeft().get(i).setPieceCoordinateLocation(j, new Position(0,0));
										returnBoolean = false;
									}
								} else {
									player.getPiecesLeft().get(i).rotatePiece();
									player.getPiecesLeft().get(i).rotatePiece();
									player.getPiecesLeft().get(i).setPieceCoordinateLocation(j, new Position(0,0));
									returnBoolean = false;
								}
							} else {
								player.getPiecesLeft().get(i).rotatePiece();
								player.getPiecesLeft().get(i).rotatePiece();
								player.getPiecesLeft().get(i).rotatePiece();
								player.getPiecesLeft().get(i).setPieceCoordinateLocation(j, new Position(0,0));
								returnBoolean = false;
							}
						} else {
							player.getPiecesLeft().get(i).setPieceCoordinateLocation(j, new Position(0,0));
							returnBoolean = false;
						}
					}
				}
			}
		}
		return returnBoolean;
	}
	
	public void rotateBoard() {
		int[][] newGrid = new int[gridSize][gridSize];
		for (int i=0;i<gridSize;i++) {
			for (int j=0;j<gridSize;j++) {
				newGrid[j][gridSize - i - 1] = grid[i][j];
			}
		}
		this.grid = newGrid;
	}
	
	public void print(Gameboard board) {
		for (int i=0; i < board.gridSize; i++) {
			for (int j=0; j < board.gridSize; j++) {
				System.out.printf("%-2s", board.grid[j][board.gridSize - i - 1] == 0 ? "." : board.grid[j][board.gridSize - i - 1]);
			}
			System.out.println();
		}
	}
	
	public void printInstantaneousGrid(Gameboard board) {
		System.out.println();
		for (int i=0; i < board.gridSize; i++) {
			for (int j=0; j < board.gridSize; j++) {
				System.out.printf("%-2s", board.instantaneousGrid[j][board.gridSize - i - 1] == 0 ? "." : board.instantaneousGrid[j][board.gridSize - i - 1]);
			}
			System.out.println();
		}
	}
	
	public static void main (String[] args) {
		Gameboard board = new Gameboard(2);
		Player player1 = new Player("Matthew", 1);
		Player player2 = new Player("Jeffrey", 2);
		board.placePiece(player1.getPiece(0), player1);
		player1.getPiece(0).moveRight();
		board.followCurrentPiece(player1.getPiece(0), player1);
		board.printInstantaneousGrid(board);
		player1.getPiece(0).moveRight();
		board.followCurrentPiece(player1.getPiece(0), player1);
		board.printInstantaneousGrid(board);
		player1.getPiece(0).moveRight();
		board.followCurrentPiece(player1.getPiece(0), player1);
		board.printInstantaneousGrid(board);
		board.placePiece(player1.getPiece(0), player1);
		//board.placePiece(player2.getPiece(0), player2);
		board.printInstantaneousGrid(board);
	}
}
