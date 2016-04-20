package assignment6;

import java.util.ArrayList;

public class Stadium {
	static ArrayList<String> seats = new ArrayList<String>();
	Stadium(){
		for(char i = 'a'; i <= 'z'; i++){
			for(int j = 101; j <= 128; j++){
				String seatString = "" + i + j;
				seats.add(seatString);
			}
		}
	}
	public String bestAvailableSeat(){
		String bestSeat = seats.get(0);
		seats.remove(0);
		return bestSeat;
		
	}
	void markAvailableSeat(){
		
	}
	void printTicketSeat(){
		
	}
}
