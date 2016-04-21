/*Assignment6 EE 422C
 * Aftab Hadimohd ah35368
 * Royce Li rl26589
*/
package assignment6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ThreadedTicketClient implements Runnable {
	String hostname = "127.0.0.1";
	String threadname = "X";
	TicketClient sc;

	public ThreadedTicketClient(TicketClient sc, String hostname, String threadname) {
		this.sc = sc;
		this.hostname = hostname;
		this.threadname = threadname;
	}

	public void run() {
		while (true){
		System.out.flush();																					// Will try Server A, if it is busy
		try {																								// Use Server B. Loop until one is open
			Socket echoSocket = new Socket(hostname, TicketServer.PORT);
			PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
			out.println("Request for a ticket");
			BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
			while(in.readLine() == null){
			}
			System.out.println("Congrats you have your assigned seat, Client " + threadname);
			System.out.println();
			echoSocket.close();
			break;
		} catch (Exception e) {
		}
		try {
			Socket echoSocket = new Socket(hostname, TicketServer.PORT1);
			PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
			out.println("Request for a ticket");
			BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
			while(in.readLine() == null){
			}
			System.out.println("Congrats you have your assigned seat, Client " + threadname);
			System.out.println();
			echoSocket.close();
			break;
			}catch (Exception e) {
			}
		}
	}
}

public class TicketClient {
	ThreadedTicketClient tc;
	String result = "dummy";
	String hostName = "";
	String threadName = "";
	
	TicketClient(String hostname, String threadname) {
		tc = new ThreadedTicketClient(this, hostname, threadname);
		hostName = hostname;
		threadName = threadname;
	}

	TicketClient(String name) {
		this("localhost", name);
	}

	TicketClient() {
		this("localhost", "unnamed client");
	}

	void requestTicket() {
		tc.run();
	}

	void sleep() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
