package torpedo.network.protocol;



public interface MinerProtocol {
	public static final String PROCEDURE_PARAMETER_SEPARATOR = " ";
	
	public void greeting(int boardSize);
	public FireResultType fire(int x, int y);
}
