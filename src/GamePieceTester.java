import java.util.List;

class GamePieceTester {
	public static void printPiece(GamePiece piece) {
		char[][] grid = new char[10][10];
		List<Position> pieceCoordinates = piece.getPieceCoordinates();
		List<Position> cornerCoordinates = piece.getCornerCoordinates();
		List<Position> adjacentCoordinates = piece.getAdjacentCoordinates();
		for (Position p : pieceCoordinates) {
			if (p.getX() >= -1 && p.getY() >= -1)
			grid[p.getY() + 1][p.getX() + 1] = 'O';
		}
		for (Position p : cornerCoordinates) {
			grid[p.getY() + 1][p.getX() + 1] = 'x';
		}
		for (Position p : adjacentCoordinates) {
			grid[p.getY() + 1][p.getX() + 1] = '*';
		}
		for (int i=6;i>=0;i--) {
			for (int j=0;j<=6;j++) {
				if (grid[i][j] != 'O' && grid[i][j] != 'x' && grid[i][j] != '*')
					grid[i][j] = '.';
				System.out.print(grid[i][j] + " ");
			}
			System.out.println("");
		}
		System.out.println("");
	}
	public static void testMovement(GamePiece piece) {
		System.out.println("Testing Movement of " + piece.getName());
		System.out.println("Default");
		printPiece(piece);
		System.out.println("Move up");
		piece.moveUp();
		printPiece(piece);
		System.out.println("Move right");
		piece.moveRight();
		printPiece(piece);
		System.out.println("Move down");
		piece.moveDown();
		printPiece(piece);
		System.out.println("Move Left");
		piece.moveLeft();
		printPiece(piece);
	}
	public static void testReflection(GamePiece piece) {
		System.out.println("Testing Reflection of " + piece.getName());
		System.out.println("Default");
		printPiece(piece);
		System.out.println("Reflected over Y-axis");
		piece.flipPiece();
		printPiece(piece);
		System.out.println("Back to default");
		piece.flipPiece();
		printPiece(piece);
	}
	public static void testRotation(GamePiece piece) {
		System.out.println("Testing Rotation of " + piece.getName());
		System.out.println("Default");
		printPiece(piece);
		System.out.println("90 degree rotation");
		piece.rotatePiece();
		printPiece(piece);
		System.out.println("180 degree rotation");
		piece.rotatePiece();
		printPiece(piece);
		System.out.println("270 degree rotation");
		piece.rotatePiece();
		printPiece(piece);
		System.out.println("Back to default");
		piece.rotatePiece();
		printPiece(piece);
	}
	public static void testPiece(GamePiece piece) {
		testMovement(piece);
		testReflection(piece);
		testRotation(piece);
	}
	public static void main(String[] args) {
		//testPiece(new LPiece());
		//testPiece(new XPiece());
		//testPiece(new WPiece());
		//testPiece(new IPiece());
		testPiece(new TPiece());
	}
}