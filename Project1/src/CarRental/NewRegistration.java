package CarRental;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class NewRegistration {

	public static void newRegistration(Console cns) throws SQLException {
		String name;
		String maker;
		String model;
		int serial_no;
		int year;
		String color;
		Db database = Db.getMeMyDBPlx();

		cns.printf("\n\n\n" + "Welcome to the New Car Registration function\n");
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
							.create_statement("SELECT serial_no FROM vehicle WHERE serial_no = '"
									+ input + "'");
					if (res.next()) {
						System.out.println("This car is already registed");
					} else {
						
						String sin = cns
								.readLine("Please enter the owner's sin: ");
						ResultSet check_sin = database
								.create_statement("SELECT sin FROM people WHERE sin = '"
										+ sin + "'");
						if (!(check_sin.next())) {
							name = cns
									.readLine("Please enter the owner's name: ");
							double height = Double.parseDouble (cns
									.readLine("Please enter the owner's height: "));
							double weight = Double.parseDouble (cns
									.readLine("Please enter the owner's weight: "));
							String eyecolor = cns
									.readLine("Please enter the owner's eye color: ");
							String haircolor = cns
									.readLine("Please enter the owner's hair color: ");
							String addr = cns
									.readLine("Please enter the owner's address: ");
							String gender = cns
									.readLine("Please enter the owner's gender (m/f): ");
							String birthday = cns.readLine("Please enter the owner's birthday (yyyy-mm-dd): ");
				
							database.create_statement("INSERT INTO people(sin, name, height, weight, eyecolor, haircolor, addr, gender, birthday)"
									+ " VALUES('" + sin + "','" + name + "','" + height + "','" + weight + "','" + eyecolor + "','" 
									+ haircolor + "','" + addr + "','" + gender + "', date '" + birthday + "')");
									
						}
							maker = cns
									.readLine("Please enter the maker of the car: ");
							model = cns
									.readLine("Please enter the model of the car: ");
							String year_buff = cns
									.readLine("Please enter the year of the car: ");
							year = Integer.parseInt(year_buff);
							color = cns
									.readLine("Please enter the color of the car: ");
							database.create_statement("INSERT INTO vehicle(serial_no, maker, model, year, color)"
									+ " VALUES('" + input +"','" + maker + "','" + model + "','" + year + "','" + color + "')");
							String is_primary=  cns.readLine("Is he/she a primary ower? ");
							
							System.out.println(is_primary);
							if (is_primary.toLowerCase().equals("yes")||is_primary.toLowerCase().equals("y")){
								
								is_primary="y";
								database.create_statement("INSERT INTO owner(owner_id,vehicle_id,is_primary_owner)"
										+"VALUES('" + sin + "','"+ input + "','"+is_primary+"')");
								
							}
							else if (is_primary.toLowerCase().equals("no")||is_primary.toLowerCase().equals("n")){
								is_primary="n";
								database.create_statement("INSERT INTO owner(owner_id,vehicle_id,is_primary_owner)"
										+"VALUES('" + sin + "','"+ input + "','"+is_primary+"')");
								
							}
							else{
								System.out.println("The input is invalid");
								is_primary=  cns.readLine("Is he/she a primary ower? ");
							}
							
					}
				} catch (Exception ee) {
					ee.printStackTrace();
				}

			}
		}
	}
}