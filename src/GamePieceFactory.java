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
		return pieces;
	}
}