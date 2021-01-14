import java.util.ArrayList;

class GameTester {
	public static ArrayList<Player> getPlayers() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new ComputerPlayer("Player 1", 1, 1));
		players.add(new ComputerPlayer("Player 2", 2, 1));
		players.add(new ComputerPlayer("Player 3", 3, 1));
		players.add(new ComputerPlayer("Player 4", 4, 1));
		return players;
	}
	public static void main(String[] args) {
		final int GAMES = 10000;
		for (int i=0;i<GAMES;i++) {
			System.out.println("Game " + (i + 1) + "/" + GAMES);
			new GameScreen(getPlayers());
		}
		System.out.println("Done. No exceptions thrown.");
	}
}