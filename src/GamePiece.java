import java.util.ArrayList;
import java.util.List;

public class GamePiece {
	private String name;
	private Position loc;
	private List<Position> pieceDeltas;
	private List<Position> cornerDeltas;
	private List<Position> adjacentDeltas;
	GamePiece(String name, Position loc, List<Position> pieceDeltas) {
		this.name = name;
		this.loc = loc;
		this.pieceDeltas = pieceDeltas;
		this.build();
	}
	GamePiece(String name, Position loc, List<Position> pieceDeltas, List<Position> cornerDeltas, List<Position> adjacentDeltas) {
		this.name = name;
		this.loc = loc;
		this.pieceDeltas = pieceDeltas;
		this.cornerDeltas = cornerDeltas;
		this.adjacentDeltas = adjacentDeltas;
	}
	private void build() {
		this.cornerDeltas = new ArrayList<Position>();
		this.adjacentDeltas = new ArrayList<Position>();
		int[][] grid = new int[7][7];
		for (Position pos : pieceDeltas)
			grid[pos.getX()+1][pos.getY()+1] = 1;
		for (int x=0;x<7;x++) {
			for (int y=-0;y<7;y++) {
				if (grid[x][y] == 0) {
					if (x > 0 && grid[x-1][y] == 1)
						grid[x][y] = 2;
					else if (x < 6 && grid[x+1][y] == 1)
						grid[x][y] = 2;
					else if (y > 0 && grid[x][y-1] == 1)
						grid[x][y] = 2;
					else if (y < 6 && grid[x][y+1] == 1)
						grid[x][y] = 2;
					else {
						if (x > 0 && y > 0 && grid[x-1][y-1] == 1)
							grid[x][y] = 3;
						else if (x > 0 && y < 6 && grid[x-1][y+1] == 1)
							grid[x][y] = 3;
						else if (x < 6 && y > 0 && grid[x+1][y-1] == 1)
							grid[x][y] = 3;
						else if (x < 6 && y < 6 && grid[x+1][y+1] == 1)
							grid[x][y] = 3;
					}
					if (grid[x][y] == 2)
						adjacentDeltas.add(new Position(x - 1, y - 1));		
					else if (grid[x][y] == 3)
						cornerDeltas.add(new Position(x - 1, y - 1));
				}
			}
		}
	}
	public List<Position> getPieceCoordinates() {
		List<Position> pieceCoordinates = new ArrayList<Position>();
		for (Position pos : pieceDeltas) {
			int xLoc = this.loc.getX() + pos.getX();
			int yLoc = this.loc.getY() + pos.getY();
			pieceCoordinates.add(new Position(xLoc, yLoc));
		}
		return pieceCoordinates;
	}
	public List<Position> getCornerCoordinates() {
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
		List<Position> adjacentCoordinates = new ArrayList<Position>();
		for (Position pos : adjacentDeltas) {
			int xLoc = this.loc.getX() + pos.getX();
			int yLoc = this.loc.getY() + pos.getY();
			if (0 <= xLoc && 0 <= yLoc)
				adjacentCoordinates.add(new Position(xLoc, yLoc));
		}
		return adjacentCoordinates;
	}
	public String getName() {
		return this.name;
	}
	public void moveUp() {
		this.loc = new Position(loc.getX(), loc.getY() + 1);
	}
	public void moveDown() {
		this.loc = new Position(loc.getX(), loc.getY() - 1);
	}
	public void moveLeft() {
		this.loc = new Position(loc.getX() - 1, loc.getY());
	}
	public void moveRight() {
		this.loc = new Position(loc.getX() + 1, loc.getY());
	}
	public void rotatePiece() {
		for (int i=0;i<pieceDeltas.size();i++) {
			pieceDeltas.set(i, new Position(pieceDeltas.get(i).getY(), 4 - pieceDeltas.get(i).getX()));
		}
		for (int i=0;i<cornerDeltas.size();i++) {
			cornerDeltas.set(i, new Position(cornerDeltas.get(i).getY(), 4 - cornerDeltas.get(i).getX()));
		}
		for (int i=0;i<adjacentDeltas.size();i++) {
			adjacentDeltas.set(i, new Position(adjacentDeltas.get(i).getY(), 4 - adjacentDeltas.get(i).getX()));
		}
	}
	public void flipPiece() {
		for (int i=0;i<pieceDeltas.size();i++) {
			pieceDeltas.set(i, new Position(4 - pieceDeltas.get(i).getX(), pieceDeltas.get(i).getY()));
		}
		for (int i=0;i<cornerDeltas.size();i++) {
			cornerDeltas.set(i, new Position(4 - cornerDeltas.get(i).getX(), cornerDeltas.get(i).getY()));
		}
		for (int i=0;i<adjacentDeltas.size();i++) {
			adjacentDeltas.set(i, new Position(4 - adjacentDeltas.get(i).getX(), adjacentDeltas.get(i).getY()));
		}
	}
}
