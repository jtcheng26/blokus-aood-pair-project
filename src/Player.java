import java.util.ArrayList;
import java.util.List;

public class Player {
	 private List<GamePiece> piecesLeft = new ArrayList<GamePiece>();
	 private List<GamePiece> piecesUsed = new ArrayList<GamePiece>();
	 private List<Position> cornerPositions = new ArrayList<Position>();
	 private int score = 0;
	 private String playerName;
	 private int playerID;
	 
	 Player (String name, int id, int difficulty) {
		 playerName = name;
		 playerID = id;
		 score = 0;
		 piecesLeft = GamePieceFactory.getGamePieceSet();
	 }
	 
	 public GamePiece getPiece (int index) {
		return piecesLeft.get(index);
	 }
	 
	 public String getName () {
		 return playerName;
	 }
	 
	 public int getScore () {
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
	 
	 public static void main(String[] args) {
		 Player player1 = new Player("matthew", 1, 0);
		 System.out.println(player1.getName());
		 System.out.println(player1.getScore());
		 System.out.println(player1.getID());
		 System.out.println(player1.getPiece(0).getName());
		 System.out.println(player1.getPiece(1).getName());
		 System.out.println(player1.getPiece(2).getName());
	 }
}
