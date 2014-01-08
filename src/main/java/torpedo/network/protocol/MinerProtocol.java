package torpedo.network.protocol;

/**
 * MinerProtocol.
 * @author Zoltan_Polgar
 *
 */
public interface MinerProtocol {
    String PROCEDURE_PARAMETER_SEPARATOR = " ";
    /**
     * greeting.
     * @param boardSize board size
     */
    void greeting(int boardSize);
    /**
     * fire.
     * @param x x coordinate
     * @param y y coordinate
     * @return FireResultType
     */
    FireResultType fire(int x, int y);
}
