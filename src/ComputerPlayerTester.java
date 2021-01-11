import java.util.List;
import java.util.ArrayList;

public class ComputerPlayerTester {
	public static void main (String[] args) {
		Gameboard board1 = new Gameboard(2);
		ComputerPlayer cp1 = new ComputerPlayer("AI One", 1, 1);
		ComputerPlayer cp2 = new ComputerPlayer("AI Two", 2, 2);
		board1.placePiece(cp2.getPiece(cp2.getDifficultyLevel(), board1), cp2);
		board1.rotateBoard();
		board1.rotateBoard();
		board1.placePiece(cp1.getPiece(cp1.getDifficultyLevel(), board1), cp1);
		board1.placePiece(cp1.getPiece(cp1.getDifficultyLevel(), board1), cp1);
		board1.placePiece(cp1.getPiece(cp1.getDifficultyLevel(), board1), cp1);
		board1.placePiece(cp1.getPiece(cp1.getDifficultyLevel(), board1), cp1);
		board1.placePiece(cp1.getPiece(cp1.getDifficultyLevel(), board1), cp1);
		board1.placePiece(cp1.getPiece(cp1.getDifficultyLevel(), board1), cp1);
		board1.placePiece(cp1.getPiece(cp1.getDifficultyLevel(), board1), cp1);
		board1.placePiece(cp1.getPiece(cp1.getDifficultyLevel(), board1), cp1);
		board1.placePiece(cp1.getPiece(cp1.getDifficultyLevel(), board1), cp1);
		board1.placePiece(cp1.getPiece(cp1.getDifficultyLevel(), board1), cp1);
		board1.placePiece(cp1.getPiece(cp1.getDifficultyLevel(), board1), cp1);
		board1.placePiece(cp1.getPiece(cp1.getDifficultyLevel(), board1), cp1);
		board1.placePiece(cp1.getPiece(cp1.getDifficultyLevel(), board1), cp1);
		board1.placePiece(cp1.getPiece(cp1.getDifficultyLevel(), board1), cp1);
		board1.placePiece(cp1.getPiece(cp1.getDifficultyLevel(), board1), cp1);
		//board1.placePiece(cp1.getPiece(cp1.getDifficultyLevel(), board1), cp1);
		/*
		board1.placePiece(cp2.getPiece(cp2.getDifficultyLevel(), board1), cp2);
		board1.placePiece(cp2.getPiece(cp2.getDifficultyLevel(), board1), cp2);
		board1.placePiece(cp2.getPiece(cp2.getDifficultyLevel(), board1), cp2);
		board1.placePiece(cp2.getPiece(cp2.getDifficultyLevel(), board1), cp2);
		board1.placePiece(cp2.getPiece(cp2.getDifficultyLevel(), board1), cp2);
		board1.placePiece(cp2.getPiece(cp2.getDifficultyLevel(), board1), cp2);
		board1.placePiece(cp2.getPiece(cp2.getDifficultyLevel(), board1), cp2);
		board1.placePiece(cp2.getPiece(cp2.getDifficultyLevel(), board1), cp2);
		board1.placePiece(cp2.getPiece(cp2.getDifficultyLevel(), board1), cp2);
		board1.placePiece(cp2.getPiece(cp2.getDifficultyLevel(), board1), cp2);
		board1.placePiece(cp2.getPiece(cp2.getDifficultyLevel(), board1), cp2);
		board1.placePiece(cp2.getPiece(cp2.getDifficultyLevel(), board1), cp2);
		board1.placePiece(cp2.getPiece(cp2.getDifficultyLevel(), board1), cp2);
		board1.placePiece(cp2.getPiece(cp2.getDifficultyLevel(), board1), cp2);
		board1.placePiece(cp2.getPiece(cp2.getDifficultyLevel(), board1), cp2);
		board1.placePiece(cp2.getPiece(cp2.getDifficultyLevel(), board1), cp2);
		board1.placePiece(cp2.getPiece(cp2.getDifficultyLevel(), board1), cp2);
		*/
		board1.print(board1);

	}
}
