package torpedo.utils;

import torpedo.board.GameBoard;
import torpedo.coordinate.Coordinate;


public class GameBoardPrinter {
	private final GameBoard gameBoard;
	
	public GameBoardPrinter(GameBoard gameBoard) {
		this.gameBoard = gameBoard;
	}
	public void print() {
		for (int i = this.gameBoard.getBoardWidth()-1; i >= 0; i--) {
			StringBuilder sb = new StringBuilder();
			
			for (int j = 0; j <= this.gameBoard.getBoardHeight()-1; j++) {
				
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
