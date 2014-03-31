public class Controller {
	private static Board board;
	
	public static void main(String[] args) {
		board = new Board();
		board.printBoard();
		board.makeClusters();
		board.printClusters();
		for(Cluster clust: board.getClusters()){
			clust.testTripod(board);
		}
	}
		
	

}
