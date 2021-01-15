import java.util.ArrayList;

class GameTester {
	public static ArrayList<Player> getPlayers() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new ComputerPlayer("Player 1", 1, 1));
		players.add(new ComputerPlayer("Player 2", 2, 2));
		players.add(new ComputerPlayer("Player 3", 3, 3));
		players.add(new ComputerPlayer("Player 4", 4, 3));
		return players;
	}
	public static void main(String[] args) {
		final int GAMES = 1000;
		double[] score = new double[getPlayers().size()];
		ArrayList<Player> players = getPlayers();
		for (int i=0;i<GAMES;i++) {
			System.out.println("Game " + (i + 1) + "/" + GAMES);
			new GameScreen(players);
			for (int j=0;j<players.size();j++) {
				score[j] += players.get(j).getScore();
			}
			players = getPlayers();
		}
		System.out.println("Done. No exceptions thrown.");
		System.out.println("Score Averages: ");
		for (int i=0;i<score.length;i++) {
			System.out.println(players.get(i).getName() + ": " + (score[i] / GAMES));
		}
	}
}