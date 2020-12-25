import java.util.ArrayList;
import java.util.List;

public abstract class GamePiece {
	private String name;
	private Position loc;
	GamePiece(String name) {
		this.name = name;
	}
	abstract public List<Position> getPieceCoordinates();
	abstract public List<Position> getCornerCoordinates();
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
		List<Position> coordinates = getPieceCoordinates();
		List<Position>  corners = getCornerCoordinates();
		for (int i=0;i<coordinates.size();i++) {
			coordinates.set(i, new Position(coordinates.get(i).getY(), 4 - coordinates.get(i).getX()));
		}
		for (int i=0;i<corners.size();i++) {
			corners.set(i, new Position(corners.get(i).getY(), 4 - corners.get(i).getX()));
		}
	}
	public void flipPiece() {
		List<Position> coordinates = getPieceCoordinates();
		List<Position>  corners = getCornerCoordinates();
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
	private List<Position> pieceCoordinates;
	private List<Position> cornerCoordinates;
	LPiece() {
		super("L Shape");
		pieceCoordinates = new ArrayList<Position>();
		pieceCoordinates.add(new Position(1, 1));
		pieceCoordinates.add(new Position(2, 1));
		pieceCoordinates.add(new Position(3, 1));
		pieceCoordinates.add(new Position(1, 2));
		pieceCoordinates.add(new Position(1, 3));
		
		cornerCoordinates = new ArrayList<Position>();
		cornerCoordinates.add(new Position(0, 0));
		cornerCoordinates.add(new Position(4, 0));
		cornerCoordinates.add(new Position(4, 2));
		cornerCoordinates.add(new Position(0, 4));
		cornerCoordinates.add(new Position(0, 4));
	}
	public List<Position> getPieceCoordinates() {
		return this.pieceCoordinates;
	}
	public List<Position> getCornerCoordinates() {
		return this.cornerCoordinates;
	}
}

class XPiece extends GamePiece {
	private List<Position> pieceCoordinates;
	private List<Position> cornerCoordinates;
	XPiece() {
		super("Cross Shape");
		pieceCoordinates = new ArrayList<Position>();
		pieceCoordinates.add(new Position(2, 2));
		pieceCoordinates.add(new Position(2, 1));
		pieceCoordinates.add(new Position(2, 3));
		pieceCoordinates.add(new Position(1, 2));
		pieceCoordinates.add(new Position(3, 2));
		
		cornerCoordinates = new ArrayList<Position>();
		cornerCoordinates.add(new Position(1, 4));
		cornerCoordinates.add(new Position(3, 4));
		cornerCoordinates.add(new Position(1, 0));
		cornerCoordinates.add(new Position(3, 0));
		cornerCoordinates.add(new Position(0, 1));
		cornerCoordinates.add(new Position(0, 3));
		cornerCoordinates.add(new Position(4, 1));
		cornerCoordinates.add(new Position(4, 3));
	}
	public List<Position> getPieceCoordinates() {
		return this.pieceCoordinates;
	}
	public List<Position> getCornerCoordinates() {
		return this.cornerCoordinates;
	}
}

class WPiece extends GamePiece {
	private List<Position> pieceCoordinates;
	private List<Position> cornerCoordinates;
	WPiece() {
		super("W Shape");
		pieceCoordinates = new ArrayList<Position>();
		pieceCoordinates.add(new Position(2, 2));
		pieceCoordinates.add(new Position(2, 3));
		pieceCoordinates.add(new Position(1, 2));
		pieceCoordinates.add(new Position(1, 1));
		pieceCoordinates.add(new Position(3, 3));
		
		cornerCoordinates = new ArrayList<Position>();
		cornerCoordinates.add(new Position(3, 1));
		cornerCoordinates.add(new Position(0, 3));
		cornerCoordinates.add(new Position(0, 0));
		cornerCoordinates.add(new Position(2, 0));
		cornerCoordinates.add(new Position(1, 4));
		cornerCoordinates.add(new Position(4, 4));
		cornerCoordinates.add(new Position(4, 2));
	}
	public List<Position> getPieceCoordinates() {
		return this.pieceCoordinates;
	}
	public List<Position> getCornerCoordinates() {
		return this.cornerCoordinates;
	}
}
