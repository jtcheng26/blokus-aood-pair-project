import java.util.List;
import java.util.ArrayList;

class GamePieceFactory {
	public static List<GamePiece> getGamePieceSet() { // sorted biggest -> smallest
		List<GamePiece> pieces = new ArrayList<GamePiece>();
		// 5-square pieces
		pieces.addAll(getGamePieceSet5());
		// 4-square pieces
		pieces.addAll(getGamePieceSet4());
		// 3-square pieces
		pieces.addAll(getGamePieceSet3());
		// 2-square pieces
		pieces.addAll(getGamePieceSet2());
		// 1-square pieces
		pieces.addAll(getGamePieceSet1());
		return pieces;
	}
	public static List<GamePiece> getGamePieceSet5() {
		List<GamePiece> pieces = new ArrayList<GamePiece>();
		pieces.add(new V5Piece());
		pieces.add(new X5Piece());
		pieces.add(new W5Piece());
		pieces.add(new T5Piece());
		pieces.add(new I5Piece());
		pieces.add(new P5Piece());
		pieces.add(new U5Piece());
		pieces.add(new Z5Piece());
		pieces.add(new L5Piece());
		pieces.add(new N5Piece());
		pieces.add(new Y5Piece());
		pieces.add(new F5Piece());
		return pieces;
	}
	public static List<GamePiece> getGamePieceSet4() {
		List<GamePiece> pieces = new ArrayList<GamePiece>();
		pieces.add(new Z4Piece());
		pieces.add(new O4Piece());
		pieces.add(new T4Piece());
		pieces.add(new L4Piece());
		pieces.add(new I4Piece());
		return pieces;
	}
	public static List<GamePiece> getGamePieceSet3() {
		List<GamePiece> pieces = new ArrayList<GamePiece>();
		pieces.add(new V3Piece());
		pieces.add(new I3Piece());
		return pieces;
	}
	public static List<GamePiece> getGamePieceSet2() {
		List<GamePiece> pieces = new ArrayList<GamePiece>();
		pieces.add(new I2Piece());
		return pieces;
	}
	public static List<GamePiece> getGamePieceSet1() {
		List<GamePiece> pieces = new ArrayList<GamePiece>();
		pieces.add(new O1Piece());
		return pieces;
	}
}
// 5-square pieces
class V5Piece extends GamePiece {
	V5Piece() {
		super("V5 Shape", new Position(-1, -1), getPieceDeltas());
	}
	private static List<Position> getPieceDeltas() {
		List<Position> pieceDeltas = new ArrayList<Position>();
		pieceDeltas.add(new Position(1, 1));
		pieceDeltas.add(new Position(2, 1));
		pieceDeltas.add(new Position(3, 1));
		pieceDeltas.add(new Position(1, 2));
		pieceDeltas.add(new Position(1, 3));
		return pieceDeltas;
	}
}

class X5Piece extends GamePiece {
	X5Piece() {
		super("X5 Shape", new Position(-1, -1), getPieceDeltas());
	}
	private static List<Position> getPieceDeltas() {
		List<Position> pieceDeltas = new ArrayList<Position>();
		pieceDeltas.add(new Position(2, 2));
		pieceDeltas.add(new Position(2, 1));
		pieceDeltas.add(new Position(2, 3));
		pieceDeltas.add(new Position(1, 2));
		pieceDeltas.add(new Position(3, 2));
		return pieceDeltas;
	}
}

class W5Piece extends GamePiece {
	W5Piece() {
		super("W5 Shape", new Position(-1, -1), getPieceDeltas());
	}
	private static List<Position> getPieceDeltas() {
		List<Position> pieceDeltas = new ArrayList<Position>();
		pieceDeltas.add(new Position(2, 2));
		pieceDeltas.add(new Position(2, 3));
		pieceDeltas.add(new Position(1, 2));
		pieceDeltas.add(new Position(1, 1));
		pieceDeltas.add(new Position(3, 3));
		return pieceDeltas;
	}
}
class I5Piece extends GamePiece {
	I5Piece() {
		super("I5 Shape", new Position(-2, 0), getPieceDeltas());
	}
	private static List<Position> getPieceDeltas() {
		List<Position> pieceDeltas = new ArrayList<Position>();
		pieceDeltas.add(new Position(2, 2));
		pieceDeltas.add(new Position(2, 3));
		pieceDeltas.add(new Position(2, 4));
		pieceDeltas.add(new Position(2, 1));
		pieceDeltas.add(new Position(2, 0));
		return pieceDeltas;
	}
}
class T5Piece extends GamePiece {
	T5Piece() {
		super("T5 Shape", new Position(-1, -1), getPieceDeltas());
	}
	private static List<Position> getPieceDeltas() {
		List<Position> pieceDeltas = new ArrayList<Position>();
		pieceDeltas.add(new Position(2, 2));
		pieceDeltas.add(new Position(2, 3));
		pieceDeltas.add(new Position(2, 1));
		pieceDeltas.add(new Position(1, 1));
		pieceDeltas.add(new Position(3, 1));
		return pieceDeltas;
	}
}
class P5Piece extends GamePiece {
	P5Piece() {
		super("P5 Shape", new Position(-1, -1), getPieceDeltas());
	}
	private static List<Position> getPieceDeltas() {
		List<Position> pieceDeltas = new ArrayList<Position>();
		pieceDeltas.add(new Position(2, 2));
		pieceDeltas.add(new Position(2, 1));
		pieceDeltas.add(new Position(1, 1));
		pieceDeltas.add(new Position(1, 2));
		pieceDeltas.add(new Position(1, 3));
		return pieceDeltas;
	}
}
class U5Piece extends GamePiece {
	U5Piece() {
		super("U5 Shape", new Position(-1, -1), getPieceDeltas());
	}
	private static List<Position> getPieceDeltas() {
		List<Position> pieceDeltas = new ArrayList<Position>();
		pieceDeltas.add(new Position(2, 3));
		pieceDeltas.add(new Position(2, 1));
		pieceDeltas.add(new Position(1, 1));
		pieceDeltas.add(new Position(1, 2));
		pieceDeltas.add(new Position(1, 3));
		return pieceDeltas;
	}
}
class Z5Piece extends GamePiece {
	Z5Piece() {
		super("Z5 Shape", new Position(-1, -1), getPieceDeltas());
	}
	private static List<Position> getPieceDeltas() {
		List<Position> pieceDeltas = new ArrayList<Position>();
		pieceDeltas.add(new Position(2, 2));
		pieceDeltas.add(new Position(2, 3));
		pieceDeltas.add(new Position(2, 1));
		pieceDeltas.add(new Position(1, 1));
		pieceDeltas.add(new Position(3, 3));
		return pieceDeltas;
	}
}
class L5Piece extends GamePiece {
	L5Piece() {
		super("L5 Shape", new Position(-1, -1), getPieceDeltas());
	}
	private static List<Position> getPieceDeltas() {
		List<Position> pieceDeltas = new ArrayList<Position>();
		pieceDeltas.add(new Position(2, 1));
		pieceDeltas.add(new Position(1, 4));
		pieceDeltas.add(new Position(1, 1));
		pieceDeltas.add(new Position(1, 2));
		pieceDeltas.add(new Position(1, 3));
		return pieceDeltas;
	}
}
class N5Piece extends GamePiece {
	N5Piece() {
		super("N5 Shape", new Position(-1, -1), getPieceDeltas());
	}
	private static List<Position> getPieceDeltas() {
		List<Position> pieceDeltas = new ArrayList<Position>();
		pieceDeltas.add(new Position(2, 2));
		pieceDeltas.add(new Position(2, 3));
		pieceDeltas.add(new Position(2, 4));
		pieceDeltas.add(new Position(1, 2));
		pieceDeltas.add(new Position(1, 1));
		return pieceDeltas;
	}
}
class Y5Piece extends GamePiece {
	Y5Piece() {
		super("Y5 Shape", new Position(-2, 0), getPieceDeltas());
	}
	private static List<Position> getPieceDeltas() {
		List<Position> pieceDeltas = new ArrayList<Position>();
		pieceDeltas.add(new Position(2, 2));
		pieceDeltas.add(new Position(2, 3));
		pieceDeltas.add(new Position(2, 1));
		pieceDeltas.add(new Position(2, 0));
		pieceDeltas.add(new Position(3, 2));
		return pieceDeltas;
	}
}
class F5Piece extends GamePiece {
	F5Piece() {
		super("F5 Shape", new Position(-1, -1), getPieceDeltas());
	}
	private static List<Position> getPieceDeltas() {
		List<Position> pieceDeltas = new ArrayList<Position>();
		pieceDeltas.add(new Position(2, 2));
		pieceDeltas.add(new Position(2, 3));
		pieceDeltas.add(new Position(2, 1));
		pieceDeltas.add(new Position(1, 1));
		pieceDeltas.add(new Position(3, 2));
		return pieceDeltas;
	}
}
// 4-square pieces
class Z4Piece extends GamePiece {
	Z4Piece() {
		super("Z4 Shape", new Position(-1, -1), getPieceDeltas());
	}
	private static List<Position> getPieceDeltas() {
		List<Position> pieceDeltas = new ArrayList<Position>();
		pieceDeltas.add(new Position(2, 2));
		pieceDeltas.add(new Position(2, 3));
		pieceDeltas.add(new Position(1, 2));
		pieceDeltas.add(new Position(1, 1));
		return pieceDeltas;
	}
}
class O4Piece extends GamePiece {
	O4Piece() {
		super("O4 Shape", new Position(-1, -1), getPieceDeltas());
	}
	private static List<Position> getPieceDeltas() {
		List<Position> pieceDeltas = new ArrayList<Position>();
		pieceDeltas.add(new Position(2, 2));
		pieceDeltas.add(new Position(2, 1));
		pieceDeltas.add(new Position(1, 2));
		pieceDeltas.add(new Position(1, 1));
		return pieceDeltas;
	}
}
class T4Piece extends GamePiece {
	T4Piece() {
		super("T4 Shape", new Position(-1, -2), getPieceDeltas());
	}
	private static List<Position> getPieceDeltas() {
		List<Position> pieceDeltas = new ArrayList<Position>();
		pieceDeltas.add(new Position(2, 2));
		pieceDeltas.add(new Position(2, 3));
		pieceDeltas.add(new Position(1, 2));
		pieceDeltas.add(new Position(3, 2));
		return pieceDeltas;
	}
}
class L4Piece extends GamePiece {
	L4Piece() {
		super("L4 Shape", new Position(-1, -1), getPieceDeltas());
	}
	private static List<Position> getPieceDeltas() {
		List<Position> pieceDeltas = new ArrayList<Position>();
		pieceDeltas.add(new Position(2, 1));
		pieceDeltas.add(new Position(1, 1));
		pieceDeltas.add(new Position(1, 2));
		pieceDeltas.add(new Position(1, 3));
		return pieceDeltas;
	}
}
class I4Piece extends GamePiece {
	I4Piece() {
		super("I4 Shape", new Position(-2, 0), getPieceDeltas());
	}
	private static List<Position> getPieceDeltas() {
		List<Position> pieceDeltas = new ArrayList<Position>();
		pieceDeltas.add(new Position(2, 2));
		pieceDeltas.add(new Position(2, 3));
		pieceDeltas.add(new Position(2, 1));
		pieceDeltas.add(new Position(2, 0));
		return pieceDeltas;
	}
}
// 3-square pieces
class V3Piece extends GamePiece {
	V3Piece() {
		super("V3 Shape", new Position(-1, -1), getPieceDeltas());
	}
	private static List<Position> getPieceDeltas() {
		List<Position> pieceDeltas = new ArrayList<Position>();
		pieceDeltas.add(new Position(2, 1));
		pieceDeltas.add(new Position(1, 1));
		pieceDeltas.add(new Position(1, 2));
		return pieceDeltas;
	}
}
class I3Piece extends GamePiece {
	I3Piece() {
		super("I3 Shape", new Position(-2, -1), getPieceDeltas());
	}
	private static List<Position> getPieceDeltas() {
		List<Position> pieceDeltas = new ArrayList<Position>();
		pieceDeltas.add(new Position(2, 2));
		pieceDeltas.add(new Position(2, 1));
		pieceDeltas.add(new Position(2, 3));
		return pieceDeltas;
	}
}
// 2-square pieces
class I2Piece extends GamePiece {
	I2Piece() {
		super("I2 Shape", new Position(-2, -1), getPieceDeltas());
	}
	private static List<Position> getPieceDeltas() {
		List<Position> pieceDeltas = new ArrayList<Position>();
		pieceDeltas.add(new Position(2, 2));
		pieceDeltas.add(new Position(2, 1));
		return pieceDeltas;
	}
}
// 1-square pieces
class O1Piece extends GamePiece {
	O1Piece() {
		super("O1 Shape", new Position(-2, -2), getPieceDeltas());
	}
	private static List<Position> getPieceDeltas() {
		List<Position> pieceDeltas = new ArrayList<Position>();
		pieceDeltas.add(new Position(2, 2));
		return pieceDeltas;
	}
}