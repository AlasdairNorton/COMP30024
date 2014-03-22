
public class Controller {
	private static Board board;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		board = new Board(Integer.parseInt(args[0]));
		readInput(args);
		board.printBoard();
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
