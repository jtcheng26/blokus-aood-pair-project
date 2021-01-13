import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ComputerPlayer extends Player {
	private int difficultyLevel;
	private List<Position> cornerPositions;
	/*
	public static final int EASY_AI = 1;
	public static final int MEDIUM_AI = 2;
	public static final int HARD_AI = 3;
	*/
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
	
	/*
	 * for AI's, access isValid and playerOut method from the board parameter
	 */
	public GamePiece getPiece (int difficulty, Gameboard board) {
		if (board.playerOut(this)) {
			System.out.println("computer player out");
			return null;
		} else if (difficulty == EASY_AI) {
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
			oneSquarePieces--;
			return easyAIPieces.get(fiveSquarePieces + fourSquarePieces + threeSquarePieces + twoSquarePieces);
		}
		if (twoSquarePieces > 0 && !checkSizeTwoE) {
			twoSquarePieces--;
			int doCounter = 0;
			int initialRotationCounter = 0;
			int k = fiveSquarePieces + fourSquarePieces + threeSquarePieces;
			cornerPositions = this.getCornerPositions();
			do {
				initialRotationCounter = (int) (Math.random()*4);
				for (int i=0; i < initialRotationCounter; i++) {
					easyAIPieces.get(k).rotatePiece();
				}
				for (int i=0; i < easyAIPieces.get(k).getPieceCoordinates().size(); i++) {
					for (int j=0; j < this.cornerPositions.size(); j++) {
						easyAIPieces.get(k).setPieceCoordinateLocation(i, this.cornerPositions.get(j));
						if (board.isValid(easyAIPieces.get(k), this)) {
							i = easyAIPieces.get(k).getPieceCoordinates().size();
							j = this.cornerPositions.size();
						}
					}
				}
				easyAIPieces.get(k).rotatePiece();
				doCounter++;
				if (doCounter == 4) {
					easyAIPieces.get(k).flipPiece();
				}
			} while (!board.isValid(easyAIPieces.get(k), this));
			return easyAIPieces.get(k);
		}
		if (threeSquarePieces > 0 && !checkSizeThreeE) {
			int doCounter = 0;
			int initialRotationCounter = 0;
			int rotationCounter = 0;
			int startOfThisSizeIndex = fourSquarePieces+fiveSquarePieces;
			//randomizes piece
			Collections.shuffle(easyAIPieces.subList(startOfThisSizeIndex, startOfThisSizeIndex+threeSquarePieces));
			cornerPositions = this.getCornerPositions();
			Collections.shuffle(cornerPositions);
			do {
				initialRotationCounter = (int) (Math.random()*4);
				for (int i=0; i < initialRotationCounter; i++) {
					easyAIPieces.get(startOfThisSizeIndex+doCounter).rotatePiece();
				}
				//sets each position on the selected piece to each available corner position
				for (int i=0; i < easyAIPieces.get(startOfThisSizeIndex+doCounter).getPieceCoordinates().size(); i++) {
					for (int j=0; j < this.cornerPositions.size(); j++) {
						easyAIPieces.get(startOfThisSizeIndex+doCounter).setPieceCoordinateLocation(i, this.cornerPositions.get(j));
						//valid position
						if (board.isValid(easyAIPieces.get(startOfThisSizeIndex+doCounter), this)) {
							i = easyAIPieces.get(startOfThisSizeIndex+doCounter).getPieceCoordinates().size();
							j = this.cornerPositions.size();
						} 
						//invalid position, so rotation needed
						else if (i == easyAIPieces.get(startOfThisSizeIndex+doCounter).getPieceCoordinates().size()-1
								&& j == this.cornerPositions.size()-1) {
							easyAIPieces.get(startOfThisSizeIndex+doCounter).rotatePiece();
							rotationCounter++;
							i=0;
							j=0;
							if (rotationCounter%4 == 0) {
								easyAIPieces.get(startOfThisSizeIndex+doCounter).flipPiece();
							}
						}
						if (rotationCounter >= 8) {
							i = easyAIPieces.get(startOfThisSizeIndex+doCounter).getPieceCoordinates().size();
							j = this.cornerPositions.size();
						}
					}
				}
				doCounter++;
			} while (doCounter < threeSquarePieces && !board.isValid(easyAIPieces.get(startOfThisSizeIndex+doCounter-1), this));
			if (board.isValid(easyAIPieces.get(startOfThisSizeIndex+doCounter-1), this)) {
				checkSizeThreeE = false;
				threeSquarePieces--;
				return easyAIPieces.get(startOfThisSizeIndex+doCounter-1);
			} else {
				checkSizeThreeE = true;
				checkSizeFiveE = false;
				checkSizeFourE = false;
				checkSizeTwoE = false;
				checkSizeOneE = false;
			}
		}
		if (fourSquarePieces > 0 && !checkSizeFourE) {
			int doCounter = 0;
			int initialRotationCounter = 0;
			int rotationCounter = 0;
			int startOfThisSizeIndex = fiveSquarePieces;
			//randomizes piece
			Collections.shuffle(easyAIPieces.subList(startOfThisSizeIndex, startOfThisSizeIndex+fourSquarePieces));
			cornerPositions = this.getCornerPositions();
			Collections.shuffle(cornerPositions);
			do {
				initialRotationCounter = (int) (Math.random()*4);
				for (int i=0; i < initialRotationCounter; i++) {
					easyAIPieces.get(startOfThisSizeIndex+doCounter).rotatePiece();
				}
				//sets each position on the selected piece to each available corner position
				for (int i=0; i < easyAIPieces.get(startOfThisSizeIndex+doCounter).getPieceCoordinates().size(); i++) {
					for (int j=0; j < this.cornerPositions.size(); j++) {
						easyAIPieces.get(startOfThisSizeIndex+doCounter).setPieceCoordinateLocation(i, this.cornerPositions.get(j));
						//valid position
						if (board.isValid(easyAIPieces.get(startOfThisSizeIndex+doCounter), this)) {
							i = easyAIPieces.get(startOfThisSizeIndex+doCounter).getPieceCoordinates().size();
							j = this.cornerPositions.size();
						} 
						//invalid position, so rotation needed
						else if (i == easyAIPieces.get(startOfThisSizeIndex+doCounter).getPieceCoordinates().size()-1
								&& j == this.cornerPositions.size()-1) {
							easyAIPieces.get(startOfThisSizeIndex+doCounter).rotatePiece();
							rotationCounter++;
							i=0;
							j=0;
							if (rotationCounter%4 == 0) {
								easyAIPieces.get(startOfThisSizeIndex+doCounter).flipPiece();
							}
						}
						if (rotationCounter >= 8) {
							i = easyAIPieces.get(startOfThisSizeIndex+doCounter).getPieceCoordinates().size();
							j = this.cornerPositions.size();
						}
					}
				}
				doCounter++;
			} while (doCounter < fourSquarePieces && !board.isValid(easyAIPieces.get(startOfThisSizeIndex+doCounter-1), this));
			if (board.isValid(easyAIPieces.get(startOfThisSizeIndex+doCounter-1), this)) {
				checkSizeFourE = false;
				fourSquarePieces--;
				return easyAIPieces.get(startOfThisSizeIndex+doCounter-1);
			} else {
				checkSizeFourE = true;
				checkSizeFiveE = false;
				checkSizeThreeE = false;
				checkSizeTwoE = false;
				checkSizeOneE = false;
			}
		}
		if (fiveSquarePieces > 0 && !checkSizeFive) {
			int doCounter = 0;
			int initialRotationCounter = 0;
			int rotationCounter = 0;
			int startOfThisSizeIndex = 0;
			//randomizes piece
			Collections.shuffle(easyAIPieces.subList(startOfThisSizeIndex, startOfThisSizeIndex+fiveSquarePieces));
			cornerPositions = this.getCornerPositions();
			Collections.shuffle(cornerPositions);
			do {
				initialRotationCounter = (int) (Math.random()*4);
				for (int i=0; i < initialRotationCounter; i++) {
					easyAIPieces.get(startOfThisSizeIndex+doCounter).rotatePiece();
				}
				//sets each position on the selected piece to each available corner position
				for (int i=0; i < easyAIPieces.get(startOfThisSizeIndex+doCounter).getPieceCoordinates().size(); i++) {
					for (int j=0; j < this.cornerPositions.size(); j++) {
						easyAIPieces.get(startOfThisSizeIndex+doCounter).setPieceCoordinateLocation(i, this.cornerPositions.get(j));
						//valid position
						if (board.isValid(easyAIPieces.get(startOfThisSizeIndex+doCounter), this)) {
							i = easyAIPieces.get(startOfThisSizeIndex+doCounter).getPieceCoordinates().size();
							j = this.cornerPositions.size();
						} 
						//invalid position, so rotation needed
						else if (i == easyAIPieces.get(startOfThisSizeIndex+doCounter).getPieceCoordinates().size()-1
								&& j == this.cornerPositions.size()-1) {
							easyAIPieces.get(startOfThisSizeIndex+doCounter).rotatePiece();
							rotationCounter++;
							i=0;
							j=0;
							if (rotationCounter%4 == 0) {
								easyAIPieces.get(startOfThisSizeIndex+doCounter).flipPiece();
							}
						}
						if (rotationCounter >= 8) {
							i = easyAIPieces.get(startOfThisSizeIndex+doCounter).getPieceCoordinates().size();
							j = this.cornerPositions.size();
						}
					}
				}
				doCounter++;
			} while (doCounter < fiveSquarePieces && !board.isValid(easyAIPieces.get(startOfThisSizeIndex+doCounter-1), this));
			if (board.isValid(easyAIPieces.get(startOfThisSizeIndex+doCounter-1), this)) {
				checkSizeFiveE = false;
				fiveSquarePieces--;
				return easyAIPieces.get(startOfThisSizeIndex+doCounter-1);
			} else {
				checkSizeFiveE = true;
				checkSizeFourE = false;
				checkSizeThreeE = false;
				checkSizeTwoE = false;
				checkSizeOneE = false;
			}
		}
		System.out.println("returning null for " + this.getName());
		System.out.println("playerout: " + board.playerOut(this));
		return null;
	}
	
	public GamePiece mediumAI (Gameboard board) {
		List<GamePiece> mediumAIPieces = this.getPiecesLeft();
		int index = 0;
		int doCounter = 0;
		int initialRotationCounter = 0;
		cornerPositions = this.getCornerPositions();
		Collections.shuffle(cornerPositions);
		do {
			index = (int) (Math.random()*(mediumAIPieces.size()));
			initialRotationCounter = (int) (Math.random()*4);
			for (int i=0; i < initialRotationCounter; i++) {
				mediumAIPieces.get(index).rotatePiece();
			}
			for (int i=0; i < mediumAIPieces.get(index).getPieceCoordinates().size(); i++) {
				for (int j=0; j < this.cornerPositions.size(); j++) {
					mediumAIPieces.get(index).setPieceCoordinateLocation(i, this.cornerPositions.get(j));
					if (board.isValid(mediumAIPieces.get(index), this)) {
						i = mediumAIPieces.get(index).getPieceCoordinates().size();
						j = this.cornerPositions.size();
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
			int doCounter = 0;
			int initialRotationCounter = 0;
			int rotationCounter = 0;
			int startOfThisSizeIndex = 0;
			Collections.shuffle(hardAIPieces.subList(startOfThisSizeIndex, startOfThisSizeIndex+fiveSquarePieces));
			cornerPositions = this.getCornerPositions();
			Collections.shuffle(cornerPositions);
			//randomizes piece
			do {
				initialRotationCounter = (int) (Math.random()*4);
				for (int i=0; i < initialRotationCounter; i++) {
					hardAIPieces.get(startOfThisSizeIndex+doCounter).rotatePiece();
				}
				//sets each position on the selected piece to each available corner position
				for (int i=0; i < hardAIPieces.get(startOfThisSizeIndex+doCounter).getPieceCoordinates().size(); i++) {
					for (int j=0; j < this.cornerPositions.size(); j++) {
						hardAIPieces.get(startOfThisSizeIndex+doCounter).setPieceCoordinateLocation(i, this.cornerPositions.get(j));
						//valid position
						if (board.isValid(hardAIPieces.get(startOfThisSizeIndex+doCounter), this)) {
							i = hardAIPieces.get(startOfThisSizeIndex+doCounter).getPieceCoordinates().size();
							j = this.cornerPositions.size();
						} 
						//invalid position, so rotation needed
						else if (i == hardAIPieces.get(startOfThisSizeIndex+doCounter).getPieceCoordinates().size()-1
								&& j == this.cornerPositions.size()-1) {
							hardAIPieces.get(startOfThisSizeIndex+doCounter).rotatePiece();
							rotationCounter++;
							i=0;
							j=0;
							if (rotationCounter%4 == 0) {
								hardAIPieces.get(startOfThisSizeIndex+doCounter).flipPiece();
							}
						}
						if (rotationCounter >= 8) {
							i = hardAIPieces.get(startOfThisSizeIndex+doCounter).getPieceCoordinates().size();
							j = this.cornerPositions.size();
						}
					}
				}
				doCounter++;
			} while (doCounter < fiveSquarePieces && !board.isValid(hardAIPieces.get(startOfThisSizeIndex+doCounter-1), this));
			if (board.isValid(hardAIPieces.get(startOfThisSizeIndex+doCounter-1), this)) {
				checkSizeFiveE = false;
				fiveSquarePieces--;
				return hardAIPieces.get(startOfThisSizeIndex+doCounter-1);
			} else {
				checkSizeFiveE = true;
				checkSizeFourE = false;
				checkSizeThreeE = false;
				checkSizeTwoE = false;
				checkSizeOneE = false;
			}
		}
		if (fourSquarePieces > 0 && !checkSizeFour) {
			//int index = 0;
			int doCounter = 0;
			int initialRotationCounter = 0;
			int rotationCounter = 0;
			int startOfThisSizeIndex = fiveSquarePieces;
			//randomizes piece
			Collections.shuffle(hardAIPieces.subList(startOfThisSizeIndex, startOfThisSizeIndex+fourSquarePieces));
			cornerPositions = this.getCornerPositions();
			Collections.shuffle(cornerPositions);
			do {
				initialRotationCounter = (int) (Math.random()*4);
				for (int i=0; i < initialRotationCounter; i++) {
					hardAIPieces.get(startOfThisSizeIndex+doCounter).rotatePiece();
				}
				//sets each position on the selected piece to each available corner position
				for (int i=0; i < hardAIPieces.get(startOfThisSizeIndex+doCounter).getPieceCoordinates().size(); i++) {
					for (int j=0; j < this.cornerPositions.size(); j++) {
						hardAIPieces.get(startOfThisSizeIndex+doCounter).setPieceCoordinateLocation(i, this.cornerPositions.get(j));
						//valid position
						if (board.isValid(hardAIPieces.get(startOfThisSizeIndex+doCounter), this)) {
							i = hardAIPieces.get(startOfThisSizeIndex+doCounter).getPieceCoordinates().size();
							j = this.cornerPositions.size();
						} 
						//invalid position, so rotation needed
						else if (i == hardAIPieces.get(startOfThisSizeIndex+doCounter).getPieceCoordinates().size()-1
								&& j == this.cornerPositions.size()-1) {
							hardAIPieces.get(startOfThisSizeIndex+doCounter).rotatePiece();
							rotationCounter++;
							i=0;
							j=0;
							if (rotationCounter%4 == 0) {
								hardAIPieces.get(startOfThisSizeIndex+doCounter).flipPiece();
							}
						}
						if (rotationCounter >= 8) {
							i = hardAIPieces.get(startOfThisSizeIndex+doCounter).getPieceCoordinates().size();
							j = this.cornerPositions.size();
						}
					}
				}
				doCounter++;
			} while (doCounter < fourSquarePieces && !board.isValid(hardAIPieces.get(startOfThisSizeIndex+doCounter-1), this));
			if (board.isValid(hardAIPieces.get(startOfThisSizeIndex+doCounter-1), this)) {
				checkSizeFourE = false;
				fourSquarePieces--;
				return hardAIPieces.get(startOfThisSizeIndex+doCounter-1);
			} else {
				checkSizeFiveE = false;
				checkSizeFourE = true;
				checkSizeThreeE = false;
				checkSizeTwoE = false;
				checkSizeOneE = false;
			}
		}
		if (threeSquarePieces > 0 && !checkSizeThree) {
			int doCounter = 0;
			int initialRotationCounter = 0;
			int rotationCounter = 0;
			int startOfThisSizeIndex = fiveSquarePieces+fourSquarePieces;
			//randomizes piece
			Collections.shuffle(hardAIPieces.subList(startOfThisSizeIndex, startOfThisSizeIndex+threeSquarePieces));
			cornerPositions = this.getCornerPositions();
			Collections.shuffle(cornerPositions);
			do {
				initialRotationCounter = (int) (Math.random()*4);
				for (int i=0; i < initialRotationCounter; i++) {
					hardAIPieces.get(startOfThisSizeIndex+doCounter).rotatePiece();
				}
				//sets each position on the selected piece to each available corner position
				for (int i=0; i < hardAIPieces.get(startOfThisSizeIndex+doCounter).getPieceCoordinates().size(); i++) {
					for (int j=0; j < this.cornerPositions.size(); j++) {
						hardAIPieces.get(startOfThisSizeIndex+doCounter).setPieceCoordinateLocation(i, this.cornerPositions.get(j));
						//valid position
						if (board.isValid(hardAIPieces.get(startOfThisSizeIndex+doCounter), this)) {
							i = hardAIPieces.get(startOfThisSizeIndex+doCounter).getPieceCoordinates().size();
							j = this.cornerPositions.size();
						} 
						//invalid position, so rotation needed
						else if (i == hardAIPieces.get(startOfThisSizeIndex+doCounter).getPieceCoordinates().size()-1
								&& j == this.cornerPositions.size()-1) {
							hardAIPieces.get(startOfThisSizeIndex+doCounter).rotatePiece();
							rotationCounter++;
							i=0;
							j=0;
							if (rotationCounter%4 == 0) {
								hardAIPieces.get(startOfThisSizeIndex+doCounter).flipPiece();
							}
						}
						if (rotationCounter >= 8) {
							i = hardAIPieces.get(startOfThisSizeIndex+doCounter).getPieceCoordinates().size();
							j = this.cornerPositions.size();
						}
					}
				}
				doCounter++;
			} while (doCounter < threeSquarePieces && !board.isValid(hardAIPieces.get(startOfThisSizeIndex+doCounter-1), this));
			if (board.isValid(hardAIPieces.get(startOfThisSizeIndex+doCounter-1), this)) {
				checkSizeThreeE = false;
				threeSquarePieces--;
				return hardAIPieces.get(startOfThisSizeIndex+doCounter-1);
			} else {
				checkSizeFiveE = false;
				checkSizeFourE = false;
				checkSizeThreeE = true;
				checkSizeTwoE = false;
				checkSizeOneE = false;
			}
		}
		if (twoSquarePieces > 0 && !checkSizeTwo) {
			int doCounter = 0;
			int initialRotationCounter = 0;
			int rotationCounter = 0;
			int startOfThisSizeIndex = fiveSquarePieces+fourSquarePieces+threeSquarePieces;
			//randomizes piece
			Collections.shuffle(hardAIPieces.subList(startOfThisSizeIndex, startOfThisSizeIndex+twoSquarePieces));
			cornerPositions = this.getCornerPositions();
			Collections.shuffle(cornerPositions);
			do {
				initialRotationCounter = (int) (Math.random()*4);
				for (int i=0; i < initialRotationCounter; i++) {
					hardAIPieces.get(startOfThisSizeIndex+doCounter).rotatePiece();
				}
				//sets each position on the selected piece to each available corner position
				for (int i=0; i < hardAIPieces.get(startOfThisSizeIndex+doCounter).getPieceCoordinates().size(); i++) {
					for (int j=0; j < this.cornerPositions.size(); j++) {
						hardAIPieces.get(startOfThisSizeIndex+doCounter).setPieceCoordinateLocation(i, this.cornerPositions.get(j));
						//valid position
						if (board.isValid(hardAIPieces.get(startOfThisSizeIndex+doCounter), this)) {
							i = hardAIPieces.get(startOfThisSizeIndex+doCounter).getPieceCoordinates().size();
							j = this.cornerPositions.size();
						} 
						//invalid position, so rotation needed
						else if (i == hardAIPieces.get(startOfThisSizeIndex+doCounter).getPieceCoordinates().size()-1
								&& j == this.cornerPositions.size()-1) {
							hardAIPieces.get(startOfThisSizeIndex+doCounter).rotatePiece();
							rotationCounter++;
							i=0;
							j=0;
							if (rotationCounter%4 == 0) {
								hardAIPieces.get(startOfThisSizeIndex+doCounter).flipPiece();
							}
						}
						if (rotationCounter >= 8) {
							i = hardAIPieces.get(startOfThisSizeIndex+doCounter).getPieceCoordinates().size();
							j = this.cornerPositions.size();
						}
					}
				}
				doCounter++;
			} while (doCounter < twoSquarePieces && !board.isValid(hardAIPieces.get(startOfThisSizeIndex+doCounter-1), this));
			if (board.isValid(hardAIPieces.get(startOfThisSizeIndex+doCounter-1), this)) {
				checkSizeTwoE = false;
				twoSquarePieces--;
				return hardAIPieces.get(startOfThisSizeIndex+doCounter-1);
			} else {
				checkSizeFiveE = false;
				checkSizeFourE = false;
				checkSizeThreeE = false;
				checkSizeTwoE = true;
				checkSizeOneE = false;
			}
		}
		if (oneSquarePieces > 0 && !checkSizeOne) {
			int doCounter = 0;
			int initialRotationCounter = 0;
			int rotationCounter = 0;
			int startOfThisSizeIndex = fiveSquarePieces+fourSquarePieces+threeSquarePieces+twoSquarePieces;
			//randomizes piece
			Collections.shuffle(hardAIPieces.subList(startOfThisSizeIndex, startOfThisSizeIndex+oneSquarePieces));
			cornerPositions = this.getCornerPositions();
			Collections.shuffle(cornerPositions);
			do {
				initialRotationCounter = (int) (Math.random()*4);
				for (int i=0; i < initialRotationCounter; i++) {
					hardAIPieces.get(startOfThisSizeIndex+doCounter).rotatePiece();
				}
				//sets each position on the selected piece to each available corner position
				for (int i=0; i < hardAIPieces.get(startOfThisSizeIndex+doCounter).getPieceCoordinates().size(); i++) {
					for (int j=0; j < this.cornerPositions.size(); j++) {
						hardAIPieces.get(startOfThisSizeIndex+doCounter).setPieceCoordinateLocation(i, this.cornerPositions.get(j));
						//valid position
						if (board.isValid(hardAIPieces.get(startOfThisSizeIndex+doCounter), this)) {
							i = hardAIPieces.get(startOfThisSizeIndex+doCounter).getPieceCoordinates().size();
							j = this.cornerPositions.size();
						} 
						//invalid position, so rotation needed
						else if (i == hardAIPieces.get(startOfThisSizeIndex+doCounter).getPieceCoordinates().size()-1
								&& j == this.cornerPositions.size()-1) {
							hardAIPieces.get(startOfThisSizeIndex+doCounter).rotatePiece();
							rotationCounter++;
							i=0;
							j=0;
							if (rotationCounter%4 == 0) {
								hardAIPieces.get(startOfThisSizeIndex+doCounter).flipPiece();
							}
						}
						if (rotationCounter >= 8) {
							i = hardAIPieces.get(startOfThisSizeIndex+doCounter).getPieceCoordinates().size();
							j = this.cornerPositions.size();
						}
					}
				}
				doCounter++;
			} while (doCounter < oneSquarePieces && !board.isValid(hardAIPieces.get(startOfThisSizeIndex+doCounter-1), this));
			if (board.isValid(hardAIPieces.get(startOfThisSizeIndex+doCounter-1), this)) {
				checkSizeOneE = false;
				oneSquarePieces--;
				return hardAIPieces.get(startOfThisSizeIndex+doCounter-1);
			} else {
				checkSizeFiveE = false;
				checkSizeFourE = false;
				checkSizeThreeE = false;
				checkSizeTwoE = false;
				checkSizeOneE = true;
			}
		}
		System.out.println("returning null for " + this.getName());
		System.out.println("playerout: " + board.playerOut(this));
		return null;
	}
	
	public static void main (String[] args) {
		ComputerPlayer player = new ComputerPlayer("Computer Player 1", 1, 0);
		System.out.println(player.getPiecesLeft().get(20).getName() == "O1 Shape");
	}
}
