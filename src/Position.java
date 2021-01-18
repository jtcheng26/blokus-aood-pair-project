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
}