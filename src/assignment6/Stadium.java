package assignment6;

import java.util.ArrayList;

public class Stadium {
	Stadium(){
		ArrayList<String> seats = new ArrayList<String>();
		for(char i = 'a'; i <= 'z'; i++){
			for(int j = 101; j <= 128; j++){
				String seatString = "" + i + j;
				seats.add(seatString);
			}
		}
	}
	void bestAvailableSeat(){
		
	}
	void markAvailableSeat(){
		
	}
	void printTicketSeat(){
		
	}
}
