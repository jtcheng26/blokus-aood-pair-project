import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HowToPlay extends JPanel {
	private JFrame frame;
	HowToPlay(JFrame frame) {
		this.frame = frame;
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createEmptyBorder(20, 40, 40, 40));
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		HowToPlay instructions = new HowToPlay(frame);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setSize(800,800);
	}
}
