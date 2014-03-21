package CarRental;

import java.io.Console;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Auto_Transaction {

	/**
	 * @param args
	 */
	public static void Auto_Transaction(Console cns) throws SQLException {
		// TODO Auto-generated method stub
		String name;
		String maker;
		String model;
		int serial_no;
		boolean a;
		int year;
		ArrayList<ArrayList<String>> type;
		ArrayList<String> ab;
		String abc;

		String color;
		Db database = Db.getMeMyDBPlx();
		NewRegistration nr = new NewRegistration();

		cns.printf("\n\n\n" + "Welcome to the Auto Transaction function\n");
		while (a = true) {
			String sin = cns.readLine("Please enter the seller's sin: ");
			String input = cns
					.readLine("Please enter the seial number of the car: ");
			ResultSet res = database
					.create_statement("SELECT serial_no FROM vehicle WHERE serial_no = '"
							+ input + "'");
			if (sin.equals("exit")) {
				break;
			}
			if (sin.equals("")) {
				System.out.println("This is Empty.");
				// input = cns
				// .readLine("Please enter the seial number of the car: ");
			} else {
				try {
					ResultSet check_sin = database
							.create_statement("select owner_id, is_primary_owner from owner where IS_PRIMARY_owner = 'y' and owner_id= '"
									+ sin + "' ");
					if (check_sin.next()) {
						String buyer_sin = cns.readLine("Please enter the buyer's sin: ");
						ResultSet check_buyer_sin = database
								.create_statement("select sin from people where sin= '"
										+ buyer_sin + "'");
						String selling_date = cns.readLine("please enter the selling date:");
						String price1 = cns.readLine("Please enter the sell price:");
						double price = Double.parseDouble(price1); 
						if(!(check_buyer_sin.next())){
							name = cns
									.readLine("Please enter the buyer's name: ");
							double height = Double.parseDouble (cns
									.readLine("Please enter the buyer's height: "));
							double weight = Double.parseDouble (cns
									.readLine("Please enter the buyer's weight: "));
							String eyecolor = cns
									.readLine("Please enter the buyer's eye color: ");
							String haircolor = cns
									.readLine("Please enter the buyer's hair color: ");
							String addr = cns
									.readLine("Please enter the buyer's address: ");
							String gender = cns
									.readLine("Please enter the buyer's gender (m/f): ");
							String birthday = cns.readLine("Please enter the buyer's birthday (yyyy-mm-dd): ");
				
							database.create_statement("INSERT INTO people(sin, name, height, weight, eyecolor, haircolor, addr, gender, birthday)"
									+ " VALUES('" + buyer_sin + "','" + name + "','" + height + "','" + weight + "','" + eyecolor + "','" 
									+ haircolor + "','" + addr + "','" + gender + "', date '" + birthday + "')");
							
														
							

							if (!(res.next())) {
								//System.out.println("This car is not registed, transaction cancelled.\n");
								
								System.out.print("This is a new car is not registed, so we can do this here.\n");
								maker = cns
										.readLine("Please enter the maker of the car: ");
								model = cns
										.readLine("Please enter the model of the car: ");
								String year_buff = cns
										.readLine("Please enter the year of the car: ");
								year = Integer.parseInt(year_buff);
								color = cns
										.readLine("Please enter the color of the car: ");
								
								
								type= nr.getQueryResult("select type from vehicle_type");//cns.readLine();
								System.out.print("Here are all the type u can choice from, please choice from the following(Enter the index of your choice)\n");
								
								for (int i=1;i<type.size();i++){
									ab=type.get(i);
									for (int j=0;j<ab.size();j++){
										abc = ab.get(j);
										System.out.print(i +" : "+abc+"\n");
									}
								}
								
								String choice_type=cns.readLine("you choice: ");
								
								ResultSet type1 = database.create_statement("select type_id from vehicle_type where type_id = '"+choice_type+"'");
								
								while (!(type1.next())){
									
								System.out.println("The input is invalid");
								choice_type=cns.readLine("choice the type: ");
								type1 = database.create_statement("select type_id from vehicle_type where type_id = '"+choice_type+"'");
								}
								database.create_statement("INSERT INTO vehicle(serial_no, maker, model, year, color,type_id)"
										+ " VALUES('" + input +"','" + maker + "','" + model + "','" + year + "','" + color + "','"+choice_type+"')");
								String is_primary = "y"; 
								database.create_statement("insert into owner (owner_id,vehicle_id,is_primary_owner)"
										+ " VALUES('" +sin+"','" +input+"','" +is_primary+ "')");
	
								
								}
							
							
							database.create_statement("delete from owner where vehicle_id = '" +input+"' ");
							
							String is_primary = "y";
							database.create_statement("insert into owner (owner_id,vehicle_id,is_primary_owner)"
									+ " VALUES('" +buyer_sin+"','" +input+"','" +is_primary+ "')");
							System.out.print("Transacation completed\n");
							int length =count_row();
							database.create_statement("insert into auto_sale(transaction_id,seller_id,buyer_id,vehicle_id,s_date,price)"
									+" values('" + length + "','" + sin + "','" + buyer_sin + "','" + input + "',date'" + selling_date + "','" 
											+ price + "')");
							
							
							}
						else{

							if (!(res.next())) {
								//System.out.println("This car is not registed, transaction cancelled.\n");
								
								System.out.print("This is a new car is not registed, so we can do this here.\n");
								maker = cns
										.readLine("Please enter the maker of the car: ");
								model = cns
										.readLine("Please enter the model of the car: ");
								String year_buff = cns
										.readLine("Please enter the year of the car: ");
								year = Integer.parseInt(year_buff);
								color = cns
										.readLine("Please enter the color of the car: ");
								
								
								type= nr.getQueryResult("select type from vehicle_type");//cns.readLine();
								System.out.print("Here are all the type u can choice from, please choice from the following(Enter the index of your choice)\n");
								
								for (int i=1;i<type.size();i++){
									ab=type.get(i);
									for (int j=0;j<ab.size();j++){
										abc = ab.get(j);
										System.out.print(i +" : "+abc+"\n");
									}
								}
								
								String choice_type=cns.readLine("you choice: ");
								
								ResultSet type1 = database.create_statement("select type_id from vehicle_type where type_id = '"+choice_type+"'");
								
								while (!(type1.next())){
									
								System.out.println("The input is invalid");
								choice_type=cns.readLine("choice the type: ");
								type1 = database.create_statement("select type_id from vehicle_type where type_id = '"+choice_type+"'");
								}
								database.create_statement("INSERT INTO vehicle(serial_no, maker, model, year, color,type_id)"
										+ " VALUES('" + input +"','" + maker + "','" + model + "','" + year + "','" + color + "','"+choice_type+"')");
								String is_primary = "y"; 
								database.create_statement("insert into owner (owner_id,vehicle_id,is_primary_owner)"
										+ " VALUES('" +sin+"','" +input+"','" +is_primary+ "')");
								}
							
							
							database.create_statement("delete from owner where vehicle_id = '" +input+"' ");
							
							String is_primary = "y";
							System.out.println("insert into owner (owner_id,vehicle_id,is_primary_owner)"
									+ " VALUES('" +buyer_sin+"','" +input+"','" +is_primary+ "')");
							database.create_statement("insert into owner (owner_id,vehicle_id,is_primary_owner)"
									+ " VALUES('" +buyer_sin+"','" +input+"','" +is_primary+ "')");
							
							int length =count_row();
							database.create_statement("insert into auto_sale(transaction_id,seller_id,buyer_id,vehicle_id,s_date,price)"
									+" values('" + length + "','" + sin + "','" + buyer_sin + "','" + input + "',date'" + selling_date + "','" 
											+ price + "')");
							
							System.out.print("Transacation completed\n");
						}
					} else {
											
						System.out.print("You dont own a vehicle, Transacation canceled\n");
						break;
					}
					String is_end= cns.readLine("Wouild like do more opreation?");
					if (is_end.toLowerCase().equals("yes")||is_end.toLowerCase().equals("y")){
						a=true;
					}
					else if(is_end.toLowerCase().equals("no")||is_end.toLowerCase().equals("n")){
						a=false;
						break;
					}
					else
					{
						is_end= cns.readLine("Invaid input.\nWouild like do more opreation");
					}
				} catch (Exception ee) {
					ee.printStackTrace();
					}

				}
			}
		}
	
	public static int count_row() throws SQLException{
		Db database = Db.getMeMyDBPlx();
		ResultSet statement = database.create_statement("Select count(*) from auto_sale");
		int number=0;
		if (statement.next()){
			number =statement.getInt(1);
		}
		
		return number+1;
	}
	
}
