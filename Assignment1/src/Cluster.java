import java.util.ArrayList;

/* A group of connected pieces of the same colour. */
public class Cluster {
	private ArrayList<Position> nodes;
	/* Colour of the pieces in the cluster. B for black, W for white */
	private char colour;
	public char getColour() {
		return colour;
	}

	public void setColour(char colour) {
		this.colour = colour;
	}

	public Cluster(char colour){
		nodes = new ArrayList<Position>(0);
		this.colour = colour;
	}
	
	public void addNode(Position newNode){
		nodes.add(newNode);
	}
	
	public ArrayList<Position> getNodes() {
		return nodes;
	}

	public void setNodes(ArrayList<Position> nodes) {
		this.nodes = nodes;
	}

	public int testWin(){
		/* Stub. Returns 0 if cluster does not fulfil a win condition
		 * 1 if cluster is a loop
		 * 2 if cluster is tripod
		 * 3 if cluster is both
		 */
		return 0;
	}
}
