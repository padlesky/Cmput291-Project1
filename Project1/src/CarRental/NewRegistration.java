package CarRental;

import java.io.*;
import java.sql.*;

public class NewRegistration {
	private static Db database;

	public static void newRegistration(String name, String maker, String model,
			int serial_no, int year, String color, Console cns)
			throws SQLException {

		cns.printf("\n\n\n" + "Welcome to the New Car Registration function");
		while (true) {
			String input = cns
					.readLine("Please enter the seial number of the car: ");
			if (input.equals("exit")) {
				break;
			}
			if (input.equals("")) {
				System.out.println("This is Empty.");
				input = cns
						.readLine("Please enter the seial number of the car: ");
			} else {
				try {
					ResultSet res = database
							.create_statement("SELECT serial_no FROM vihicle WHERE serial_no = '"
									+ input + "';");
					if (res.next()) {
						System.out.println("This car is already registed");
					} else {
						database.create_statement("INSERT INTO vihicle(serial_no)"
								+ "VALUES('" + input + "');");
						name = cns.readLine("Please enter the owner's name: ");
						database.create_statement("INSERT INTO people(name)"
								+ "VALUES('" + name + "');");
						maker = cns
								.readLine("Please enter the maker of the car: ");
						database.create_statement("INSERT INTO vihicle(maker)"
								+ "VALUES('" + maker + "');");
						model = cns
								.readLine("Please enter the model of the car: ");
						database.create_statement("INSERT INTO vihicle(model)"
								+ "VALUES('" + model + "');");
						String year_buff = cns
								.readLine("Please enter the year of the car: ");
						year = Integer.parseInt(year_buff);
						database.create_statement("INSERT INTO vihicle(year)"
								+ "VALUES('" + year + "');");
						color = cns
								.readLine("Please enter the color of the car: ");
						database.create_statement("INSERT INTO vihicle(color)"
								+ "VALUES('" + color + "');");

					}
				} catch (Exception ee) {

				}
			}
		}
	}
}