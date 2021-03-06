/*Assignment6 EE 422C
 * Aftab Hadimohd ah35368
 * Royce Li rl26589
*/
package assignment6;

import static org.junit.Assert.fail;

import java.util.Random;

import org.junit.Test;

public class TestTicketOffice {

	public static int score = 0;
	
	/******************************************************************************
	* Method Name: basicServerTest                                   			*
	* Purpose: Tests 1 customer with 1 server                  	  				*
	******************************************************************************/
	
	@Test
	public void basicServerTest() {
		Stadium newStadium = new Stadium();
		try {
			TicketServer.start(16789, newStadium);
		} catch (Exception e) {
			fail();
		}
		TicketClient client = new TicketClient();
		client.requestTicket();

	} 
	
	/******************************************************************************
	* Method Name: testServerCachedHardInstance                                   *
	* Purpose: Tests 2 customers with 2 box offices/servers                    	  *
	******************************************************************************/
	
	@Test
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

	/******************************************************************************
	* Method Name: twoNonConcurrentServerTest                                     *
	* Purpose: Tests 3 customers with 1 box office/server                    	  *
	******************************************************************************/
	@Test
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

	/******************************************************************************
	* Method Name: twoConcurrentServerTest                                    	  *
	* Purpose: Tests 3 customers with 2 box offices/servers                    	  *
	******************************************************************************/
	@Test
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
	
	/******************************************************************************
	* Method Name: our1ServerTest1                                     			  *
	* Purpose: Tests 100 customers with 1 box office/server                    	  *
	******************************************************************************/
	
	@Test
	public void our1ServerTest1() {
		Stadium newStadium = new Stadium();
		try {
			TicketServer.start(16789, newStadium);
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
	
	/******************************************************************************
	* Method Name: ourConcurrent2ServerTest2                                      *
	* Purpose: Tests 100 customers with 2 box offices/servers                     *
	******************************************************************************/
	@Test
	public void ourConcurrent2ServerTest2() {
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
	public void preservationofQueueServerTest() {
		Stadium newStadium = new Stadium();
		try {
			TicketServer.start(16792, 16973, newStadium);
		} catch (Exception e) {
			fail();
		}
		TicketClient [] c = new TicketClient[50];
		TicketClient [] d = new TicketClient[50];								// Each TickentClient Object is a new request.
		TicketClient [] e = new TicketClient[50];
		for (int i = 0; i < 50; i++){
		c[i] = new TicketClient("Aftab");
		d[i] = new TicketClient("Royce");
		e[i] = new TicketClient("DeWayne");
		}
		for (int i = 0; i < 50; i++){
			c[i].requestTicket();
			d[i].requestTicket();
			e[i].requestTicket();
			}
	}
	
	/******************************************************************************
	* Method Name: randomClientConstructServerTest                                *
	* Purpose: Tests random number of customers with 2 box offices/servers        *
	******************************************************************************/
	
	@Test
		public void randomClientConstructServerTest() {
			Stadium newStadium = new Stadium();
			try {
				TicketServer.start(16789, 16790, newStadium);
			} catch (Exception e) {
				fail();
			}
			while(true){
				Random rand = new Random();
				int  n = rand.nextInt(50) + 1;
				TicketClient [] c = new TicketClient[n];												// Each TickentClient Object is a new request.
				for (int i = 0; i < n; i++){
				c[i] = new TicketClient("Random");
				}
				for (int i = 0; i <n; i++){
					c[i].requestTicket();
				}
			}		
		}
	


}
