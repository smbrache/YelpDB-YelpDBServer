package ca.ece.ubc.cpen221.mp5;

import java.io.*;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;

public class YelpDBServer {

	public static final int YELPDB_PORT = 4949;

	private ServerSocket serverSocket;
	
	private YelpDB db;

	// Rep invariant: serverSocket != null
	//
	// Thread safety argument:
	// TODO YELPDB_PORT
	// TODO serverSocket
	// TODO socket objects
	// TODO readers and writers in handle()
	// TODO data in handle()

	// added a YelpDB to the constructor
	public YelpDBServer(YelpDB db, int portNumber) throws IOException {
		this.serverSocket = new ServerSocket(portNumber);
		this.db = db;
	}

	public void serve() throws IOException {
		while (true) {
			// block until a client connects
			final Socket socket = serverSocket.accept();
			// create a new thread to handle that client
			Thread handler = new Thread(new Runnable() {
				public void run() {
					try {
						try {
							handle(socket);
						} finally {
							socket.close();
						}
					} catch (IOException ioe) {
						// this exception wouldn't terminate serve(),
						// since we're now on a different thread, but
						// we still need to handle it
						ioe.printStackTrace();
					}
				}
			});
			// start the thread
			handler.start();
		}
	}
	
	private void handle(Socket socket) throws IOException {
		System.err.println("client connected");

		// get the socket's input stream, and wrap converters around it
		// that convert it from a byte stream to a character stream,
		// and that buffer it so that we can read a line at a time
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		// similarly, wrap character=>bytestream converter around the
		// socket output stream, and wrap a PrintWriter around that so
		// that we have more convenient ways to write Java primitive
		// types to it.
		PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

		try {
			// each request is a single line: a command followed by info which are separated by a single space
			for (String line = in.readLine(); line != null; line = in.readLine()) {
				System.err.println("request: " + line);
				
				// break up request into command and info
				String command = line.substring(0, line.indexOf(' ')).trim();
				String info = line.substring(line.indexOf(' '), line.length()).trim();
				
				// what type of exception should we throw?
				try {
					// figure out what the command is
					if(command.equals("GETRESTAURANT")) {
						String businessID = info;
						String restaurantJSON = db.getRestaurantJSON(businessID);
						
					}else if(command.equals("GETUSER")) {
						String userID = info;
						String userJSON = db.getUserJSON(userID);
						
					}else if(command.equals("GETREVIEW")) {
						String reviewID = info;
						String reviewJSON = db.getReviewJSON(reviewID);
						
					}else if(command.equals("ADDRESTAURANT")) {
						String restaurantInfo = info;
						
					}else if(command.equals("ADDREVIEW")) {
						String reviewInfo = info;
						
					}else if(command.equals("ADDUSER")) {
						String userInfo = info;
						
					}

				} catch (NumberFormatException e) {
					// complain about ill-formatted request
					System.err.println("reply: err");
					out.print("err\n");
				}
				// important! our PrintWriter is auto-flushing, but if it were
				// not:
				// out.flush();
			}
		} finally {
			out.close();
			in.close();
		}
	}
}
