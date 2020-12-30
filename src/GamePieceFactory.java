import java.util.List;
import java.util.ArrayList;

class GamePieceFactory {
	public static List<GamePiece> getGamePieceSet() { // sorted biggest -> smallest
		ArrayList<GamePiece> pieces = new ArrayList<GamePiece>();
		// 5-square pieces
		pieces.add(new LPiece());
		pieces.add(new XPiece());
		pieces.add(new IPiece());
		pieces.add(new TPiece());
		pieces.add(new IPiece());
		pieces.add(new BPiece());
		pieces.add(new CPiece());
		return pieces;
	}
}

class LPiece extends GamePiece {
	LPiece() {
		super("L Shape", new Position(-1, -1), getPieceDeltas());
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

class XPiece extends GamePiece {
	XPiece() {
		super("Cross Shape", new Position(-1, -1), getPieceDeltas());
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

class WPiece extends GamePiece {
	WPiece() {
		super("W Shape", new Position(-1, -1), getPieceDeltas());
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
class IPiece extends GamePiece {
	IPiece() {
		super("I Shape", new Position(-2, 0), getPieceDeltas());
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
class TPiece extends GamePiece {
	TPiece() {
		super("T Shape", new Position(-1, -1), getPieceDeltas());
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
class BPiece extends GamePiece {
	BPiece() {
		super("b Shape", new Position(-1, -1), getPieceDeltas());
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
class CPiece extends GamePiece {
	CPiece() {
		super("C Shape", new Position(-1, -1), getPieceDeltas());
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