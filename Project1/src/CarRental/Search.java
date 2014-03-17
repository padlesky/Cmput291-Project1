package CarRental;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

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
			if (input.equals("driverGN")){
				String givenName = cns.readLine("Please enter a name: ");
				Db db = new Db(m_username, m_password);
				ArrayList<FirstSearchContainer> results = db.driverGN(givenName);
				if (results == null) {
					cns.printf("No results for %s were found\n", givenName);
				} else {
					cns.printf("Results for %s are:\n\n", input);
					cns.printf("Name \t Licence# \t Address \t Birthday \t Class \t Description \t Expiration Date \n");
					for (FirstSearchContainer result: results) {
						cns.printf("%s \t %s \t %s \t %s \t %s \t %s \t %s \n", result.getName(), result.getLicence_no(),
										result.getAddr(), result.getBirthday().toString(), result.getdClass(), result.getDescription(),
										result.getExpDate().toString());
					}
				}
			}
		}
	}
}
