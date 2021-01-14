import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ComputerPlayer extends Player {
	private int difficultyLevel;
	private List<Position> cornerPositions;
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
			int rotationCounter = 0;
			int doCounter = 0;
			int startOfThisSizeIndex = fiveSquarePieces + fourSquarePieces + threeSquarePieces + twoSquarePieces;
			Collections.shuffle(easyAIPieces.subList(startOfThisSizeIndex, startOfThisSizeIndex+oneSquarePieces));
			cornerPositions = this.getCornerPositions();
			Collections.shuffle(cornerPositions);
			do {
				choosePositionForPiece (easyAIPieces, board, startOfThisSizeIndex, doCounter, rotationCounter);
				doCounter++;
			} while (doCounter < oneSquarePieces && !board.isValid(easyAIPieces.get(startOfThisSizeIndex+doCounter-1), this));
			if (board.isValid(easyAIPieces.get(startOfThisSizeIndex+doCounter-1), this)) {
				checkSizeOneE = false;
				checkSizeThreeE = false;
				checkSizeFiveE = false;
				checkSizeFourE = false;
				checkSizeTwoE = false;
				oneSquarePieces--;
				return easyAIPieces.get(startOfThisSizeIndex+doCounter-1);
			} else {
				checkSizeThreeE = false;
				checkSizeFiveE = false;
				checkSizeFourE = false;
				checkSizeTwoE = false;
				checkSizeOneE = true;
			}
		}
		if (twoSquarePieces > 0 && !checkSizeTwoE) {
			int rotationCounter = 0;
			int doCounter = 0;
			int startOfThisSizeIndex = fiveSquarePieces + fourSquarePieces + threeSquarePieces;
			Collections.shuffle(easyAIPieces.subList(startOfThisSizeIndex, startOfThisSizeIndex+twoSquarePieces));
			cornerPositions = this.getCornerPositions();
			Collections.shuffle(cornerPositions);
			do {
				choosePositionForPiece (easyAIPieces, board, startOfThisSizeIndex, doCounter, rotationCounter);
				doCounter++;
			} while (doCounter < twoSquarePieces && !board.isValid(easyAIPieces.get(startOfThisSizeIndex+doCounter-1), this));
			if (board.isValid(easyAIPieces.get(startOfThisSizeIndex+doCounter-1), this)) {
				checkSizeOneE = false;
				checkSizeThreeE = false;
				checkSizeFiveE = false;
				checkSizeFourE = false;
				checkSizeTwoE = false;
				twoSquarePieces--;
				return easyAIPieces.get(startOfThisSizeIndex+doCounter-1);
			} else {
				checkSizeThreeE = false;
				checkSizeFiveE = false;
				checkSizeFourE = false;
				checkSizeTwoE = true;
				checkSizeOneE = false;
			}
		}
		if (threeSquarePieces > 0 && !checkSizeThreeE) {
			int doCounter = 0;
			int rotationCounter = 0;
			int startOfThisSizeIndex = fourSquarePieces+fiveSquarePieces;
			Collections.shuffle(easyAIPieces.subList(startOfThisSizeIndex, startOfThisSizeIndex+threeSquarePieces));
			cornerPositions = this.getCornerPositions();
			Collections.shuffle(cornerPositions);
			do {
				choosePositionForPiece (easyAIPieces, board, startOfThisSizeIndex, doCounter, rotationCounter);
				doCounter++;
			} while (doCounter < threeSquarePieces && !board.isValid(easyAIPieces.get(startOfThisSizeIndex+doCounter-1), this));
			if (board.isValid(easyAIPieces.get(startOfThisSizeIndex+doCounter-1), this)) {
				checkSizeOneE = false;
				checkSizeThreeE = false;
				checkSizeFiveE = false;
				checkSizeFourE = false;
				checkSizeTwoE = false;
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
			int rotationCounter = 0;
			int startOfThisSizeIndex = fiveSquarePieces;
			Collections.shuffle(easyAIPieces.subList(startOfThisSizeIndex, startOfThisSizeIndex+fourSquarePieces));
			cornerPositions = this.getCornerPositions();
			Collections.shuffle(cornerPositions);
			do {
				choosePositionForPiece (easyAIPieces, board, startOfThisSizeIndex, doCounter, rotationCounter);
				doCounter++;
			} while (doCounter < fourSquarePieces && !board.isValid(easyAIPieces.get(startOfThisSizeIndex+doCounter-1), this));
			if (board.isValid(easyAIPieces.get(startOfThisSizeIndex+doCounter-1), this)) {
				checkSizeOneE = false;
				checkSizeThreeE = false;
				checkSizeFiveE = false;
				checkSizeFourE = false;
				checkSizeTwoE = false;
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
			int rotationCounter = 0;
			int startOfThisSizeIndex = 0;
			Collections.shuffle(easyAIPieces.subList(startOfThisSizeIndex, startOfThisSizeIndex+fiveSquarePieces));
			cornerPositions = this.getCornerPositions();
			Collections.shuffle(cornerPositions);
			do {
				choosePositionForPiece (easyAIPieces, board, startOfThisSizeIndex, doCounter, rotationCounter);
				doCounter++;
			} while (doCounter < fiveSquarePieces && !board.isValid(easyAIPieces.get(startOfThisSizeIndex+doCounter-1), this));
			if (board.isValid(easyAIPieces.get(startOfThisSizeIndex+doCounter-1), this)) {
				checkSizeOneE = false;
				checkSizeThreeE = false;
				checkSizeFiveE = false;
				checkSizeFourE = false;
				checkSizeTwoE = false;
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
		int doCounter = 0;
		int rotationCounter = 0;
		//randomize order of pieces every turn and randomize order of corners
		Collections.shuffle(mediumAIPieces);
		cornerPositions = this.getCornerPositions();
		Collections.shuffle(cornerPositions);
		do {
			//randomize rotation
			int initialRotationCounter = (int) (Math.random()*4);
			for (int i=0; i < initialRotationCounter; i++) {
				mediumAIPieces.get(doCounter).rotatePiece();
			}
			for (int i=0; i < mediumAIPieces.get(doCounter).getPieceCoordinates().size(); i++) {
				for (int j=0; j < this.cornerPositions.size(); j++) {
					mediumAIPieces.get(doCounter).setPieceCoordinateLocation(i, this.cornerPositions.get(j));
					if (board.isValid(mediumAIPieces.get(doCounter), this)) {
						i = mediumAIPieces.get(doCounter).getPieceCoordinates().size();
						j = this.cornerPositions.size();
					}
					else if (i == mediumAIPieces.get(doCounter).getPieceCoordinates().size()-1
						&& j == this.cornerPositions.size()-1 && !board.isValid(mediumAIPieces.get(doCounter), this)) {
						mediumAIPieces.get(doCounter).rotatePiece();
						rotationCounter++;
						i=0;
						j=-1;
						if (rotationCounter%4 == 0) {
							mediumAIPieces.get(doCounter).flipPiece();
						}
					}
					if (rotationCounter >= 8) {
						rotationCounter = 0;
						i = mediumAIPieces.get(doCounter).getPieceCoordinates().size();
						j = this.cornerPositions.size();
					}
					//System.out.println("i: " + i + ", j: " + j);
				}
			}
			doCounter++;
		} while (doCounter < mediumAIPieces.size() && !board.isValid(mediumAIPieces.get(doCounter-1), this));
		if (board.isValid(mediumAIPieces.get(doCounter-1), this)) {
			return mediumAIPieces.get(doCounter-1);
		}
		return null;
	}
	
	public GamePiece hardAI (Gameboard board) {
		List<GamePiece> hardAIPieces = this.getPiecesLeft();
		if (fiveSquarePieces > 0 && !checkSizeFive) {
			int doCounter = 0;
			int rotationCounter = 0;
			int startOfThisSizeIndex = 0;
			Collections.shuffle(hardAIPieces.subList(startOfThisSizeIndex, startOfThisSizeIndex+fiveSquarePieces));
			cornerPositions = this.getCornerPositions();
			Collections.shuffle(cornerPositions);
			do {
				choosePositionForPiece(hardAIPieces, board, startOfThisSizeIndex, doCounter, rotationCounter);
				doCounter++;
			} while (doCounter < fiveSquarePieces && !board.isValid(hardAIPieces.get(startOfThisSizeIndex+doCounter-1), this));
			if (board.isValid(hardAIPieces.get(startOfThisSizeIndex+doCounter-1), this)) {
				checkSizeOneE = false;
				checkSizeThreeE = false;
				checkSizeFiveE = false;
				checkSizeFourE = false;
				checkSizeTwoE = false;
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
			int doCounter = 0;
			int rotationCounter = 0;
			int startOfThisSizeIndex = fiveSquarePieces;
			Collections.shuffle(hardAIPieces.subList(startOfThisSizeIndex, startOfThisSizeIndex+fourSquarePieces));
			cornerPositions = this.getCornerPositions();
			Collections.shuffle(cornerPositions);
			do {
				choosePositionForPiece(hardAIPieces, board, startOfThisSizeIndex, doCounter, rotationCounter);
				doCounter++;
			} while (doCounter < fourSquarePieces && !board.isValid(hardAIPieces.get(startOfThisSizeIndex+doCounter-1), this));
			if (board.isValid(hardAIPieces.get(startOfThisSizeIndex+doCounter-1), this)) {
				checkSizeOneE = false;
				checkSizeThreeE = false;
				checkSizeFiveE = false;
				checkSizeFourE = false;
				checkSizeTwoE = false;
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
			int rotationCounter = 0;
			int startOfThisSizeIndex = fiveSquarePieces+fourSquarePieces;
			Collections.shuffle(hardAIPieces.subList(startOfThisSizeIndex, startOfThisSizeIndex+threeSquarePieces));
			cornerPositions = this.getCornerPositions();
			Collections.shuffle(cornerPositions);
			do {
				choosePositionForPiece(hardAIPieces, board, startOfThisSizeIndex, doCounter, rotationCounter);
				doCounter++;
			} while (doCounter < threeSquarePieces && !board.isValid(hardAIPieces.get(startOfThisSizeIndex+doCounter-1), this));
			if (board.isValid(hardAIPieces.get(startOfThisSizeIndex+doCounter-1), this)) {
				checkSizeOneE = false;
				checkSizeThreeE = false;
				checkSizeFiveE = false;
				checkSizeFourE = false;
				checkSizeTwoE = false;
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
			int rotationCounter = 0;
			int startOfThisSizeIndex = fiveSquarePieces+fourSquarePieces+threeSquarePieces;
			Collections.shuffle(hardAIPieces.subList(startOfThisSizeIndex, startOfThisSizeIndex+twoSquarePieces));
			cornerPositions = this.getCornerPositions();
			Collections.shuffle(cornerPositions);
			do {
				choosePositionForPiece(hardAIPieces, board, startOfThisSizeIndex, doCounter, rotationCounter);
				doCounter++;
			} while (doCounter < twoSquarePieces && !board.isValid(hardAIPieces.get(startOfThisSizeIndex+doCounter-1), this));
			if (board.isValid(hardAIPieces.get(startOfThisSizeIndex+doCounter-1), this)) {
				checkSizeOneE = false;
				checkSizeThreeE = false;
				checkSizeFiveE = false;
				checkSizeFourE = false;
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
			int rotationCounter = 0;
			int startOfThisSizeIndex = fiveSquarePieces+fourSquarePieces+threeSquarePieces+twoSquarePieces;
			Collections.shuffle(hardAIPieces.subList(startOfThisSizeIndex, startOfThisSizeIndex+oneSquarePieces));
			cornerPositions = this.getCornerPositions();
			Collections.shuffle(cornerPositions);
			do {
				choosePositionForPiece(hardAIPieces, board, startOfThisSizeIndex, doCounter, rotationCounter);
				doCounter++;
			} while (doCounter < oneSquarePieces && !board.isValid(hardAIPieces.get(startOfThisSizeIndex+doCounter-1), this));
			if (board.isValid(hardAIPieces.get(startOfThisSizeIndex+doCounter-1), this)) {
				checkSizeOneE = false;
				checkSizeThreeE = false;
				checkSizeFiveE = false;
				checkSizeFourE = false;
				checkSizeTwoE = false;
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
	
	//sets each position on the selected piece to each available corner position
	private void choosePositionForPiece (List<GamePiece> pieceList, Gameboard gameBoard, int listStartIndex, int doLoopCounter, int numberOfRotations) {
		int initialRotationCounter = (int) (Math.random()*4);
		for (int i=0; i < initialRotationCounter; i++) {
			pieceList.get(listStartIndex+doLoopCounter).rotatePiece();
		}
		for (int i=0; i < pieceList.get(listStartIndex+doLoopCounter).getPieceCoordinates().size(); i++) {
			for (int j=0; j < this.cornerPositions.size(); j++) {
				pieceList.get(listStartIndex+doLoopCounter).setPieceCoordinateLocation(i, this.cornerPositions.get(j));
				//valid position
				if (gameBoard.isValid(pieceList.get(listStartIndex+doLoopCounter), this)) {
					i = pieceList.get(listStartIndex+doLoopCounter).getPieceCoordinates().size();
					j = this.cornerPositions.size();
				} 
				//invalid position, so rotation needed
				else if (i == pieceList.get(listStartIndex+doLoopCounter).getPieceCoordinates().size()-1
						&& j == this.cornerPositions.size()-1 && !gameBoard.isValid(pieceList.get(listStartIndex+doLoopCounter), this)) {
					pieceList.get(listStartIndex+doLoopCounter).rotatePiece();
					numberOfRotations++;
					i=0;
					j=-1;
					if (numberOfRotations%4 == 0) {
						pieceList.get(listStartIndex+doLoopCounter).flipPiece();
					}
				}
				if (numberOfRotations >= 8) {
					numberOfRotations = 0;
					i = pieceList.get(listStartIndex+doLoopCounter).getPieceCoordinates().size();
					j = this.cornerPositions.size();
				}
			}
		}
	}
	
	public static void main (String[] args) {
		ComputerPlayer player = new ComputerPlayer("Computer Player 1", 1, 0);
		System.out.println(player.getPiecesLeft().get(20).getName() == "O1 Shape");
	}
}
