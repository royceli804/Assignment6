package assignment6;

import java.util.ArrayList;

public class Stadium {
	static ArrayList<String> seats = new ArrayList<String>();
	Stadium(){
		for(char i = 'A'; i <= 'Z'; i++){
			for(int j = 101; j <= 128; j++){
				String seatString = "" + i + j;
				seats.add(seatString);
			}
		}
	}
	public String bestAvailableSeat(){
		String bestSeat = seats.get(0);
		seats.remove(0);
		return "You have been assigned Seat: " + bestSeat;
		
	}
	void markAvailableSeat(){
		
	}
	void printTicketSeat(){
		
	}
}
