package CarRental;

import java.io.*;

public class Search {

	public static void search(String m_username, String m_password, Console cns) {
		cns.printf("\n\n\n" + "Welcome to the search function, there are three\n" 
					+ "different types of searches you can do. Type 'exit' to exit.\n\n"
					+ "First is a search for driver by either inputing their given name\n"
					+ "or licence number. Type either 'driverGN' for a given name\n" 
					+ "or 'driverLN' for a licence number to initiate the respected\n"
					+ "type of search.\n\n"
					+ "Second is a search for violation records by driver.\n"
					+ "Type 'violation' to initiate the search.\n\n"
					+ "Third is a search on a vehicle, this returns relevant\n"
					+ "information about the vehicle. Type 'vehicle' o iniiate the search.\n\n");
		while(true) {
			String input = cns.readLine("Please enter a command: ");
			if (input.equals("exit")) {
				break;
			}
		}
	}
}
