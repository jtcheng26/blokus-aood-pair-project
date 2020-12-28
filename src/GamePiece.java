import java.util.ArrayList;
import java.util.List;

public abstract class GamePiece {
	private String name;
	private Position loc = new Position(0, 0);
	GamePiece(String name) {
		this.name = name;
	}
	public List<Position> getPieceCoordinates() {
		List<Position> pieceDeltas = getPieceDeltas();
		List<Position> pieceCoordinates = new ArrayList<Position>();
		for (Position pos : pieceDeltas) {
			pieceCoordinates.add(new Position(this.loc.getX() + pos.getX(), this.loc.getY() + pos.getY()));
		}
		return pieceCoordinates;
	}
	public List<Position> getCornerCoordinates() {
		List<Position> cornerDeltas = getCornerDeltas();
		List<Position> cornerCoordinates = new ArrayList<Position>();
		for (Position pos : cornerDeltas) {
			cornerCoordinates.add(new Position(this.loc.getX() + pos.getX(), this.loc.getY() + pos.getY()));
		}
		return cornerCoordinates;
	}
	abstract protected List<Position> getPieceDeltas();
	abstract protected List<Position> getCornerDeltas();
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
		List<Position>  corners = getCornerDeltas();
		for (int i=0;i<coordinates.size();i++) {
			coordinates.set(i, new Position(coordinates.get(i).getY(), 4 - coordinates.get(i).getX()));
		}
		for (int i=0;i<corners.size();i++) {
			corners.set(i, new Position(corners.get(i).getY(), 4 - corners.get(i).getX()));
		}
	}
	public void flipPiece() {
		List<Position> coordinates = getPieceDeltas();
		List<Position>  corners = getCornerDeltas();
		for (int i=0;i<coordinates.size();i++) {
			coordinates.set(i, new Position(4 - coordinates.get(i).getX(), coordinates.get(i).getY()));
		}
		for (int i=0;i<corners.size();i++) {
			corners.set(i, new Position(4 - corners.get(i).getX(), corners.get(i).getY()));
		}
	}
	private void printPiece() {
		List<Position> coordinates = getPieceCoordinates();
		List<Position>  corners = getCornerCoordinates();
		char[][] grid = new char[7][7];
		for (Position p : coordinates) {
			grid[p.getY() + 1][p.getX() + 1] = 'O';
		}
		for (Position p : corners) {
			grid[p.getY() + 1][p.getX() + 1] = 'X';
		}
		for (int i=6;i>=0;i--) {
			for (int j=0;j<=6;j++) {
				if (grid[i][j] != 'O' && grid[i][j] != 'X')
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
	LPiece() {
		super("L Shape");
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
		cornerDeltas.add(new Position(0, 4));
		cornerDeltas.add(new Position(0, 4));
	}
	protected List<Position> getPieceDeltas() {
		return this.pieceDeltas;
	}
	protected List<Position> getCornerDeltas() {
		return this.cornerDeltas;
	}
}

class XPiece extends GamePiece {
	private List<Position> pieceDeltas;
	private List<Position> cornerDeltas;
	XPiece() {
		super("Cross Shape");
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
	}
	protected List<Position> getPieceDeltas() {
		return this.pieceDeltas;
	}
	protected List<Position> getCornerDeltas() {
		return this.cornerDeltas;
	}
}

class WPiece extends GamePiece {
	private List<Position> pieceDeltas;
	private List<Position> cornerDeltas;
	WPiece() {
		super("W Shape");
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
	}
	protected List<Position> getPieceDeltas() {
		return this.pieceDeltas;
	}
	protected List<Position> getCornerDeltas() {
		return this.cornerDeltas;
	}
}
