import java.util.List;
import java.util.ArrayList;

public class ComputerPlayerTester {
	public static void main (String[] args) {
		Gameboard board1 = new Gameboard(3);
		ComputerPlayer cp1 = new ComputerPlayer("AI One", 1, 1);
		ComputerPlayer cp2 = new ComputerPlayer("AI Two", 2, 2);
		ComputerPlayer cp3 = new ComputerPlayer("AI Three", 3, 3);
		ComputerPlayer cp4 = new ComputerPlayer("AI Four", 4, 2);
	}
}
