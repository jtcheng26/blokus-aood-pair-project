import java.util.List;
import java.util.ArrayList;

public class ComputerPlayerTester {
	public static void main (String[] args) {
		Gameboard board1 = new Gameboard(2);
		ComputerPlayer cp1 = new ComputerPlayer("AI One", 1, 1);
		ComputerPlayer cp2 = new ComputerPlayer("AI Two", 2, 1);
		List<Player> playerList = new ArrayList<Player>();
		playerList.add(cp1);
		playerList.add(cp2);
		GameScreen screen1 = new GameScreen(playerList);
		
	}
}
