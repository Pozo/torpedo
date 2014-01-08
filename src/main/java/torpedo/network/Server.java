package torpedo.network;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Server.
 * @author Zoltan_Polgar
 *
 */
public abstract class Server {
    private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);
    private static final String SIGNAL_END = "\n";

    private ServerSocket serverSocket;
    private boolean listening;

    /**
     * Server.
     * @param listeningPortNumber port number
     */
    public Server(int listeningPortNumber) {
        try {
            serverSocket = new ServerSocket(listeningPortNumber);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
    /**
     * startListening.
     */
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
            LOGGER.error(e.getMessage());
        }
    }
    /**
     * processRequest.
     * @param socket socket
     * @throws IOException io exception
     */
    protected abstract void processRequest(Socket socket) throws IOException;

    public void setListening(boolean state) {
        listening = state;
    }
    /**
     * sendResponse.
     * @param socket socket
     * @param message message
     * @throws IOException IO Exception
     */
    protected void sendResponse(Socket socket, String message) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        bw.write(message + SIGNAL_END);
        bw.flush();
    }
}
