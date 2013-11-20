package torpedo.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

import torpedo.network.protocol.FireResultType;
import torpedo.network.protocol.MinerProtocol;
import torpedo.network.protocol.RequestValidator;
import torpedo.network.protocol.ResponseValidator;

public class MinerClient extends Client implements MinerProtocol {
	
	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		if(args.length == 2) {
			MinerClient client = new MinerClient(InetAddress.getByName(args[0]), Integer.valueOf(args[1]));
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String request = null;

			while((request = br.readLine().toUpperCase())!=null) {
				if(request.startsWith(MinerProtocol.PROCEDURE_FIRE)) {
					if(new RequestValidator(request).validate()) {
						String[] splittedRequest = request.split(MinerProtocol.PROCEDURE_PARAMETER_SEPARATOR);
						
						if(splittedRequest.length == 3) {
							int xCoordinate = Integer.valueOf(splittedRequest[1]);
							int yCoordinate = Integer.valueOf(splittedRequest[2]);
							
							System.out.println(client.fire(xCoordinate, yCoordinate));							
						}
					} else {
						System.err.println("Wrong request !");
					}
				} else if(request.startsWith(MinerProtocol.PROCEDURE_GREETING)) {
					String[] splittedRequest = request.split(MinerProtocol.PROCEDURE_PARAMETER_SEPARATOR);
					
					if(splittedRequest.length == 2) {
						int boardSize = Integer.valueOf(splittedRequest[1]);

						client.greeting(boardSize);
					}
				} else if(request.startsWith("QUIT")) {
					System.out.println("Bye looser");
					break;
				}
			}
			
		} else {
			System.err.println("You must specify the host and port number! (host first)");
		}
	}
	
	public MinerClient(InetAddress address, int portNumber)	throws UnknownHostException, IOException {
		super(address, portNumber);
	}

	public void greeting(int boardSize) {
		try {
			System.out.println(String.format("%s%s%s",MinerProtocol.PROCEDURE_GREETING,MinerProtocol.PROCEDURE_PARAMETER_SEPARATOR,String.valueOf(boardSize)));
			sendRequest(String.format("%s%s%s",MinerProtocol.PROCEDURE_GREETING,MinerProtocol.PROCEDURE_PARAMETER_SEPARATOR,String.valueOf(boardSize)));
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
				if(response.equals(FireResultType.HIT.name())) {
					retval = FireResultType.HIT;
					
				} else if(response.equals(FireResultType.MISS.name())) {
					retval = FireResultType.MISS;
					
				} else if(response.equals(FireResultType.SUNK.name())) {
					retval = FireResultType.SUNK;
					
				} else if(response.equals(FireResultType.WIN.name())) {
					retval = FireResultType.WIN;
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retval;
	}
}
