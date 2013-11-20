package torpedo.network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	private static final String SIGNAL_END = "\n";
	
	private final InetAddress address;
	private final int portNumber;

	public Client(InetAddress address, int portNumber) throws UnknownHostException, IOException {
		this.address = address;
		this.portNumber = portNumber;
	}

	protected String sendRequest(String request) throws InterruptedException, IOException {
		Socket socket = new Socket(address, portNumber);

		this.writeRequest(socket, request);
		String response = this.readRequest(socket);

		socket.close();		
		return response;

	}
	private String readRequest(Socket socket) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		return br.readLine();
	}
	private void writeRequest(Socket socket, String message) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		bw.write(message + SIGNAL_END);
		bw.flush();			
	}
}
