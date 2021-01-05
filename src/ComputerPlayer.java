import java.util.List;
import java.util.Random;

public class ComputerPlayer extends Player {
	private int difficultyLevel;
	public static final int EASY_AI = 1;
	public static final int MEDIUM_AI = 2;
	public static final int HARD_AI = 3;
	private int oneSquarePieces = 1;
	private int twoSquarePieces = 1;
	private int threeSquarePieces = 2;
	private int fourSquarePieces = 5;
	private int fiveSquarePieces = 12;
	
	ComputerPlayer(String name, int id, int difficulty) {
		super(name, id, difficulty);
		difficultyLevel = difficulty;
	}
	
	public int getDifficultyLevel () {
		return difficultyLevel;
	}
	
	public GamePiece easyAI () {
		List<GamePiece> easyAIPieces = this.getPiecesLeft();
		if (easyAIPieces.size() == 21) {
			/*
			 * size: 1
			 */
			return easyAIPieces.get(20);
		} else if (easyAIPieces.size() == 20) {
			/*
			 * size: 2
			 */
			return easyAIPieces.get(19);
		} else if (easyAIPieces.size() == 19) {
			/*
			 * size: 3
			 */
			int index = (int) Math.random()*(19-17+1)+17;
			return  easyAIPieces.get(index);
		} else if (easyAIPieces.size() == 18) {
			/*
			 * size: 3
			 */
			return easyAIPieces.get(17);
		} else if (easyAIPieces.size() <= 17 && easyAIPieces.size() > 12) {
			/*
			 * size: 4
			 */
			int index = (int) Math.random()*(easyAIPieces.size()-13+1)+13;
			return easyAIPieces.get(index);
		} else {
			/*
			 * size: 5
			 */
			int index = (int) Math.random()*(easyAIPieces.size()+1);
			return easyAIPieces.get(index);
		}
	}
	
	public GamePiece mediumAI () {
		List<GamePiece> mediumAIPieces = this.getPiecesLeft();
		int index = (int) Math.random()*(mediumAIPieces.size()+1);
		return mediumAIPieces.get(index);
	}
	
	public GamePiece hardAI () {
		List<GamePiece> hardAIPieces = this.getPiecesLeft();
		
	}
}
