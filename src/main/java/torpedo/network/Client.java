package torpedo.network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Client.
 * @author Zoltan_Polgar
 *
 */
public class Client {
    private static final String SIGNAL_END = "\n";

    private final InetAddress address;
    private final int portNumber;

    /**
     * Client.
     * @param address InetAddress
     * @param portNumber portNumber
     */
    public Client(InetAddress address, int portNumber) {
        this.address = address;
        this.portNumber = portNumber;
    }
    /**
     * sendRequest.
     * @param request request
     * @return response
     * @throws IOException
     */
    protected String sendRequest(String request) throws IOException {
        Socket socket = new Socket(address, portNumber);

        writeRequest(socket, request);
        String response = readRequest(socket);

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
