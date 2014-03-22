
public class Position {
	/* The tile's x coordinate */
	private int x;
	/* The tile's y coordinate */
	private int y;
	/* The colour of the piece on the tile ('B', 'W' or '-') */
	private char colour;
	
	public Position(int x, int y, char colour) {
		super();
		this.x = x;
		this.y = y;
		this.colour = colour;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public char getColour() {
		return colour;
	}
	public void setColour(char colour) {
		this.colour = colour;
	}
	
	public Position[] getAdjacents(){
		/* Stub, returns an array of adjacent tiles */
		return null;
	}
}
