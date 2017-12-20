package ca.ece.ubc.cpen221.mp5;

import java.net.Socket;
import java.io.*;

// This class is for testing the YelpDBServer class and its methods
/**
 * YelpDBClient is a client that sends requests to the YelpDBServer
 * and interprets its replies.
 * A new YelpDBClient is "open" until the close() method is called,
 * at which point it is "closed" and may not be used further.
 */
public class YelpDBClient {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	//RI: socket, in, and out != null
	
    /**
     * Make a FibonacciClient and connect it to a server running on
     * hostname at the specified port.
     * @throws IOException if can't connect
     */
    public YelpDBClient(String hostname, int port) throws IOException {
        this.socket = new Socket(hostname, port);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
    }
    
    /**
     * Send a request to the server. Requires this is "open".
     * @param request request to send to YelpDBServer
     * @throws IOException if network or server failure
     */
    public void sendRequest(String request) throws IOException {
        out.println(request);
        out.flush();
    }
    
    public String getReply() throws IOException {
        String reply = in.readLine();
        if (reply == null) {
            throw new IOException("connection terminated unexpectedly");
        }
   
    	return reply;
    }
    
    /**
     * Closes the client's connection to the server.
     * This client is now "closed". Requires this is "open".
     * @throws IOException if close fails
     */
    public synchronized void close() throws IOException {
        in.close();
        out.close();
        socket.close();
    }
    
}
