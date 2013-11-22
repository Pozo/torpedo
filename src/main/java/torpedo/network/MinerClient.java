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

public class MinerClient extends Client implements MinerProtocol {
	private static final Logger logger = LoggerFactory.getLogger(MinerClient.class);
	
	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		if(args.length == 2) {
			MinerClient client = new MinerClient(InetAddress.getByName(args[0]), Integer.valueOf(args[1]));
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String request = null;

			while((request = br.readLine().toUpperCase())!=null) {
				if(request.startsWith(Procedures.FIRE.name())) {
					callFire(client, request);
				} else if(request.startsWith(Procedures.GREETING.name())) {
					callGreeting(client, request);
				} else if(request.startsWith("QUIT")) {
					logger.info("Bye looser");
					break;
				}
			}
			
		} else {
			logger.error("You must specify the host and port number! (host first)");
		}
	}

	private static void callGreeting(MinerClient client, String request) {
		String[] splittedRequest = request.split(MinerProtocol.PROCEDURE_PARAMETER_SEPARATOR);
		
		if(splittedRequest.length == 2) {
			doGreeting(client, splittedRequest);
		}
	}

	private static void doGreeting(MinerClient client, String[] splittedRequest) {
		int boardSize = Integer.valueOf(splittedRequest[1]);

		client.greeting(boardSize);
	}

	private static void callFire(MinerClient client, String request) {
		if(new RequestValidator(request).validate()) {
			String[] splittedRequest = request.split(MinerProtocol.PROCEDURE_PARAMETER_SEPARATOR);
			
			if(splittedRequest.length == 3) {
				doFire(client, splittedRequest);							
			}
		} else {
			logger.error("Wrong request !");
		}
	}

	private static void doFire(MinerClient client, String[] splittedRequest) {
		int xCoordinate = Integer.valueOf(splittedRequest[1]);
		int yCoordinate = Integer.valueOf(splittedRequest[2]);
		
		FireResultType retval = client.fire(xCoordinate, yCoordinate);
		logger.info("{}",retval);
	}
	
	public MinerClient(InetAddress address, int portNumber)	throws UnknownHostException, IOException {
		super(address, portNumber);
	}

	public void greeting(int boardSize) {
		try {
			sendRequest(String.format("%s%s%s",Procedures.GREETING.name(),MinerProtocol.PROCEDURE_PARAMETER_SEPARATOR,String.valueOf(boardSize)));
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public FireResultType fire(int x, int y) {
		FireResultType retval = FireResultType.MISS;
		
		try {			
			String response = sendRequest(String.format("FIRE %s %s", String.valueOf(x), String.valueOf(y))).toUpperCase();
			
			if(new ResponseValidator(response).validate()) {
				retval = decideFireType(retval, response);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retval;
	}

	private FireResultType decideFireType(FireResultType retval, String response) {
		if(response.equals(FireResultType.HIT.name())) {
			retval = FireResultType.HIT;
			
		} else if(response.equals(FireResultType.MISS.name())) {
			retval = FireResultType.MISS;
			
		} else if(response.equals(FireResultType.SUNK.name())) {
			retval = FireResultType.SUNK;
			
		} else if(response.equals(FireResultType.WIN.name())) {
			retval = FireResultType.WIN;
		}
		return retval;
	}
}
