import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class HomeScreen extends JPanel {
	private JPanel container;
	private List<JPanel> rows = new ArrayList<JPanel>();
	private List<JTextField> names = new ArrayList<JTextField>();
	private List<JComboBox<String> > playerTypes = new ArrayList<JComboBox<String> >();
	private GameScreen game;
	private JFrame frame;
	private JComboBox<String> playerCounts;
	private String[] countList = {"2", "3", "4"};
	private String[] playerTypeList = {"Human", "Easy AI", "Medium AI", "Hard AI"};
	HomeScreen(JFrame frame) {
		this.frame = frame;
		init();
	}
	private void init() {
		this.container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		this.setLayout(new GridBagLayout());
		this.add(container);
		this.setVisible(true);
		container.setVisible(true);
		setSize();
		Border b = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
		Font titleFont = new Font("Helvetica", Font.BOLD, 20);
		JLabel title = new JLabel("Players");
		title.setFont(titleFont);
		title.setBorder(new EmptyBorder(10, 0, 10, 0));
		title.setAlignmentX(CENTER_ALIGNMENT);
		container.add(title);
		JPanel selectPanel = new JPanel();
		container.add(selectPanel);
		playerCounts = new JComboBox<String>(countList);
		playerCounts.setSelectedIndex(0);
		playerCounts.setPreferredSize(new Dimension(200, 25));
		selectPanel.add(playerCounts);
		for (int i=0;i<2;i++) {
			addRow();
		}
		playerCounts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int count = playerCounts.getSelectedIndex() + 2;
				clearAll();
				for (int i=0;i<count;i++) {
					addRow();
				}
				setMenu();
			}
		});
		setMenu();
		container.setBorder(b);
		JButton startButton = new JButton("Start");
		startButton.setAlignmentX(CENTER_ALIGNMENT);
		startButton.setBorderPainted(false);
		startButton.setOpaque(true);
		startButton.setBackground(Color.GREEN);
		startButton.setBorder(new EmptyBorder(10, 20, 10, 20));
		container.add(startButton);
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Player> players = new ArrayList<Player>();
				for (int i=0;i<rows.size();i++) {
					String name = names.get(i).getText();
					int playerType = playerTypes.get(i).getSelectedIndex();
					players.add(new Player(name, i+1, playerType));
				}
				setGameScreen(new GameScreen(frame, players));
			}
		});
	}
    protected void paintComponent(Graphics og) {
        super.paintComponent(og);
        //og.drawImage(game.board.getPicture(), 0, 0, null);
    }  
    private void clearAll() {
    	for (JPanel row : this.rows) {
    		this.container.remove(row);
    	}
    	this.rows.clear();
    	this.playerTypes.clear();
    }
    private void setGameScreen(GameScreen screen) {
    	this.removeAll();
    	this.setLayout(new BorderLayout());
    	this.add(screen);
    	this.revalidate();
    	this.repaint();
    	this.frame.pack();
    }
	private void setSize() {
		Dimension d = new Dimension(350, 120 + this.rows.size() * 45);
		container.setPreferredSize(d);
	}
	private void setMenu() {
		this.revalidate();
		this.repaint();
	}
	private void addRow() {
		JPanel row = new JPanel();
		JComboBox<String> playerType = new JComboBox<String>(playerTypeList);
		JTextField name = new JTextField("Player " + (this.rows.size() + 1));
		row.add(playerType);
		row.add(name);
		names.add(name);
		rows.add(row);
		playerTypes.add(playerType);
		container.add(row);
		container.setComponentZOrder(row, rows.size() + 1);
		setSize();
	}
}