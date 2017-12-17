package ca.ece.ubc.cpen221.mp5;

import java.io.*;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

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
	public YelpDBServer(int portNumber) throws IOException {
		this.serverSocket = new ServerSocket(portNumber);
		this.db = new YelpDB("data/restaurants.json", "data/reviews.json", "data/users.json");
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

		// wrap character=>bytestream converter around the
		// socket output stream, and wrap a PrintWriter around that to have more
		// convenient ways to write Java primitive types to it.
		PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

		try {
			// each request is a single line: a command followed by info which are separated
			// by a single space
			for (String line = in.readLine(); line != null; line = in.readLine()) {
				System.err.println("request: " + line);

				// break up request into command and info
				final String command = line.substring(0, line.indexOf(' ')).trim();
				final String info = line.substring(line.indexOf(' '), line.length()).trim();

			// removed try-catch block here, should we have a format exception?
				// figure out what the command is
				if (command.equals("GETRESTAURANT")) {
					String businessID = info;
					final String restaurantJSON = db.getRestaurantJSON(businessID);
					System.err.println("-----------------------------------------------------------------------");
					out.println(restaurantJSON);

				} else if (command.equals("GETUSER")) {
					String userID = info;
					final String userJSON = db.getUserJSON(userID);
					out.println(userJSON);

				} else if (command.equals("GETREVIEW")) {
					String reviewID = info;
					final String reviewJSON = db.getReviewJSON(reviewID);
					out.println(reviewJSON);

				} else if (command.equals("ADDRESTAURANT")) {
					String restInfo = info;
					
					// parse info into JsonObject restaurantInfo
					JsonReader parseRestaurant = Json.createReader(new StringReader(restInfo));
					JsonObject restaurantInfo = parseRestaurant.readObject();
					
					// Convert JsonObject restaurantInfo to JSON format String
					final String addRestoJSON = db.serverAddRestaurant(restaurantInfo);
					out.println(addRestoJSON);
					

				} else if (command.equals("ADDREVIEW")) {
					String revInfo = info;

					// parse info into JsonObject reviewInfo
					JsonReader parseReview = Json.createReader(new StringReader(revInfo));
					JsonObject reviewInfo = parseReview.readObject();

					// Convert JsonObject reviewInfo to JSON format String
					final String addReviewJSON = db.serverAddReview(reviewInfo);
					out.println(addReviewJSON);

				} else if (command.equals("ADDUSER")) {
					String usrInfo = info;

					// parse info into JsonObject userInfo
					JsonReader parseUser = Json.createReader(new StringReader(usrInfo));
					JsonObject userInfo = parseUser.readObject();

					// Convert JsonObject userInfo to JSON format String
					final String addUserJSON = db.serverAddUser(userInfo);
					out.println(addUserJSON);
				} else {
					out.println("ERR: ILLEGAL_REQUEST");
				}
				System.out.println("for loop iteration");
			}
		} finally {
			System.out.println("finally: client thread closed");
			out.close();
			in.close();
		}
	}
}
