package torpedo.board;

import torpedo.coordinate.Coordinate;


public class ConsolePrinter implements GameBoardPrinter {
	private final GameBoard gameBoard;
	
	public ConsolePrinter(GameBoard gameBoard) {
		this.gameBoard = gameBoard;
	}
	public void print() {
		for (int i = this.gameBoard.getWidth()-1; i >= 0; i--) {
			StringBuilder sb = new StringBuilder();
			
			for (int j = 0; j <= this.gameBoard.getHeight()-1; j++) {
				
				if(this.gameBoard.getAllShipCoordinates().contains(new Coordinate(j, i))) {
					sb.append("[X]");
					
				} else {
					sb.append("[ ]");
				}
			}
			System.out.println(sb.toString());
		}
	}
}
