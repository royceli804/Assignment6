package assignment6;

import java.util.ArrayList;

public class Stadium {
	static ArrayList<String> seats = new ArrayList<String>();
	Stadium(){
		int j = 115;
		 int k = 114;
		for(char i = 'A'; i <= 'Z'; i++){
			while(j<=128 && k>=101){
				String seatString = "" + i + k;
				seats.add(seatString);
				k--;
				String seatString2 = "" + i + j;
				seats.add(seatString2);
				j++;
			}
		}
	}
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
