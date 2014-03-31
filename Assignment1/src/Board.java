import java.util.ArrayList;
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
	
	/* Arraylist containing all clusters (continuous groups of same-coloured pieces) */
	private ArrayList<Cluster> clusters;
	
	/* Creates a new blank board, given its size */
	public Board (int size){
		int i, j;
		this.clusters = new ArrayList<Cluster>(0);
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
	public Board (){
		
		Scanner sc = new Scanner(System.in);
		int i, j;
		
		if(sc.hasNextInt()){
			this.size = sc.nextInt();
		} else {
			System.out.println("Invalid input: No size given");
			System.exit(1);
		}	
		
		this.arraySize = size+1;
		nodes = new Position[2*arraySize-1][2*arraySize-1];

		for(i=0;i<2*arraySize-1;i++){
			for(j=Math.max(0, i-arraySize+1); j< Math.min(arraySize+i, 2*arraySize-1) ;j++){
				if(i==0 || i== 2*arraySize-2
						|| j==Math.max(0, i-arraySize+1)
						|| j==Math.min(arraySize+i, 2*arraySize-1)-1){
					/* If first row, last row, first column, last column */
					nodes[i][j] = new Position(i,j,'O');
				}else{
					if(sc.hasNext()){
						nodes[i][j] = new Position(i,j,sc.next().charAt(0));
					} else {
						System.out.println("Input Error: Syntax Error");
						System.exit(1);
					}
				}
				
			}
		}
	}
	
	public ArrayList<Cluster> getClusters() {
		return clusters;
	}

	public void setClusters(ArrayList<Cluster> clusters) {
		this.clusters = clusters;
	}

	/* Takes the board state, determines individual groups of pieces */
	public void makeClusters(){
		int i, j;
		Position node;
		for(i=0;i<2*arraySize-1;i++){
			for(j=Math.max(0, i-arraySize+1);j< Math.min(arraySize+i, 2*arraySize-1);j++){
				node = nodes[i][j];
				if(node.getParentCluster() == null
						&& node.getColour()!='-' && node.getColour()!='O'){
					// Node is not in a cluster and is not empty
					fillCluster(node);
				}
			}
		}
	}
	
	/* Given a piece, finds all connected pieces of the same colour,
	 * creates a cluster object containing them
	 * 
	 * Method: Initially adds the node it is passed to the cluster.
	 * Populates the toAdd list with all the adjacent nodes.
	 * The first adjacent node is popped off, if it is the right colour,
	 * and not already in a cluster, it is added to the cluster, and all its
	 * adjacent nodes are added to the list.
	 * Repeat until list is empty.
	 */
	public void fillCluster(Position node){
		Cluster newCluster = new Cluster(node.getColour());
		ArrayList<Position> toAdd = new ArrayList<Position>(0);
		Position tempNode;
		
		newCluster.addNode(node);
		node.setParentCluster(newCluster);
		toAdd.addAll(node.getAdjacents(this));
		
		while(!toAdd.isEmpty()){
			tempNode = toAdd.remove(0);
			if(tempNode.getParentCluster()==null &&
					tempNode.getColour()==node.getColour()){
				newCluster.addNode(tempNode);
				tempNode.setParentCluster(newCluster);
				toAdd.addAll(tempNode.getAdjacents(this));
			}
		}
		clusters.add(newCluster);
	}
	
	public void printClusters(){
		for(Cluster cluster:clusters){
			System.out.print(cluster.getColour()+" cluster, positions:");
			for(Position node:cluster.getNodes()){
				System.out.print("("+node.getY()+","+node.getX()+")");
			}
			System.out.print("\n");
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
