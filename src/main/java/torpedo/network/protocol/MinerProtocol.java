package torpedo.network.protocol;



public interface MinerProtocol {
	public static final String PROCEDURE_PARAMETER_SEPARATOR = " ";
	public static final String PROCEDURE_GREETING = "GREETING";
	public static final String PROCEDURE_FIRE = "FIRE";
	
	public void greeting(int boardSize);
	public FireResultType fire(int x, int y);
}
