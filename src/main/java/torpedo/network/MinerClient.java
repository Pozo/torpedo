package torpedo.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import torpedo.network.protocol.FireResultType;
import torpedo.network.protocol.MinerProtocol;
import torpedo.network.protocol.Procedures;
import torpedo.network.protocol.RequestValidator;
import torpedo.network.protocol.ResponseValidator;

/**
 * MinerClient.
 * @author Zoltan_Polgar
 *
 */
public class MinerClient extends Client implements MinerProtocol {
    private static final int REQUEST_VALID_LENGTH = 3;
    private static final Logger LOGGER = LoggerFactory.getLogger(MinerClient.class);

    public MinerClient(InetAddress address, int portNumber) {
        super(address, portNumber);
    }
    /**
     * main.
     * @param args
     * @throws UnknownHostException
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
        if (args.length == 2) {
            MinerClient client = new MinerClient(InetAddress.getByName(args[0]), Integer.valueOf(args[1]));
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String request = null;

            while ((request = br.readLine().toUpperCase()) != null) {
                if (request.startsWith(Procedures.FIRE.name())) {
                    callFire(client, request);
                } else if (request.startsWith(Procedures.GREETING.name())) {
                    callGreeting(client, request);
                } else if (request.startsWith("QUIT")) {
                    LOGGER.info("Bye looser");
                    break;
                }
            }

        } else {
            LOGGER.error("You must specify the host and port number! (host first)");
        }
    }

    private static void callGreeting(MinerClient client, String request) {
        String[] splittedRequest = request.split(MinerProtocol.PROCEDURE_PARAMETER_SEPARATOR);

        if (splittedRequest.length == 2) {
            doGreeting(client, splittedRequest);
        }
    }

    private static void doGreeting(MinerClient client, String[] splittedRequest) {
        int boardSize = Integer.valueOf(splittedRequest[1]);

        client.greeting(boardSize);
    }

    private static void callFire(MinerClient client, String request) {
        if (new RequestValidator(request).validate()) {
            String[] splittedRequest = request.split(MinerProtocol.PROCEDURE_PARAMETER_SEPARATOR);

            if (splittedRequest.length == REQUEST_VALID_LENGTH) {
                doFire(client, splittedRequest);
            }
        } else {
            LOGGER.error("Wrong request !");
        }
    }

    private static void doFire(MinerClient client, String[] splittedRequest) {
        int xCoordinate = Integer.valueOf(splittedRequest[1]);
        int yCoordinate = Integer.valueOf(splittedRequest[2]);

        FireResultType retval = client.fire(xCoordinate, yCoordinate);
        LOGGER.info("{}", retval);
    }

    public void greeting(int boardSize) {
        try {
            sendRequest(String.format("%s%s%s", Procedures.GREETING.name(), MinerProtocol.PROCEDURE_PARAMETER_SEPARATOR, String.valueOf(boardSize)));
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public FireResultType fire(int x, int y) {
        FireResultType retval = FireResultType.MISS;

        try {
            String response = sendRequest(String.format("FIRE %s %s", String.valueOf(x), String.valueOf(y))).toUpperCase();

            if (new ResponseValidator(response).validate()) {
                retval = decideFireType(response);
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return retval;
    }

    private FireResultType decideFireType(String response) {
        FireResultType result = FireResultType.MISS;
        if (response.equals(FireResultType.HIT.name())) {
            result = FireResultType.HIT;

        } else if (response.equals(FireResultType.MISS.name())) {
            result = FireResultType.MISS;

        } else if (response.equals(FireResultType.SUNK.name())) {
            result = FireResultType.SUNK;

        } else if (response.equals(FireResultType.WIN.name())) {
            result = FireResultType.WIN;
        }
        return result;
    }
}
