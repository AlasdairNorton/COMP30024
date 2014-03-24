import java.util.ArrayList;


public class Position {
	/* The tile's x coordinate */
	private int x;
	/* The tile's y coordinate */
	private int y;
	/* The colour of the piece on the tile ('B', 'W' or '-') */
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
		 * (2*size-1)-abs(size-(row+1)) (assuming rows indexed from 0)
		 * e.g. for board size 5, the first row is 
		 * 2*5-1-abs(5-1)=10-1-4=5
		 * and the fifth (longest) row is
		 * 2*5-1-abs(5-5) = 9
		 * Hence, to test for a position not being the last in a row
		 * x < 2*size-1 - |size-(y+1)| - 1
		 */
		if(this.x<2*board.getSize()-1-Math.abs(board.getSize()-(this.y+1))-1){
			adjacents.add(board.getNodes()[this.y][this.x+1]);
		}
		/* Next, find the adjacent positions in the rows above and below
		 * If said row is longer, the tiles will be at
		 *  (y-1)(x) and (y-1)(x+1) / (y+1)(x) and (y+1)(x+1)
		 * If the rows are shorter, they will be at
		 *  (y-1)(x-1) and (y-1)(x) / (y+1)(x-1) and (y+1)(x)
		 *  
		 * As a rule, 
		 * if y<size-1, the row above is shorter, the row below is longer
		 * if y=size-1, the rows above and below are shorter
		 * if y>size-1, the row below is shorter and the row above is longer
		 */
		if(this.y<board.getSize()-1){
			// Top half of board 
			if(this.y>0){
				// Row above exists
				if(this.x-1>=0){
					adjacents.add(board.getNodes()[y-1][x-1]);
				}
				if(this.x<2*board.getSize()-1-Math.abs(board.getSize()-(this.y))-1){
					adjacents.add(board.getNodes()[y-1][x]);
				}
			}
			/* These positions always exist given the restrictions
			 * So no guards are required.
			 */
			adjacents.add(board.getNodes()[y+1][x]);
			adjacents.add(board.getNodes()[y+1][x+1]);
		}
		
		if(this.y==board.getSize()-1){
			// Middle row
			if(this.x-1>=0){
				adjacents.add(board.getNodes()[y-1][x-1]);
				adjacents.add(board.getNodes()[y+1][x-1]);
			}
			if(this.x<2*board.getSize()-1-Math.abs(board.getSize()-(this.y))-1){
					adjacents.add(board.getNodes()[y-1][x]);
					adjacents.add(board.getNodes()[y+1][x]);
			}
		}
		
		if(this.y>board.getSize()-1){
			// Bottom half of board 
			if(this.y<2*board.getSize()-2){
				// Row below exists
				if(this.x-1>=0){
					adjacents.add(board.getNodes()[y+1][x-1]);
				}
				if(this.x<2*board.getSize()-1-Math.abs(board.getSize()-(this.y))-1){
					adjacents.add(board.getNodes()[y+1][x]);
				}
			}
			/* These positions always exist given the restrictions
			 * So no guards are required.
			 */
			adjacents.add(board.getNodes()[y-1][x]);
			adjacents.add(board.getNodes()[y-1][x+1]);
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
