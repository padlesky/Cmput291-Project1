package CarRental;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Search {

	public static void search(Console cns) {
		
		DisplayMessage(cns);
		while(true) {
			String input = cns.readLine("Please enter a command: ");
			if (input.equals("exit")) {
				break;
			}
			if (input.equals("driverGN")){
				String givenName = cns.readLine("Please enter a name: ");
				if (TypeChecking.checkLetter(givenName)) {
					Db db = Db.getMeMyDBPlx();
					ArrayList<FirstSearchContainer> results = db.driverGN(givenName);
					DisplayFirstResults (cns, results, givenName);
				} else {
					cns.printf("Output conatined numbers. Please try again.\n");
				}
			}
			else if (input.equals("driverLN")){
				String licenceNo = cns.readLine("Please enter a licence number: ");
				Db db = Db.getMeMyDBPlx();
				ArrayList<FirstSearchContainer> results = db.driverLN(licenceNo);
				DisplayFirstResults (cns, results, licenceNo);
			}
			else if (input.equals("violation")) {
				String type = cns.readLine("Please specify if sin number ('sin') or licence number ('ln') will be given: ");
				if (type.equals("sin")) {
					String sin = cns.readLine("Please enter a sin number: ");
					Db db = Db.getMeMyDBPlx();
					ArrayList<SecondSearchContainer> results = db.violationSin(sin);
					DisplaySecondResults(cns, results, sin);
				}
				else if (type.equals("ln")) {
					String ln = cns.readLine("Please enter a licence number: ");
					Db db = Db.getMeMyDBPlx();
					ArrayList<SecondSearchContainer> results = db.violationLn(ln);
					DisplaySecondResults(cns, results, ln);
				} else {
					cns.printf("Did not specify the type of number to be given.\n");
				}
			}
			else if (input.equals("vehicle")) {
				String sn = cns.readLine("Please enter a vehicle serial number: ");
				Db db = Db.getMeMyDBPlx();
				ArrayList<ThirdSearchContainer> results = db.vehicleHistory(sn);
				DisplayThirdResults(cns, results, sn);
			}
			else if (input.equals("redisplay")) {
				DisplayMessage(cns);
			}
			else {
				cns.printf("No search word was inputed. For the list of search words again please enter 'redisplay'.\n");
			}
		}
	}
	
	
	public static void DisplayMessage(Console cns) {
		cns.printf("\n\n\n" + "Welcome to the search function, there are three\n" 
				+ "different types of searches you can do. Type 'exit' to exit.\n\n"
				+ "First is a search for driver by either inputing their given name\n"
				+ "or licence number. Type either 'driverGN' for a given name\n" 
				+ "or 'driverLN' for a licence number to initiate the respected\n"
				+ "type of search.\n\n"
				+ "Second is a search for violation records by driver.\n"
				+ "Type 'violation' to initiate the search.\n\n"
				+ "Third is a search on a vehicle, this returns relevant\n"
				+ "information about the vehicle. Type 'vehicle' to iniiate the search.\n\n");
	}
	
	public static void DisplayThirdResults(Console cns, ArrayList<ThirdSearchContainer> results, String searchInput) {
		if (results == null) {
			cns.printf("No results for %s were found\n", searchInput);
		} else {
			cns.printf("Results for %s are:\n\n", searchInput);
			cns.printf("Vehicle ID \t Number of Times Sold \t Average Price \t Total # Tickets Recieved \n");
			for (ThirdSearchContainer result: results) {
				cns.printf("%s \t %s \t %s \t %s \n", result.getVehicleId(), result.getNumberSales(), result.getAvgPrice(), result.getTotalTickets());
			}
		}
	}
	
	public static void DisplaySecondResults(Console cns, ArrayList<SecondSearchContainer> results, String searchInput) {
		if (results == null) {
			cns.printf("No results for %s were found\n", searchInput);
		} else {
			cns.printf("Results for %s are:\n\n", searchInput);
			cns.printf("Ticket# \t Sin# \t Vehicle# \t Officer# \t Type of Ticket \t Date of Ticket \t Place of Ticket \t Description \n");
			for (SecondSearchContainer result: results) {
				cns.printf("%s \t %s \t %s \t %s \t %s \t %s \t %s \t %s\n", result.getTicket_no().toString(), result.getViolator_no(),
								result.getVehicle_id(), result.getOffice_no(), result.getVtype(), result.getVdate().toString(),
								result.getPlace(), result.getDescriptions());
			}
		}
	}
	
	public static void DisplayFirstResults(Console cns, ArrayList<FirstSearchContainer> results, String searchInput) {
		if (results == null) {
			cns.printf("No results for %s were found\n", searchInput);
		} else {
			cns.printf("Results for %s are:\n\n", searchInput);
			cns.printf("Name \t Licence# \t Address \t Birthday \t Class \t Description \t Expiration Date \n");
			for (FirstSearchContainer result: results) {
				cns.printf("%s \t %s \t %s \t %s \t %s \t %s \t %s \n", result.getName(), result.getLicence_no(),
								result.getAddr(), result.getBirthday().toString(), result.getdClass(), result.getDescription(),
								result.getExpDate().toString());
			}
		}
	}
}
