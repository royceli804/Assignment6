package assignment6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
	
public class TicketServer {
	static int PORT ;
	static int PORT1;
	// EE422C: no matter how many concurrent requests you get,
	// do not have more than three servers running concurrently
	final static int MAXPARALLELTHREADS = 3;
	
	public static void start(int portNumber, Stadium a) throws IOException {
		PORT = portNumber;																			
		Runnable serverThread = new ThreadedTicketServer(portNumber,"A", a);
		Thread t = new Thread(serverThread);
		t.start();
	}
	
	public static void start(int portNumber, int portNumber1, Stadium a) throws IOException {
		PORT = portNumber;																			// Two servers on two ports running simultaneously
		PORT1 = portNumber1;
		Runnable serverThread = new ThreadedTicketServer(portNumber,"A", a);
		Thread t = new Thread(serverThread);
		t.start();
		Runnable serverThread1 = new ThreadedTicketServer(portNumber1,"B", a);
		Thread t1 = new Thread(serverThread1);
		t1.start();
	}
	
}

class ThreadedTicketServer implements Runnable {
	String hostname = "127.0.0.1";
	String threadname = "X";
	String testcase;
	TicketClient sc;
	Stadium passed;
	int portNum;
	String serverName;

	public ThreadedTicketServer(int port, String name, Stadium a) {
		passed = a;
		portNum = port;
		serverName = name;
	}
	
	@Override
	public void  run() {
		// TODO 422C
		while(true){																							// Both servers run the same way
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(portNum);
			Socket clientSocket = serverSocket.accept();
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String clientRequest = in.readLine();
			if (clientRequest.equals("Request for a ticket")){
				System.out.println("Server " + serverName + "; " + passed.bestAvailableSeat());
				out.println("Done");
				try {
					Thread.sleep(100);																			// How long the user will see the message
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
				}
			}
			in.close();
			out.close();
			clientSocket.close();
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		}
	}
}