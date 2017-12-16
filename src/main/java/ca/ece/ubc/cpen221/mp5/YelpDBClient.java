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
        out.flush(); // important! make sure x actually gets sent
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
    public void close() throws IOException {
        in.close();
        out.close();
        socket.close();
    }
    
    public static void main(String[] args) {

        try {
    		YelpDBClient client = new YelpDBClient("localhost", YelpDBServer.YELPDB_PORT);

    // send the requests to do each of the six commands supported by the server
            client.sendRequest("ADDRESTAURANT {\"longitude\": 42.0, \"latitude\": 42.0, \"state\": \"state\", \"city\": \"city\", \"full_address\": \"full_address\", \"neighborhoods\": [\"Dank\", \"Memes\"], \"schools\": [\"school\"], \"name\": \"name\", \"categories\": [\"cat1\", \"cat2\"]}");
            System.out.println("ADDRESTAURANT {\"longitude\": 42.0, \"latitude\": 42.0, \"state\": \"state\", \"city\": \"city\", \"full_address\": \"full_address\", \"neighborhoods\": [\"Dank\", \"Memes\"], \"schools\": [\"school\"], \"name\": \"name\", \"categories\": [\"cat1\", \"cat2\"]}");
            
            client.sendRequest("ADDREVIEW {\"business_id\": \"bizID\", \"text\": \"text\", \"user_id\": \"userID\", \"date\": \"date\"}");
            System.out.println("ADDREVIEW {\"business_id\": \"bizID\", \"text\": \"text\", \"user_id\": \"userID\", \"date\": \"date\"}");
            
            client.sendRequest("ADDUSER {\"name\": \"name\"}");
            System.out.println("ADDUSER {\"name\": \"name\"}");
            
            // first restaurant in db, Cafe 3
            client.sendRequest("GETRESTAURANT gclB3ED6uk6viWlolSb_uA");
            System.out.println("GETRESTAURANT gclB3ED6uk6viWlolSb_uA");
            
            // first review in db, the pizza place review
            client.sendRequest("GETREVIEW 0a-pCW4guXIlWNpVeBHChg");
            System.out.println("GETREVIEW 0a-pCW4guXIlWNpVeBHChg");
            
            // first user in db, Chris M.
            client.sendRequest("GETUSER _NH7Cpq3qZkByP5xR4gXog");
            System.out.println("GETUSER _NH7Cpq3qZkByP5xR4gXog");
            
    // collect the replies
            String addRestReply = client.getReply();
            System.out.println("Added Restaurant JSON: " + addRestReply);
            
            String addReviewReply = client.getReply();
            System.out.println("Added Review JSON: " + addReviewReply);
            
            String addUserReply = client.getReply();
            System.out.println("Added User JSON: " + addUserReply);
            
            String getRestReply = client.getReply();
            System.out.println("got Restaurant JSON: " + getRestReply);
            
            String getReviewReply = client.getReply();
            System.out.println("got Review JSON: " + getReviewReply);
            
            String getUserReply = client.getReply();
            System.out.println("got User JSON: " + getUserReply);
            
            client.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
}
