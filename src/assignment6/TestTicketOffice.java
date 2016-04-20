package assignment6;

import static org.junit.Assert.fail;

import org.junit.Test;

public class TestTicketOffice {

	public static int score = 0;
	
	@Test
	public void basicServerTest() {
		Stadium newStadium = new Stadium();
		try {
			TicketServer.start(16789, 16790, newStadium);
		} catch (Exception e) {
			fail();
		}
		TicketClient [] c = new TicketClient[100];												// Each TickentClient Object is a new request.
		for (int i = 0; i < 100; i++){
		c[i] = new TicketClient();
		}
		for (int i = 0; i <100; i++){
			c[i].requestTicket();
		}
	}

	//@Test
	public void testServerCachedHardInstance() {
		Stadium newStadium = new Stadium();
		try {
			TicketServer.start(16790, 16791, newStadium);
		} catch (Exception e) {
			fail();
		}
		TicketClient client1 = new TicketClient("localhost", "c1");
		TicketClient client2 = new TicketClient("localhost", "c2");
		client1.requestTicket();
		client2.requestTicket();
		
	}

	//@Test
	public void twoNonConcurrentServerTest() {
		Stadium newStadium = new Stadium();
		try {
			TicketServer.start(16791, 16792, newStadium);
		} catch (Exception e) {
			fail();
		}
		TicketClient c1 = new TicketClient("nonconc1");
		TicketClient c2 = new TicketClient("nonconc2");
		TicketClient c3 = new TicketClient("nonconc3");
		c1.requestTicket();
		c2.requestTicket();
		c3.requestTicket();
	}

	//@Test
	public void twoConcurrentServerTest() {
		Stadium newStadium = new Stadium();
		try {
			TicketServer.start(16792, 16973, newStadium);
		} catch (Exception e) {
			fail();
		}
		final TicketClient c1 = new TicketClient("conc1");
		final TicketClient c2 = new TicketClient("conc2");
		final TicketClient c3 = new TicketClient("conc3");
		Thread t1 = new Thread() {
			public void run() {
				c1.requestTicket();
			}
		};
		Thread t2 = new Thread() {
			public void run() {
				c2.requestTicket();
			}
		};
		Thread t3 = new Thread() {
			public void run() {
				c3.requestTicket();
			}
		};
		t1.start();
		t2.start();
		t3.start();
		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
