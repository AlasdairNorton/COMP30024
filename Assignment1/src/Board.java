

public class Board {
	/* The length of the board's sides */
	private int size;
	/* Array containing all the positions on the board
	 * First dimension is y (rows), second is x (position in row) */
	private Position[][] nodes;
	
	public Board (int size){
		int i, j;
		this.size = size;
		nodes = new Position[2*size-1][];
		/* Length of each row = 2*size-1 - |size-(row+1)|
		 * assuming rows start at 0.
		 * Initialise board by filling it with blank tiles
		 */
		for(i=0;i<2*size-1;i++){
			nodes[i] = new Position[2*size-1-Math.abs(size-(i+1))];
			for(j=0; j<2*size-1-Math.abs(size-(i+1)) ;j++){
				nodes[i][j] = new Position(j,i,'-');
			}
		}
	}
	
	
	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}


	public Position[][] getNodes() {
		return nodes;
	}


	public void setNodes(Position[][] nodes) {
		this.nodes = nodes;
	}


	public void printBoard(){
		int i, j;
		for(i=0;i<2*size-1;i++){
			for(j=0; j<2*size-1-Math.abs(size-(i+1)) ;j++){
				System.out.print(nodes[i][j].getColour());
			}
			System.out.print("\n");
		}
	}
}
