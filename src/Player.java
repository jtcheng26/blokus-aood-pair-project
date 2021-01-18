import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {
	 public List<GamePiece> piecesLeft = new ArrayList<GamePiece>();
	 private List<GamePiece> piecesUsed = new ArrayList<GamePiece>();
	 private List<Position> cornerPositions = new ArrayList<Position>();
	 private int score;
	 private String playerName;
	 private int playerID;
	 public static final int HUMAN = 0;
	 public static final int EASY_AI = 1;
	 public static final int MEDIUM_AI = 2;
	 public static final int HARD_AI = 3;
	 private int difficultyLevel;
	 
	 Player (String name, int id, int difficulty) {
		 playerName = name;
		 playerID = id;
		 score = 0;
		 piecesLeft = GamePieceFactory.getGamePieceSet();
		 difficultyLevel = difficulty;
	 }
	 
	 public int getDifficultyLevel () {
		return difficultyLevel;
	}
	 
	 public GamePiece getPiece (int index, Gameboard board) {
		return piecesLeft.get(index);
	 }
	 
	 public String getName () {
		 return playerName;
	 }
	 
	 public int getScore () {
		 score = 0;
		 if (piecesUsed.size() > 0) {
			 for (int i = 0; i < piecesUsed.size(); i++) {
				 score = score+piecesUsed.get(i).getPieceCoordinates().size();
			 }
		 }
		 return score;
	 }
	 
	 public int getID () {
		 return playerID;
	 }
	 
	 public List<GamePiece> getPiecesLeft () {
		 return piecesLeft;
	 }
	 
	 public List<GamePiece> getPiecesUsed () {
		 return piecesUsed;
	 }
	 
	 public List<Position> getCornerPositions () {
		 return cornerPositions;
	 }
}
