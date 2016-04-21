/*Assignment6 EE 422C
 * Aftab Hadimohd ah35368
 * Royce Li rl26589
*/
package assignment6;

import java.util.ArrayList;

public class Stadium {
	static ArrayList<String> seats = new ArrayList<String>();
	Stadium(){
		int j = 115; //middle seats are best available
		 int k = 114; //middle seats are best available
		for(char i = 'A'; i <= 'Z'; i++){
			while(j<=128 && k>=101){
				String seatString = "" + i + k; 
				seats.add(seatString);
				k--;
				String seatString2 = "" + i + j;
				seats.add(seatString2);
				j++;
			}//goes from seat 115-128 and 114-101
		}
	}
	/******************************************************************************
	* Method Name: bestAvailableSeat                                              *
	* Purpose: Gets the next available seat from the arraylist of seats based on  *
	* which seat is best and is still available. If no seats are available, prints*
	* message to the console that tickets are sold out                     		  *
	******************************************************************************/
	public synchronized String bestAvailableSeat(){
		try{
		String bestSeat = seats.get(0);
		seats.remove(0);
		return "You have been assigned Seat: " + bestSeat;
		}
		catch (IndexOutOfBoundsException e){
			System.out.println("Sorry Tickets Are Sold Out!");
			System.exit(0);
		}
		return null;
	}
}
