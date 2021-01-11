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
		int rotationCounter = 0;
		if (oneSquarePieces > 0 && !checkSizeOneE) {
			oneSquarePieces--;
			return easyAIPieces.get(20);
		}
		if (twoSquarePieces > 0 && !checkSizeTwoE) {
			twoSquarePieces--;
			int doCounter = 0;
			do {
				for (int i=0; i < easyAIPieces.get(19).getPieceCoordinates().size(); i++) {
					for (int j=0; j < this.getCornerPositions().size(); j++) {
						int cornerIndex = (int) (Math.random()*(this.getCornerPositions().size()));
						easyAIPieces.get(19).setPieceCoordinateLocation(i, this.getCornerPositions().get(cornerIndex));
						if (board.isValid(easyAIPieces.get(19), this)) {
							i = easyAIPieces.get(19).getPieceCoordinates().size();
							j = this.getCornerPositions().size();
						}
					}
				}
				easyAIPieces.get(19).rotatePiece();
				doCounter++;
				if (doCounter > 3) {
					easyAIPieces.get(19).flipPiece();
				}
			} while (!board.isValid(easyAIPieces.get(19), this));
			return easyAIPieces.get(19);
		}
		if (threeSquarePieces > 0 && !checkSizeThreeE) {
			int index;
			int doCounter = 0;
			do {
				//randomizes piece
				index = (int) (Math.random()*(threeSquarePieces)+fourSquarePieces+fiveSquarePieces);
				//sets each position on the selected piece to each available corner position
				for (int i=0; i < easyAIPieces.get(index).getPieceCoordinates().size(); i++) {
					for (int j=0; j < this.getCornerPositions().size(); j++) {
						int cornerIndex = (int) (Math.random()*(this.getCornerPositions().size()));
						easyAIPieces.get(index).setPieceCoordinateLocation(i, this.getCornerPositions().get(cornerIndex));
						//valid position
						if (board.isValid(easyAIPieces.get(index), this)) {
							i = easyAIPieces.get(index).getPieceCoordinates().size();
							j = this.getCornerPositions().size();
						} 
						//invalid position, so rotation needed
						else if (i == easyAIPieces.get(index).getPieceCoordinates().size()-1
								&& j == this.getCornerPositions().size()-1) {
							easyAIPieces.get(index).rotatePiece();
							rotationCounter++;
							i=0;
							j=0;
							if (rotationCounter%4 == 0) {
								easyAIPieces.get(index).flipPiece();
							}
						}
						if (rotationCounter >= 8) {
							i = easyAIPieces.get(index).getPieceCoordinates().size();
							j = this.getCornerPositions().size();
						}
					}
				}
			} while (!board.isValid(easyAIPieces.get(index), this) && doCounter < threeSquarePieces);
			if (board.isValid(easyAIPieces.get(index), this)) {
				checkSizeThreeE = false;
				threeSquarePieces--;
				return easyAIPieces.get(index);
			} else {
				checkSizeThreeE = true;
				checkSizeFiveE = false;
				checkSizeFourE = false;
				checkSizeTwoE = false;
				checkSizeOneE = false;
			}
		}
		if (fourSquarePieces > 0 && !checkSizeFourE) {
			int index = 0;
			int doCounter = 0;
			do {
				//randomizes piece
				index = (int) (Math.random()*(fourSquarePieces)+fiveSquarePieces);
				//sets each position on the selected piece to each available corner position
				for (int i=0; i < easyAIPieces.get(index).getPieceCoordinates().size(); i++) {
					for (int j=0; j < this.getCornerPositions().size(); j++) {
						int cornerIndex = (int) (Math.random()*(this.getCornerPositions().size()));
						easyAIPieces.get(index).setPieceCoordinateLocation(i, this.getCornerPositions().get(cornerIndex));
						//valid position
						if (board.isValid(easyAIPieces.get(index), this)) {
							i = easyAIPieces.get(index).getPieceCoordinates().size();
							j = this.getCornerPositions().size();
						} 
						//invalid position, so rotation needed
						else if (i == easyAIPieces.get(index).getPieceCoordinates().size()-1
								&& j == this.getCornerPositions().size()-1) {
							easyAIPieces.get(index).rotatePiece();
							rotationCounter++;
							i=0;
							j=0;
							if (rotationCounter%4 == 0) {
								easyAIPieces.get(index).flipPiece();
							}
						}
						if (rotationCounter >= 8) {
							i = easyAIPieces.get(index).getPieceCoordinates().size();
							j = this.getCornerPositions().size();
						}
					}
				}
			} while (!board.isValid(easyAIPieces.get(index), this) && doCounter < fourSquarePieces);
			if (board.isValid(easyAIPieces.get(index), this)) {
				checkSizeFourE = false;
				fourSquarePieces--;
				return easyAIPieces.get(index);
			} else {
				checkSizeFourE = true;
				checkSizeFiveE = false;
				checkSizeThreeE = false;
				checkSizeTwoE = false;
				checkSizeOneE = false;
			}
		}
		if (fiveSquarePieces > 0 && !checkSizeFive) {
			int index = 0;
			int doCounter = 0;
			//List<GamePiece> checkedPieces = new ArrayList<GamePiece>();
			do {
				//randomizes piece
				index = (int) (Math.random()*(fiveSquarePieces));
				//sets each position on the selected piece to each available corner position
				for (int i=0; i < easyAIPieces.get(index).getPieceCoordinates().size(); i++) {
					for (int j=0; j < this.getCornerPositions().size(); j++) {
						int cornerIndex = (int) (Math.random()*(this.getCornerPositions().size()));
						easyAIPieces.get(index).setPieceCoordinateLocation(i, this.getCornerPositions().get(cornerIndex));
						//valid position
						if (board.isValid(easyAIPieces.get(index), this)) {
							i = easyAIPieces.get(index).getPieceCoordinates().size();
							j = this.getCornerPositions().size();
						} 
						//invalid position, so rotation needed
						else if (i == easyAIPieces.get(index).getPieceCoordinates().size()-1
								&& j == this.getCornerPositions().size()-1) {
							easyAIPieces.get(index).rotatePiece();
							rotationCounter++;
							i=0;
							j=0;
							if (rotationCounter%4 == 0) {
								easyAIPieces.get(index).flipPiece();
							}
						}
						if (rotationCounter >= 8) {
							i = easyAIPieces.get(index).getPieceCoordinates().size();
							j = this.getCornerPositions().size();
						}
					}
				}
			} while (!board.isValid(easyAIPieces.get(index), this) && doCounter < fiveSquarePieces);
			if (board.isValid(easyAIPieces.get(index), this)) {
				checkSizeFiveE = false;
				fiveSquarePieces--;
				return easyAIPieces.get(index);
			} else {
				checkSizeFiveE = true;
				checkSizeFourE = false;
				checkSizeThreeE = false;
				checkSizeTwoE = false;
				checkSizeOneE = false;
			}
		}
		
		return null;
	}
	
	public GamePiece mediumAI (Gameboard board) {
		List<GamePiece> mediumAIPieces = this.getPiecesLeft();
		int index = 0;
		int doCounter = 0;
		do {
			index = (int) (Math.random()*(mediumAIPieces.size()));
			for (int i=0; i < mediumAIPieces.get(index).getPieceCoordinates().size(); i++) {
				for (int j=0; j < this.getCornerPositions().size(); j++) {
					int cornerIndex = (int) (Math.random()*(this.getCornerPositions().size()));
					mediumAIPieces.get(index).setPieceCoordinateLocation(i, this.getCornerPositions().get(cornerIndex));
					if (board.isValid(mediumAIPieces.get(index), this)) {
						i = mediumAIPieces.get(index).getPieceCoordinates().size();
						j = this.getCornerPositions().size();
					}
				}
			}
			mediumAIPieces.get(index).rotatePiece();
			doCounter++;
			if (doCounter > 3) {
				mediumAIPieces.get(index).flipPiece();
			}
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
				index = (int) (Math.random()*(fiveSquarePieces));
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
				do {
					for (int i=0; i < hardAIPieces.get(index).getPieceCoordinates().size(); i++) {
						for (int j=0; j < this.getCornerPositions().size(); j++) {
							int cornerIndex = (int) (Math.random()*(this.getCornerPositions().size()));
							hardAIPieces.get(index).setPieceCoordinateLocation(i, this.getCornerPositions().get(cornerIndex));
							if (board.isValid(hardAIPieces.get(index), this)) {
								i = hardAIPieces.get(index).getPieceCoordinates().size();
								j = this.getCornerPositions().size();
							}
						}
					}
				} while (!board.isValid(hardAIPieces.get(index), this));
				return hardAIPieces.get(index);
			}
		}
		if (fourSquarePieces > 0 && !checkSizeFour) {
			int index = 0;
			int counter = 0;
			List<GamePiece> checkedPieces = new ArrayList<GamePiece>();
			do {
				index = (int) (Math.random()*(fourSquarePieces)+fiveSquarePieces);
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
				do {
					for (int i=0; i < hardAIPieces.get(index).getPieceCoordinates().size(); i++) {
						for (int j=0; j < this.getCornerPositions().size(); j++) {
							int cornerIndex = (int) (Math.random()*(this.getCornerPositions().size()));
							hardAIPieces.get(index).setPieceCoordinateLocation(i, this.getCornerPositions().get(cornerIndex));
							if (board.isValid(hardAIPieces.get(index), this)) {
								i = hardAIPieces.get(index).getPieceCoordinates().size();
								j = this.getCornerPositions().size();
							}
						}
					}
				} while (!board.isValid(hardAIPieces.get(index), this));
				return hardAIPieces.get(index);
			}
		}
		if (threeSquarePieces > 0 && !checkSizeThree) {
			int index = 0;
			int counter = 0;
			List<GamePiece> checkedPieces = new ArrayList<GamePiece>();
			do {
				index = (int) (Math.random()*(threeSquarePieces)+fourSquarePieces+fiveSquarePieces);
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
				do {
					for (int i=0; i < hardAIPieces.get(index).getPieceCoordinates().size(); i++) {
						for (int j=0; j < this.getCornerPositions().size(); j++) {
							int cornerIndex = (int) (Math.random()*(this.getCornerPositions().size()));
							hardAIPieces.get(index).setPieceCoordinateLocation(i, this.getCornerPositions().get(cornerIndex));
							if (board.isValid(hardAIPieces.get(index), this)) {
								i = hardAIPieces.get(index).getPieceCoordinates().size();
								j = this.getCornerPositions().size();
							}
						}
					}
				} while (!board.isValid(hardAIPieces.get(index), this));
				return hardAIPieces.get(index);
			}
		}
		if (twoSquarePieces > 0 && !checkSizeTwo) {
			int index = 0;
			int counter = 0;
			List<GamePiece> checkedPieces = new ArrayList<GamePiece>();
			do {
				index = (int) (Math.random()*(twoSquarePieces)+fiveSquarePieces+fourSquarePieces+threeSquarePieces);
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
				do {
					for (int i=0; i < hardAIPieces.get(index).getPieceCoordinates().size(); i++) {
						for (int j=0; j < this.getCornerPositions().size(); j++) {
							int cornerIndex = (int) (Math.random()*(this.getCornerPositions().size()));
							hardAIPieces.get(index).setPieceCoordinateLocation(i, this.getCornerPositions().get(cornerIndex));
							if (board.isValid(hardAIPieces.get(index), this)) {
								i = hardAIPieces.get(index).getPieceCoordinates().size();
								j = this.getCornerPositions().size();
							}
						}
					}
				} while (!board.isValid(hardAIPieces.get(index), this));
				return hardAIPieces.get(index);
			}
		}
		if (oneSquarePieces > 0 && !checkSizeOne) {
			int index = 0;
			int counter = 0;
			List<GamePiece> checkedPieces = new ArrayList<GamePiece>();
			do {
				index = (int) (Math.random()*(oneSquarePieces)+fiveSquarePieces+fourSquarePieces+threeSquarePieces+twoSquarePieces);
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
				do {
					for (int i=0; i < hardAIPieces.get(index).getPieceCoordinates().size(); i++) {
						for (int j=0; j < this.getCornerPositions().size(); j++) {
							int cornerIndex = (int) (Math.random()*(this.getCornerPositions().size()));
							hardAIPieces.get(index).setPieceCoordinateLocation(i, this.getCornerPositions().get(cornerIndex));
							if (board.isValid(hardAIPieces.get(index), this)) {
								i = hardAIPieces.get(index).getPieceCoordinates().size();
								j = this.getCornerPositions().size();
							}
						}
					}
				} while (!board.isValid(hardAIPieces.get(index), this));
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
