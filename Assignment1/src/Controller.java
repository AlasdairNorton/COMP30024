
public class Controller {
	private static Board board;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		board = new Board(Integer.parseInt(args[0]));
		readInput(args);
		board.printBoard();
		
		board.getNodes()[0][0].getAdjacents(board);
		board.getNodes()[0][4].getAdjacents(board);
		board.getNodes()[2][3].getAdjacents(board);
		board.getNodes()[4][0].getAdjacents(board);
		board.getNodes()[4][8].getAdjacents(board);
		board.getNodes()[8][0].getAdjacents(board);
		board.getNodes()[8][4].getAdjacents(board);
		board.getNodes()[7][2].getAdjacents(board);
	}
	
	public static void readInput(String[] args){
		int i, j, nextchar=1;
		int size = board.getSize();
		for(i=0;i<2*size-1;i++){
			for(j=0;j<2*size-1-Math.abs(size-(i+1));j++){
				board.getNodes()[i][j].setColour(args[nextchar].charAt(0));
				nextchar++;
			}
		}
	}

}
