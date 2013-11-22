package torpedo.board;


public class SwingPrinter implements GameBoardPrinter {
	private final GameBoard gameBoard;

	public SwingPrinter(GameBoard gameBoard) {
		this.gameBoard = gameBoard;
	}
	
	public void print() {
		/*
		for (int j = 0; j <= this.gameBoard.getHeight()-1; j++) {
			
			if(this.gameBoard.getAllShipCoordinates().contains(new Coordinate(j, i))) {
				
				
			} else {
				
			}
		}
		*/
	}

}
