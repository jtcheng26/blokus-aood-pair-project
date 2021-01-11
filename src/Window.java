import java.util.ArrayList;

import javax.swing.*;

class Window {
	private JFrame frame;

	Window() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		frame = new JFrame("Blokus");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Player 1", 1, 0));
		players.add(new Player("Player 2", 2, 0));
		frame.setContentPane(new GameScreen(players));
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Window();
			}
		});
	}
}