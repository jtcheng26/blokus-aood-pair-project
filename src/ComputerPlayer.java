import java.util.ArrayList;
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
	/*
	 * easy AI
	 */
	boolean checkSizeFiveE = false;
	boolean checkSizeFourE = false;
	boolean checkSizeThreeE = false;
	boolean checkSizeTwoE = false;
	boolean checkSizeOneE = false;
			
	/*
	 * hard AI
	 */
	boolean checkSizeFive = false;
	boolean checkSizeFour = false;
	boolean checkSizeThree = false;
	boolean checkSizeTwo = false;
	boolean checkSizeOne = false;
	
	ComputerPlayer(String name, int id, int difficulty) {
		super(name, id, difficulty);
		difficultyLevel = difficulty;
	}
	
	public int getDifficultyLevel () {
		return difficultyLevel;
	}
	
	/*
	 * for AI's, access isValid and playerOut method from the board parameter
	 */
	public GamePiece getPiece (int difficulty, Gameboard board) {
		if (difficulty == EASY_AI) {
			return easyAI(board);
		} else if (difficulty == MEDIUM_AI) {
			return mediumAI(board);
		} else {
			return hardAI(board);
		}
	}
	public GamePiece easyAI (Gameboard board) {
		List<GamePiece> easyAIPieces = this.getPiecesLeft();
		if (oneSquarePieces > 0 && !checkSizeOneE) {
			/*
			 * size: 1
			 */
			oneSquarePieces--;
			return easyAIPieces.get(20);
		}
		if (twoSquarePieces > 0 && !checkSizeTwoE) {
			/*
			 * size: 2
			 */
			twoSquarePieces--;
			return easyAIPieces.get(19);
		}
		if (threeSquarePieces > 0 && !checkSizeThreeE) {
			/*
			 * size: 3
			 */
			int index;
			int counter = 0;
			List<GamePiece> checkedPieces = new ArrayList<GamePiece>();
			do {
				index = (int) Math.random()*(easyAIPieces.size()-17+1)+17;
				boolean newPiece = true;
				for (int i=0; i < checkedPieces.size(); i++) {
					if (!checkedPieces.get(i).equals(easyAIPieces.get(index)) && newPiece) {
						if (i == checkedPieces.size()-1) {
							counter++;
						}
					} else {
						newPiece = false;
					}
				}
				if (newPiece) {
					checkedPieces.add(easyAIPieces.get(index));
				}
			} while (!board.isValid(easyAIPieces.get(index), this) && counter < threeSquarePieces);
			if (counter >= threeSquarePieces) {
				checkSizeThreeE = true;
				checkSizeFiveE = false;
				checkSizeFourE = false;
				checkSizeTwoE = false;
				checkSizeOneE = false;
			} else {
				checkSizeThreeE = false;
				threeSquarePieces--;
				return easyAIPieces.get(index);
			}
		}
		if (fourSquarePieces > 0 && !checkSizeFourE) {
			/*
			 * size: 4
			 */
			int index = 0;
			int counter = 0;
			List<GamePiece> checkedPieces = new ArrayList<GamePiece>();
			do {
				index = (int) Math.random()*(easyAIPieces.size()-13+1)+13;
				boolean newPiece = true;
				for (int i=0; i < checkedPieces.size(); i++) {
					if (!checkedPieces.get(i).equals(easyAIPieces.get(index)) && newPiece) {
						if (i == checkedPieces.size()-1) {
							counter++;
						}
					} else {
						newPiece = false;
					}
				}
				if (newPiece) {
					checkedPieces.add(easyAIPieces.get(index));
				}
			} while (!board.isValid(easyAIPieces.get(index), this) && counter < fourSquarePieces);
			if (counter >= fourSquarePieces) {
				checkSizeFourE = true;
				checkSizeFiveE = false;
				checkSizeThreeE = false;
				checkSizeTwoE = false;
				checkSizeOneE = false;
			} else {
				checkSizeFourE = false;
				fourSquarePieces--;
				return easyAIPieces.get(index);
			}
		}
		if (fiveSquarePieces > 0 && !checkSizeFive) {
			/*
			 * size: 5
			 */
			int index = 0;
			int counter = 0;
			List<GamePiece> checkedPieces = new ArrayList<GamePiece>();
			do {
				index = (int) Math.random()*(fiveSquarePieces);
				boolean newPiece = true;
				for (int i=0; i < checkedPieces.size(); i++) {
					if (!checkedPieces.get(i).equals(easyAIPieces.get(index)) && newPiece) {
						if (i == checkedPieces.size()-1) {
							counter++;
						}
					} else {
						newPiece = false;
					}
				}
				if (newPiece) {
					checkedPieces.add(easyAIPieces.get(index));
				}
			} while (!board.isValid(easyAIPieces.get(index), this) && counter < fiveSquarePieces);
			if (counter >= fiveSquarePieces) {
				checkSizeFiveE = true;
				checkSizeFourE = false;
				checkSizeThreeE = false;
				checkSizeTwoE = false;
				checkSizeOneE = false;
			} else {
				checkSizeFiveE = false;
				fiveSquarePieces--;
				return easyAIPieces.get(index);
			}
		}
		return null;
	}
	
	public GamePiece mediumAI (Gameboard board) {
		List<GamePiece> mediumAIPieces = this.getPiecesLeft();
		int index = 0;
		do {
			index = (int) Math.random()*(mediumAIPieces.size());
		} while (!board.isValid(mediumAIPieces.get(index), this));
		return mediumAIPieces.get(index);
	}
	
	public GamePiece hardAI (Gameboard board) {
		List<GamePiece> hardAIPieces = this.getPiecesLeft();
		if (fiveSquarePieces > 0 && !checkSizeFive) {
			int index = 0;
			int counter = 0;
			List<GamePiece> checkedPieces = new ArrayList<GamePiece>();
			do {
				index = (int) Math.random()*(fiveSquarePieces);
				boolean newPiece = true;
				for (int i=0; i < checkedPieces.size(); i++) {
					if (!checkedPieces.get(i).equals(hardAIPieces.get(index)) && newPiece) {
						if (i == checkedPieces.size()-1) {
							counter++;
						}
					} else {
						newPiece = false;
					}
				}
				if (newPiece) {
					checkedPieces.add(hardAIPieces.get(index));
				}
			} while (!board.isValid(hardAIPieces.get(index), this) && counter < fiveSquarePieces);
			if (counter >= fiveSquarePieces) {
				checkSizeFive = true;
			} else {
				checkSizeFive = false;
				checkSizeFour = false;
				checkSizeThree = false;
				checkSizeTwo = false;
				checkSizeOne = false;
				fiveSquarePieces--;
				return hardAIPieces.get(index);
			}
		}
		if (fourSquarePieces > 0 && !checkSizeFour) {
			int index = 0;
			int counter = 0;
			List<GamePiece> checkedPieces = new ArrayList<GamePiece>();
			do {
				index = (int) Math.random()*(fourSquarePieces)+fiveSquarePieces;
				boolean newPiece = true;
				for (int i=0; i < checkedPieces.size(); i++) {
					if (!checkedPieces.get(i).equals(hardAIPieces.get(index)) && newPiece) {
						if (i == checkedPieces.size()-1) {
							counter++;
						}
					} else {
						newPiece = false;
					}
				}
				if (newPiece) {
					checkedPieces.add(hardAIPieces.get(index));
				}
			} while (!board.isValid(hardAIPieces.get(index), this) && counter < fourSquarePieces);
			if (counter >= fourSquarePieces) {
				checkSizeFour = true;
			} else {
				checkSizeFive = false;
				checkSizeFour = false;
				checkSizeThree = false;
				checkSizeTwo = false;
				checkSizeOne = false;
				fourSquarePieces--;
				return hardAIPieces.get(index);
			}
		}
		if (threeSquarePieces > 0 && !checkSizeThree) {
			int index = 0;
			int counter = 0;
			List<GamePiece> checkedPieces = new ArrayList<GamePiece>();
			do {
				index = (int) Math.random()*(threeSquarePieces)+fourSquarePieces+fiveSquarePieces;
				boolean newPiece = true;
				for (int i=0; i < checkedPieces.size(); i++) {
					if (!checkedPieces.get(i).equals(hardAIPieces.get(index)) && newPiece) {
						if (i == checkedPieces.size()-1) {
							counter++;
						}
					} else {
						newPiece = false;
					}
				}
				if (newPiece) {
					checkedPieces.add(hardAIPieces.get(index));
				}
			} while (!board.isValid(hardAIPieces.get(index), this) && counter < threeSquarePieces);
			if (counter >= threeSquarePieces) {
				checkSizeThree = true;
			} else {
				checkSizeFive = false;
				checkSizeFour = false;
				checkSizeThree = false;
				checkSizeTwo = false;
				checkSizeOne = false;
				threeSquarePieces--;
				return hardAIPieces.get(index);
			}
		}
		if (twoSquarePieces > 0 && !checkSizeTwo) {
			int index = 0;
			int counter = 0;
			List<GamePiece> checkedPieces = new ArrayList<GamePiece>();
			do {
				index = (int) Math.random()*(twoSquarePieces)+fiveSquarePieces+fourSquarePieces+threeSquarePieces;
				boolean newPiece = true;
				for (int i=0; i < checkedPieces.size(); i++) {
					if (!checkedPieces.get(i).equals(hardAIPieces.get(index)) && newPiece) {
						if (i == checkedPieces.size()-1) {
							counter++;
						}
					} else {
						newPiece = false;
					}
				}
				if (newPiece) {
					checkedPieces.add(hardAIPieces.get(index));
				}
			} while (!board.isValid(hardAIPieces.get(index), this) && counter < twoSquarePieces);
			if (counter >= twoSquarePieces) {
				checkSizeTwo = true;
			} else {
				checkSizeFive = false;
				checkSizeFour = false;
				checkSizeThree = false;
				checkSizeTwo = false;
				checkSizeOne = false;
				twoSquarePieces--;
				return hardAIPieces.get(index);
			}
		}
		if (oneSquarePieces > 0 && !checkSizeOne) {
			int index = 0;
			int counter = 0;
			List<GamePiece> checkedPieces = new ArrayList<GamePiece>();
			do {
				index = (int) Math.random()*(oneSquarePieces)+fiveSquarePieces+fourSquarePieces+threeSquarePieces+twoSquarePieces;
				boolean newPiece = true;
				for (int i=0; i < checkedPieces.size(); i++) {
					if (!checkedPieces.get(i).equals(hardAIPieces.get(index)) && newPiece) {
						if (i == checkedPieces.size()-1) {
							counter++;
						}
					} else {
						newPiece = false;
					}
				}
				if (newPiece) {
					checkedPieces.add(hardAIPieces.get(index));
				}
			} while (!board.isValid(hardAIPieces.get(index), this) && counter < oneSquarePieces);
			if (counter >= oneSquarePieces) {
				checkSizeOne = true;
			} else {
				checkSizeFive = false;
				checkSizeFour = false;
				checkSizeThree = false;
				checkSizeTwo = false;
				checkSizeOne = false;
				oneSquarePieces--;
				return hardAIPieces.get(index);
			}
		}
		return null;
	}
	
	public static void main (String[] args) {
		ComputerPlayer player = new ComputerPlayer("Computer Player 1", 1, 0);
		System.out.println(player.getPiecesLeft().get(20).getName() == "O1 Shape");
	}
}
