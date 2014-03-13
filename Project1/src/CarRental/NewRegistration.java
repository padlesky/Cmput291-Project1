package CarRental;

import java.io.*;
import java.sql.*;

public class NewRegistration {
	public static void newRegistration(String name, String maker, String model,
			int serial_no, int year, String color, Console cns) {
		cns.printf("\n\n\n" + "Welcome to the New Car Registration function");
		while (true) {
			String input = cns
					.readLine("Please enter the seial number of the car: ");
			if (input.equals("exit")) {
				break;
			}
			
		}
	}
}