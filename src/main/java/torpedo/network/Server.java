package torpedo.network;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import torpedo.network.protocol.MinerProtocol;

public abstract class Server implements MinerProtocol {
	private ServerSocket serverSocket;
	private boolean listening;

	public Server(int listeningPortNumber) {
		try {
			serverSocket = new ServerSocket(listeningPortNumber);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			System.exit(-1);
		}
	}

	public void startListening() {
		try {
			setListening(true);

			while (listening) {
				Socket socket = serverSocket.accept();
				//String remoteSocketAddress = socket.getRemoteSocketAddress().toString();

				processRequest(socket);
			}
			serverSocket.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	protected abstract void processRequest(Socket socket) throws IOException;
	public void setListening(boolean state) {
		this.listening = true;
	}
	protected void sendResponse(Socket socket, String message) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

		bw.write(message + "\n");
		bw.flush();
	}
}

