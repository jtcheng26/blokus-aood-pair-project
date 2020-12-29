import java.util.ArrayList;
import java.util.List;

public abstract class GamePiece {
	private String name;
	private Position loc;
	GamePiece(String name) {
		this.name = name;
	}
	GamePiece(String name, Position loc) {
		this.name = name;
		this.loc = loc;
	}
	public List<Position> getPieceCoordinates() {
		List<Position> pieceDeltas = getPieceDeltas();
		List<Position> pieceCoordinates = new ArrayList<Position>();
		for (Position pos : pieceDeltas) {
			int xLoc = this.loc.getX() + pos.getX();
			int yLoc = this.loc.getY() + pos.getY();
			pieceCoordinates.add(new Position(xLoc, yLoc));
		}
		return pieceCoordinates;
	}
	public List<Position> getCornerCoordinates() {
		List<Position> cornerDeltas = getCornerDeltas();
		List<Position> cornerCoordinates = new ArrayList<Position>();
		for (Position pos : cornerDeltas) {
			int xLoc = this.loc.getX() + pos.getX();
			int yLoc = this.loc.getY() + pos.getY();
			if (0 <= xLoc && 0 <= yLoc)
				cornerCoordinates.add(new Position(xLoc, yLoc));
		}
		return cornerCoordinates;
	}
	public List<Position> getAdjacentCoordinates() {
		List<Position> adjacentDeltas = getAdjacentDeltas();
		List<Position> adjacentCoordinates = new ArrayList<Position>();
		for (Position pos : adjacentDeltas) {
			int xLoc = this.loc.getX() + pos.getX();
			int yLoc = this.loc.getY() + pos.getY();
			if (0 <= xLoc && 0 <= yLoc)
				adjacentCoordinates.add(new Position(xLoc, yLoc));
		}
		return adjacentCoordinates;
	}
	abstract protected List<Position> getPieceDeltas();
	abstract protected List<Position> getCornerDeltas();
	abstract protected List<Position> getAdjacentDeltas();
	public String getName() {
		return this.name;
	}
	public void moveUp() {
		this.loc = new Position(loc.getX() + 1, loc.getY());
	}
	public void moveDown() {
		this.loc = new Position(loc.getX() - 1, loc.getY());
	}
	public void moveLeft() {
		this.loc = new Position(loc.getX(), loc.getY() - 1);
	}
	public void moveRight() {
		this.loc = new Position(loc.getX(), loc.getY() + 1);
	}
	public void rotatePiece() {
		List<Position> coordinates = getPieceDeltas();
		List<Position> corners = getCornerDeltas();
		List<Position> adj = getAdjacentDeltas();
		for (int i=0;i<coordinates.size();i++) {
			coordinates.set(i, new Position(coordinates.get(i).getY(), 4 - coordinates.get(i).getX()));
		}
		for (int i=0;i<corners.size();i++) {
			corners.set(i, new Position(corners.get(i).getY(), 4 - corners.get(i).getX()));
		}
		for (int i=0;i<adj.size();i++) {
			adj.set(i, new Position(adj.get(i).getY(), 4 - adj.get(i).getX()));
		}
	}
	public void flipPiece() {
		List<Position> coordinates = getPieceDeltas();
		List<Position>  corners = getCornerDeltas();
		List<Position> adj = getAdjacentDeltas();
		for (int i=0;i<coordinates.size();i++) {
			coordinates.set(i, new Position(4 - coordinates.get(i).getX(), coordinates.get(i).getY()));
		}
		for (int i=0;i<corners.size();i++) {
			corners.set(i, new Position(4 - corners.get(i).getX(), corners.get(i).getY()));
		}
		for (int i=0;i<adj.size();i++) {
			adj.set(i, new Position(4 - adj.get(i).getX(), adj.get(i).getY()));
		}
	}
	private void printPiece() {
		List<Position> coordinates = getPieceCoordinates();
		List<Position>  corners = getCornerCoordinates();
		List<Position> adj = getAdjacentCoordinates();
		char[][] grid = new char[7][7];
		for (Position p : coordinates) {
			grid[p.getY() + 1][p.getX() + 1] = 'O';
		}
		for (Position p : corners) {
			grid[p.getY() + 1][p.getX() + 1] = 'X';
		}
		for (Position p : adj) {
			grid[p.getY() + 1][p.getX() + 1] = 'a';
		}
		for (int i=6;i>=0;i--) {
			for (int j=0;j<=6;j++) {
				if (grid[i][j] != 'O' && grid[i][j] != 'X' && grid[i][j] != 'a')
					grid[i][j] = '.';
				System.out.print(grid[i][j] + " ");
			}
			System.out.println("");
		}
		System.out.println("");
	}
	public static void testPiece(GamePiece piece) {
		System.out.println("Testing " + piece.getName());
		System.out.println("Default");
		piece.printPiece();
		piece.flipPiece();
		System.out.println("Reflected over Y-axis");
		piece.printPiece();
		piece.flipPiece();
		System.out.println("Back to default");
		piece.printPiece();
		piece.rotatePiece();
		System.out.println("90 degree rotation");
		piece.printPiece();
		piece.rotatePiece();
		System.out.println("180 degree rotation");
		piece.printPiece();
		piece.rotatePiece();
		System.out.println("270 degree rotation");
		piece.printPiece();
		piece.rotatePiece();
		System.out.println("Back to default");
		piece.printPiece();
	}
	public static void main(String[] args) {
		testPiece(new WPiece());
	}
}

class LPiece extends GamePiece {
	private List<Position> pieceDeltas;
	private List<Position> cornerDeltas;
	private List<Position> adjacentDeltas;
	LPiece() {
		super("L Shape", new Position(-1, -1));
		pieceDeltas = new ArrayList<Position>();
		pieceDeltas.add(new Position(1, 1));
		pieceDeltas.add(new Position(2, 1));
		pieceDeltas.add(new Position(3, 1));
		pieceDeltas.add(new Position(1, 2));
		pieceDeltas.add(new Position(1, 3));
		
		cornerDeltas = new ArrayList<Position>();
		cornerDeltas.add(new Position(0, 0));
		cornerDeltas.add(new Position(4, 0));
		cornerDeltas.add(new Position(4, 2));
		cornerDeltas.add(new Position(2, 4));
		cornerDeltas.add(new Position(0, 4));
		cornerDeltas.add(new Position(0, 4));
		
		adjacentDeltas = new ArrayList<Position>();
		adjacentDeltas.add(new Position(1, 0));
		adjacentDeltas.add(new Position(2, 0));
		adjacentDeltas.add(new Position(3, 0));
		adjacentDeltas.add(new Position(4, 1));
		adjacentDeltas.add(new Position(3, 2));
		adjacentDeltas.add(new Position(2, 2));
		adjacentDeltas.add(new Position(2, 3));
		adjacentDeltas.add(new Position(0, 1));
		adjacentDeltas.add(new Position(0, 2));
		adjacentDeltas.add(new Position(0, 3));
		adjacentDeltas.add(new Position(1, 4));
	}
	protected List<Position> getPieceDeltas() {
		return this.pieceDeltas;
	}
	protected List<Position> getCornerDeltas() {
		return this.cornerDeltas;
	}
	protected List<Position> getAdjacentDeltas() {
		return this.adjacentDeltas;
	}
}

class XPiece extends GamePiece {
	private List<Position> pieceDeltas;
	private List<Position> cornerDeltas;
	private List<Position> adjacentDeltas;
	XPiece() {
		super("Cross Shape", new Position(-1, -1));
		pieceDeltas = new ArrayList<Position>();
		pieceDeltas.add(new Position(2, 2));
		pieceDeltas.add(new Position(2, 1));
		pieceDeltas.add(new Position(2, 3));
		pieceDeltas.add(new Position(1, 2));
		pieceDeltas.add(new Position(3, 2));
		
		cornerDeltas = new ArrayList<Position>();
		cornerDeltas.add(new Position(1, 4));
		cornerDeltas.add(new Position(3, 4));
		cornerDeltas.add(new Position(1, 0));
		cornerDeltas.add(new Position(3, 0));
		cornerDeltas.add(new Position(0, 1));
		cornerDeltas.add(new Position(0, 3));
		cornerDeltas.add(new Position(4, 1));
		cornerDeltas.add(new Position(4, 3));
		
		adjacentDeltas = new ArrayList<Position>();
		adjacentDeltas.add(new Position(2, 0));
		adjacentDeltas.add(new Position(2, 4));
		adjacentDeltas.add(new Position(1, 3));
		adjacentDeltas.add(new Position(1, 1));
		adjacentDeltas.add(new Position(3, 3));
		adjacentDeltas.add(new Position(3, 1));
		adjacentDeltas.add(new Position(4, 2));
		adjacentDeltas.add(new Position(0, 2));
	}
	protected List<Position> getPieceDeltas() {
		return this.pieceDeltas;
	}
	protected List<Position> getCornerDeltas() {
		return this.cornerDeltas;
	}
	protected List<Position> getAdjacentDeltas() {
		return this.adjacentDeltas;
	}
}

class WPiece extends GamePiece {
	private List<Position> pieceDeltas;
	private List<Position> cornerDeltas;
	private List<Position> adjacentDeltas;
	WPiece() {
		super("W Shape", new Position(-1, -1));
		pieceDeltas = new ArrayList<Position>();
		pieceDeltas.add(new Position(2, 2));
		pieceDeltas.add(new Position(2, 3));
		pieceDeltas.add(new Position(1, 2));
		pieceDeltas.add(new Position(1, 1));
		pieceDeltas.add(new Position(3, 3));
		
		cornerDeltas = new ArrayList<Position>();
		cornerDeltas.add(new Position(3, 1));
		cornerDeltas.add(new Position(0, 3));
		cornerDeltas.add(new Position(0, 0));
		cornerDeltas.add(new Position(2, 0));
		cornerDeltas.add(new Position(1, 4));
		cornerDeltas.add(new Position(4, 4));
		cornerDeltas.add(new Position(4, 2));
		
		adjacentDeltas = new ArrayList<Position>();
		adjacentDeltas.add(new Position(0, 1));
		adjacentDeltas.add(new Position(1, 0));
		adjacentDeltas.add(new Position(0, 2));
		adjacentDeltas.add(new Position(2, 1));
		adjacentDeltas.add(new Position(1, 3));
		adjacentDeltas.add(new Position(2, 4));
		adjacentDeltas.add(new Position(3, 4));
		adjacentDeltas.add(new Position(4, 3));
		adjacentDeltas.add(new Position(3, 2));
	}
	protected List<Position> getPieceDeltas() {
		return this.pieceDeltas;
	}
	protected List<Position> getCornerDeltas() {
		return this.cornerDeltas;
	}
	protected List<Position> getAdjacentDeltas() {
		return this.adjacentDeltas;
	}
}
