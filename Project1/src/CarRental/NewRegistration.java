package CarRental;

import java.io.Console;
import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class NewRegistration {

	public static void newRegistration(Console cns) throws SQLException {
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

		cns.printf("\n\n\n" + "Welcome to the New Car Registration function\n");
		while (a=true) {
			String input = cns
					.readLine("Please enter the seial number of the car: ");
			if (input.equals("exit")) {
				break;
			}
			if (input.equals("")) {
				System.out.println("This is Empty.");
				//input = cns
					//	.readLine("Please enter the seial number of the car: ");
			} else {
				try {
					String is_primary = cns
							.readLine("Are u a primary owner?");
					if (is_primary.toLowerCase().equals("yes")||is_primary.toLowerCase().equals("y")){
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
							else {
								System.out.println("Registration has been completed.");

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
								
								
								type= getQueryResult("select type from vehicle_type");//cns.readLine();
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
							
								is_primary="y";
								database.create_statement("INSERT INTO owner(owner_id,vehicle_id,is_primary_owner)"
											+"VALUES('" + sin + "','"+ input + "','"+is_primary+"')");	
								}
					}				
							else if (is_primary.toLowerCase().equals("no")||is_primary.toLowerCase().equals("n")){
								ResultSet res = database
										.create_statement("SELECT serial_no FROM vehicle WHERE serial_no = '"
												+ input + "'");
								if (res.next()) {
									
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
								else{
									System.out.println("Registration has been completed.");
								}
									is_primary="n";
									database.create_statement("INSERT INTO owner(owner_id,vehicle_id,is_primary_owner)"
										+"VALUES('" + sin + "','"+ input + "','"+is_primary+"')");
								
							}
								else{
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
												+ haircolor + "','" + addr + "','" + gender + "', date '" + birthday + "')");}
									maker = cns
											.readLine("Please enter the maker of the car: ");
									model = cns
											.readLine("Please enter the model of the car: ");
									String year_buff = cns
											.readLine("Please enter the year of the car: ");
									year = Integer.parseInt(year_buff);
									color = cns
											.readLine("Please enter the color of the car: ");
									
									
									
									
									
									
									type= getQueryResult("select type from vehicle_type");//cns.readLine();
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
									database.create_statement("INSERT INTO owner(owner_id,vehicle_id,is_primary_owner)"
											+"VALUES('" + sin + "','"+ input + "','"+is_primary+"')");
								}
								
							/*else{
								System.out.println("The input is invalid");
								is_primary=  cns.readLine("Is he/she a primary ower? ");
								
							}*/
							
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
	public static ArrayList<ArrayList<String>> getQueryResult(String query){

        ArrayList<ArrayList<String>> feedback = new ArrayList<ArrayList<String>>();
        ArrayList<String> feed = null;
		Db database = Db.getMeMyDBPlx();
        try {
            ResultSet rs = database.create_statement(query);

            ResultSetMetaData rsm = rs.getMetaData();
                feed = new ArrayList<String>();

                for(int y = 1;y<rsm.getColumnCount();y++){

                    feed.add(rsm.getColumnName(y));
                }
                feedback.add(feed);

            while(rs.next()){
                feed = new ArrayList<String>();
            for(int i=1;i<=rsm.getColumnCount();i++){

                    feed.add(rs.getString(i));
            }
            feedback.add(feed);
        }



        } catch (SQLException e) {
            //handler
        }
        return feedback;

    }
}