import java.util.Scanner;


public class Board {
	/* The length of the board's sides */
	private int size;
	/* Length of the sides of the board the program stores
	 * The program considers a board one size larger than it actually is-
	 * Pieces cannot be placed on the outer edge, but it is used in
	 * computation
	 */
	private int arraySize;


	/* Array containing all the positions on the board
	 * First dimension is y (rows), second is x (position in row) */
	private Position[][] nodes;
	
	/* Creates a new blank board, given its size */
	public Board (int size){
		int i, j;
		this.size = size;
		this.arraySize = size+1;
		nodes = new Position[2*arraySize-1][2*arraySize-1];
		/* Rows start at 0, until the 'size'th row,
		 * at which point the first index begins increasing by 1 per row.
		 * j(init) = Math.max(0, i-size+1)
		 * Last index of a row starts at (size-1) and increases until (2*size-2)
		 * j < Math.min(size+i, 2*size-1)
		 * Initialise board by filling it with blank tiles
		 */
		for(i=0;i<2*arraySize-1;i++){
			for(j=Math.max(0, i-arraySize+1); j< Math.min(arraySize+i, 2*arraySize-1) ;j++){
				if(i==0 || i== 2*arraySize-2
						|| j==Math.max(0, i-arraySize+1)
						|| j==Math.min(arraySize+i, 2*arraySize-1)-1){
					/* If first row, last row, first column, last column */
					nodes[i][j] = new Position(i,j,'O');
				}else{
					nodes[i][j] = new Position(i,j,'-');
				}
			}
		}
	}
	
	/* Creates a new board defined by the standard input */
	public Board (String[] args){
		//Scanner sc = new Scanner(System.in);
		int i, j, nextchar=1;
		this.size = Integer.parseInt(args[0]);
		this.arraySize = size+1;
		nodes = new Position[2*arraySize-1][2*arraySize-1];
		/* Length of each row = 2*size-1 - |size-(row+1)|
		 * assuming rows start at 0.
		 * Initialise board by filling it with blank tiles
		 */
		for(i=0;i<2*arraySize-1;i++){
			for(j=Math.max(0, i-arraySize+1); j< Math.min(arraySize+i, 2*arraySize-1) ;j++){
				if(i==0 || i== 2*arraySize-2
						|| j==Math.max(0, i-arraySize+1)
						|| j==Math.min(arraySize+i, 2*arraySize-1)-1){
					/* If first row, last row, first column, last column */
					nodes[i][j] = new Position(i,j,'O');
				}else{
					nodes[i][j] = new Position(i,j,args[nextchar].charAt(0));
					nextchar++;
				}
				
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
	
	public int getArraySize() {
		return arraySize;
	}

	public void setArraySize(int arraySize) {
		this.arraySize = arraySize;
	}


	public void printBoard(){
		int i, j;
		for(i=0;i<2*arraySize-1;i++){
			for(j=Math.max(0, i-arraySize+1); j< Math.min(arraySize+i, 2*arraySize-1) ;j++){
				System.out.print(nodes[i][j].getColour());
			}
			System.out.print("\n");
		}
	}
}
