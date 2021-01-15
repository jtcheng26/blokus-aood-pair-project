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
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

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
		this.setPreferredSize(new Dimension(960, 960));
		this.setVisible(true);
		container.setVisible(true);
		setSize();
		Font titleFont = new Font("Helvetica", Font.BOLD, 20);
		JLabel title = new JLabel("Blokus");
		container.setOpaque(false);
		container.setBorder(null);
		this.setOpaque(false);
		title.setFont(titleFont);
		title.setBorder(new EmptyBorder(10, 0, 10, 0));
		title.setAlignmentX(CENTER_ALIGNMENT);
		container.add(title);
		JPanel selectPanel = new JPanel();
		selectPanel.setOpaque(false);
		selectPanel.setBorder(null);
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
		JButton startButton = new JButton("Play");
		startButton.setAlignmentX(CENTER_ALIGNMENT);
		startButton.setBorderPainted(true);
		startButton.setOpaque(true);
		//startButton.setPreferredSize(new Dimension(200, 50));
		startButton.setBackground(Color.white);
		startButton.setBorder(new CompoundBorder(new LineBorder(Color.black, 2, true), new EmptyBorder(10, 50, 10, 50)));
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
        og.setColor(GameboardScreen.colors[0]);
        og.fillRect(0, 0, getWidth() / 2, getHeight() / 2);
        og.setColor(GameboardScreen.colors[1]);
        og.fillRect(getWidth() / 2, 0, getWidth() / 2, getHeight() / 2);
        og.setColor(GameboardScreen.colors[2]);
        og.fillRect(0, getHeight() / 2, getWidth() / 2, getHeight() / 2);
        og.setColor(GameboardScreen.colors[3]);
        og.fillRect(getWidth() / 2, getHeight() / 2, getWidth() / 2, getHeight() / 2);
        //og.drawImage(game.board.getPicture(), 0, 0, null);
    }  
    private void clearAll() {
    	for (JPanel row : this.rows) {
    		this.container.remove(row);
    	}
    	this.rows.clear();
    	this.names.clear();
    	this.playerTypes.clear();
    	this.playerTypes.clear();
    }
    private void setGameScreen(GameScreen screen) {
    	this.removeAll();
    	this.setLayout(new BorderLayout());
    	this.add(screen);
    	this.setPreferredSize(new Dimension(1400, 960));
    	this.revalidate();
    	this.repaint();
    	this.frame.pack();
    }
	private void setSize() {
		Dimension d = new Dimension(350, 130 + this.rows.size() * 45);
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
		row.setOpaque(false);
		names.add(name);
		rows.add(row);
		playerTypes.add(playerType);
		container.add(row);
		container.setComponentZOrder(row, rows.size() + 1);
		setSize();
	}
}