import java.util.ArrayList;
import java.util.List;

public class Player {
	 private List<GamePiece> piecesLeft = new ArrayList<GamePiece>();
	 private int score;
	 private String playerName;
	 private int playerID;
	 
	 Player (String name, int id) {
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
		 for (int i = 0; i < this.getPiecesLeft().size(); i++) {
			 score = 89-this.getPiecesLeft().get(i).getPieceCoordinates().size();
		 }
		 return score;
	 }
	 
	 public int getID () {
		 return playerID;
	 }
	 
	 public List<GamePiece> getPiecesLeft () {
		 return piecesLeft;
	 }
	 
	 public static void main(String[] args) {
		 Player player1 = new Player("matthew", 1);
		 System.out.println(player1.getName());
		 System.out.println(player1.getScore());
		 System.out.println(player1.getID());
		 System.out.println(player1.getPiece(0).getName());
		 System.out.println(player1.getPiece(1).getName());
		 System.out.println(player1.getPiece(2).getName());
	 }
}
