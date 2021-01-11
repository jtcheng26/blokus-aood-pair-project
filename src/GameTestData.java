import java.util.ArrayList;

class GameTestData {
	public static Gameboard getSmallTestBoard1(Player player1, Player player2) {
		Gameboard board = new Gameboard(2);
		// player 1 (out player) pieces
		GamePiece V51 = new V5Piece();
		GamePiece O11 = new O1Piece();
		GamePiece I21 = new I2Piece();
		GamePiece T51 = new T5Piece();
		GamePiece F51 = new F5Piece();
		GamePiece L41 = new L4Piece();
		GamePiece V31 = new V3Piece();
		GamePiece I31 = new I3Piece();
		GamePiece L51 = new L5Piece();
		GamePiece Y51 = new Y5Piece();
		GamePiece O41 = new O4Piece();
		player1.piecesLeft = new ArrayList<GamePiece>();
		player1.piecesLeft.add(V51);
		player1.piecesLeft.add(O11);
		player1.piecesLeft.add(I21);
		player1.piecesLeft.add(T51);
		player1.piecesLeft.add(F51);
		player1.piecesLeft.add(L41);
		player1.piecesLeft.add(V31);
		player1.piecesLeft.add(I31);
		player1.piecesLeft.add(L51);
		player1.piecesLeft.add(Y51);
		player1.piecesLeft.add(O41);
		for (int i=0;i<10;i++) {
			player1.piecesLeft.add(new I4Piece());
		}
		// player 2 (not out player) pieces
		GamePiece L42 = new L4Piece();
		GamePiece Y52 = new Y5Piece();
		GamePiece V52 = new V5Piece();
		GamePiece O42 = new O4Piece();
		GamePiece O12 = new O1Piece();
		GamePiece I22 = new I2Piece();
		GamePiece I32 = new I3Piece();
		GamePiece I42 = new I4Piece();
		player2.piecesLeft = new ArrayList<GamePiece>();
		player2.piecesLeft.add(L42);
		player2.piecesLeft.add(Y52);
		player2.piecesLeft.add(V52);
		player2.piecesLeft.add(O42);
		player2.piecesLeft.add(O12);
		player2.piecesLeft.add(I22);
		player2.piecesLeft.add(I32);
		player2.piecesLeft.add(I42);
		for (int i=0;i<13;i++) {
			player2.piecesLeft.add(new T5Piece());
		}
		board.placePiece(V51, player1);
		O11.setPieceCoordinateLocation(0, new Position(1, 3));
		board.placePiece(O11, player1);
		I21.rotatePiece();
		I21.setPieceCoordinateLocation(1, new Position(3, 1));
		board.placePiece(I21, player1);
		L41.flipPiece();
		L41.setPieceCoordinateLocation(0, new Position(5, 0));
		board.placePiece(L41, player1);
		T51.rotatePiece();
		T51.setPieceCoordinateLocation(4, new Position(0, 4));
		board.placePiece(T51, player1);
		F51.flipPiece();
		F51.setPieceCoordinateLocation(3, new Position(5, 3));
		board.placePiece(F51, player1);
		V31.rotatePiece();
		V31.setPieceCoordinateLocation(1, new Position(3, 7));
		board.placePiece(V31, player1);
		I31.rotatePiece();
		I31.setPieceCoordinateLocation(0, new Position(6, 6));
		board.placePiece(I31, player1);
		L51.rotatePiece();
		L51.rotatePiece();
		L51.setPieceCoordinateLocation(0, new Position(8, 5));
		board.placePiece(L51, player1);
		Y51.flipPiece();
		Y51.rotatePiece();
		Y51.setPieceCoordinateLocation(4, new Position(10, 1));
		board.placePiece(Y51, player1);
		O41.setPieceCoordinateLocation(3, new Position(12, 1));
		board.placePiece(O41, player1);
		// player 2 (not out)
		board.rotateBoard();
		board.rotateBoard();
		board.rotateBoard();
		L42.flipPiece();
		L42.rotatePiece();
		L42.setPieceCoordinateLocation(1, new Position(0, 0));
		board.placePiece(L42, player2);
		board.rotateBoard();
		Y52.rotatePiece();
		Y52.setPieceCoordinateLocation(3, new Position(1, 11));
		board.placePiece(Y52, player2);
		V52.rotatePiece();
		V52.setPieceCoordinateLocation(0, new Position(0, 9));
		board.placePiece(V52, player2);
		O42.setPieceCoordinateLocation(0, new Position(6, 10));
		board.placePiece(O42, player2);
		O12.setPieceCoordinateLocation(0, new Position(7, 8));
		board.placePiece(O12, player2);
		I22.rotatePiece();
		I22.setPieceCoordinateLocation(1, new Position(8, 7));
		board.placePiece(I22, player2);
		I32.setPieceCoordinateLocation(2, new Position(10, 6));
		board.placePiece(I32, player2);
		I42.rotatePiece();
		I42.setPieceCoordinateLocation(3, new Position(11, 3));
		board.placePiece(I42, player2);
		player2.piecesLeft.get(0).setPieceCoordinateLocation(3, new Position(7, 10));
		//player2.piecesLeft.get(0).setPieceCoordinateLocation(3, new Position(7, 11));
		board.followCurrentPiece(player2.piecesLeft.get(0), player2);
		board.print(board);
		return board;
	}
}