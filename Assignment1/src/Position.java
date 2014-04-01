import java.util.ArrayList;


public class Position {
	/* The tile's x coordinate */
	private int x;
	/* The tile's y coordinate */
	private int y;
	/* The colour of the piece on the tile ('B', 'W', '-' or 'O') */
	private char colour;
	/* The cluster this node belongs to (null by default) */
	private Cluster parentCluster;
	
	public Position(int y, int x, char colour) {
		super();
		this.x = x;
		this.y = y;
		this.colour = colour;
		this.parentCluster = null;
	}
	
	public Cluster getParentCluster() {
		return parentCluster;
	}
	public void setParentCluster(Cluster parentCluster) {
		this.parentCluster = parentCluster;
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
	
	/* Takes the board containing this position, returns the
	 * 3-6 adjacent positions in an ArrayList
	 */
	public ArrayList<Position> getAdjacents(Board board){
		ArrayList<Position> adjacents = new ArrayList<Position>(0);
		/* First get tiles to left and right */
		if(this.x>0){
			adjacents.add(board.getNodes()[this.y][this.x-1]);
		}
		/* Length of a row is given by
		 * Math.min(size+y, 2*size-1)
		 */
		if(this.x<Math.min(board.getArraySize()+this.y, 2*board.getArraySize()-2)){
			adjacents.add(board.getNodes()[this.y][this.x+1]);
		}
		/* Next, find the adjacent positions in the rows above and below
		 * For the tile at [y,x] these are located at
		 * [y-1,x-1],[y-1,x],[y+1,x] and [y+1,x+1] 
		 */
		if(this.y>0){
			// Row above exists
			if(this.x < board.getArraySize()-1+this.y){
				// Check tile above and to the right exists
				adjacents.add(board.getNodes()[this.y-1][this.x]);
			}
			if(this.x > 0){
				// Check tile above and to the left exists
				adjacents.add(board.getNodes()[this.y-1][this.x-1]);
			}
		}

		if(this.y<2*board.getArraySize()-2){
			// Row below exists
			if(this.x < 2*board.getArraySize()-2){
				// Check tile below and to the right exists
				adjacents.add(board.getNodes()[y+1][x+1]);
			}
			if(this.x > this.y-board.getArraySize()+1){
				// Check tile below and to the left exists
				adjacents.add(board.getNodes()[y+1][x]);
			}
		}

		return adjacents;
	}
	
	/* Testing purposes. Prints a list of adjacent nodes */
	public void printAdjacents(ArrayList<Position> nodes){
		System.out.print("For node ("+this.y+","+this.x+"):");
		for(Position node:nodes){
			System.out.print("("+node.getY()+","+node.getX()+")");
		}
		System.out.print("\n");
	}
}
