class Position {
	private int x, y;
	Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	public boolean equals (Object obj) {
		if (this == obj) {
			return true;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		if (obj.getClass().getName() == "Position") {
			Position comparePos = (Position) obj;
			if (this.getX() == comparePos.getX() 
				&& this.getY() == comparePos.getY()) {
				return true;
			}
		}
		return false;
	}
	public static void main (String[] args) {
		Player player1 = new Player("Matthew", 1, 0);
		System.out.println(player1.getPiece(0).getPieceCoordinates().get(0).equals(new Position (0,0)));
	}
}