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
				String command = line.substring(0, line.indexOf(' ')).trim();
				String info = line.substring(line.indexOf(' '), line.length()).trim();

				// what type of exception should we throw?
				try {
					// figure out what the command is
					if (command.equals("GETRESTAURANT")) {
						String businessID = info;
						String restaurantJSON = db.getRestaurantJSON(businessID);
						out.println(restaurantJSON);

					} else if (command.equals("GETUSER")) {
						String userID = info;
						String userJSON = db.getUserJSON(userID);
						out.println(userJSON);

					} else if (command.equals("GETREVIEW")) {
						String reviewID = info;
						String reviewJSON = db.getReviewJSON(reviewID);
						out.print(reviewJSON);

					} else if (command.equals("ADDRESTAURANT")) {
						// parse info into JsonObject restaurantInfo
						JsonReader parseRestaurant = Json.createReader(new StringReader(info));
						JsonObject restaurantInfo = parseRestaurant.readObject();

						// Convert JsonObject restaurantInfo to JSON format String
						String addRestoJSON = db.serverAddRestaurant(restaurantInfo);
						out.print(addRestoJSON);

					} else if (command.equals("ADDREVIEW")) {
						// parse info into JsonObject reviewInfo
						JsonReader parseReview = Json.createReader(new StringReader(info));
						JsonObject reviewInfo = parseReview.readObject();

						// Convert JsonObject reviewInfo to JSON format String
						String addReviewJSON = db.serverAddReview(reviewInfo);
						out.print(addReviewJSON);

					} else if (command.equals("ADDUSER")) {
						// parse info into JsonObject userInfo
						JsonReader parseUser = Json.createReader(new StringReader(info));
						JsonObject userInfo = parseUser.readObject();

						// Convert JsonObject userInfo to JSON format String
						String addUserJSON = db.serverAddUser(userInfo);
						out.print(addUserJSON);
					}

					// TODO: still need to modify catch block below, taken from fibonacci server
				} catch (NumberFormatException e) {
					// complain about ill-formatted request
					System.err.println("reply: err");
					out.print("err\n");
				}

			}
		} finally {
			out.close();
			in.close();
		}
	}
}
