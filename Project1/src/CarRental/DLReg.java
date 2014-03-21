package CarRental;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DLReg {
	private static Connection conn = null;

	public static void DLReg(Console cns) throws SQLException {
		
		Db database = Db.getMeMyDBPlx();
		SimpleDateFormat date = new SimpleDateFormat("yyy-mm-dd");
		Boolean a;

		cns.printf("\n\nWelcome to the Driver Licence Registration function.\n");
		while (a = true) {
			//read the licence number
			String licence_no = cns.readLine("Please enter the licence number of the driver: ");
			// check the input
			if (licence_no.equals("exit")) {
				break;
			}
			if (licence_no.equals("")) {
				System.out.println("This is Empty.");
			} else {
				try {
					//check if the licence number being registered or not
					ResultSet check_dl = database.create_statement("SELECT licence_no FROM drive_licence WHERE licence_no = '" + licence_no + "'");
					if (check_dl.next()) {
						System.out.println("This licence number is already registed");
					} else {
						//read and check the sin of the driver exist in the database or not
						String sin = cns.readLine("Please enter the driver's sin: ");
						ResultSet check_sin = database.create_statement("SELECT sin FROM people WHERE sin = '" + sin + "'");
						
						if(check_sin.next()) {
							//if he/she exists in the database, continue on entering the other information for a dirver licence
							String dclass = cns.readLine("Please enter the class of the driver belongs: ");
								
							String i_date = cns.readLine("Please enter the issuing date (yyyy-mm-dd): ");
							//check is the issuing date valid or not
							ResultSet birth = database.create_statement("SELECT birthday FROM people WHERE sin = '" + sin + "'");
							String abc = "";
							if(birth.next()){
								abc = birth.getString(1);
							}
							System.out.println(abc);
							Date bday = date.parse(abc);
							Date issuing = date.parse(i_date);
							
							while(bday.after(issuing)){
								System.out.println("Invaild issuing date.");
								i_date = cns.readLine("Please enter the issuing date (yyyy-mm-dd): ");
								issuing = date.parse(i_date);
							}
							
							String e_date = cns.readLine("Please enter the expiring date (yyyy-mm-dd): ");
							//check is the expiring date valid or not
							Date expiring = date.parse(e_date);
							
							while(expiring.before(issuing)){
								System.out.println("Invaild expiring date.");
								e_date = cns.readLine("Please enter the expiring date (yyyy-mm-dd): ");
								expiring = date.parse(e_date);
							}
							
							//need to import a pic of the driver!!!
							PreparedStatement stmt = conn.prepareStatement("INSERT INTO drive_licence(licence_no, sin, class, photo, issuing_date, expiring_date)"
									+ " VALUES('" + licence_no + "','" + sin + "','" + dclass + "', ?, date '" + i_date + "', date '" + e_date + "')");
									//"insert into pictures values (" + pid + ", '" + title+ "', '" + place + "', ?)" );

						    stmt.clearParameters();
						    // Set the first parameter 
						    String pic = cns.readLine("Please select the photo to be inserted to the licence.");
						    File file = new File( pic );
						    System.out.println("the file length is " + file.length() );

						    stmt.setBinaryStream(1,new FileInputStream(file),(int)file.length());

						    // execute the insert statement
						    stmt.executeUpdate();
						    System.out.println( "the execution succeeds");
					
//							//insert the given information to drive_licence 
//							database.create_statement("INSERT INTO drive_licence(licence_no, sin, class, issuing_date, expiring_date)"
//									+ " VALUES('" + licence_no + "','" + sin + "','" + dclass + "',"+/*'" + picture + "', */"date '" + i_date + "', date '" + e_date + "')");
							}
							else{
								//if he/she does not exists in people, ask for his/her personal information
								String name = cns.readLine("Please enter the owner's name: ");
								double height = Double.parseDouble (cns.readLine("Please enter the owner's height: "));
								double weight = Double.parseDouble (cns.readLine("Please enter the owner's weight: "));
								String eyecolor = cns.readLine("Please enter the owner's eye color: ");
								String haircolor = cns.readLine("Please enter the owner's hair color: ");
								String addr = cns.readLine("Please enter the owner's address: ");
								String gender = cns.readLine("Please enter the owner's gender (m/f): ");
								String birthday = cns.readLine("Please enter the owner's birthday (yyyy-mm-dd): ");
					
								//insert he/she into people, then ask for the information needed for driver licence
								database.create_statement("INSERT INTO people(sin, name, height, weight, eyecolor, haircolor, addr, gender, birthday)"
										+ " VALUES('" + sin + "','" + name + "','" + height + "','" + weight + "','" + eyecolor + "','" 
										+ haircolor + "','" + addr + "','" + gender + "', date '" + birthday + "')");
								
								String dclass = cns.readLine("Please enter the class of the driver belongs: ");
															
								String i_date = cns.readLine("Please enter the issuing date (yyyy-mm-dd): ");
								//check the issuing date is valid or not
								Date issuing = date.parse(i_date);
								Date bday = date.parse(birthday);
								while(bday.after(issuing)){
									System.out.println("Invaild issuing date.");
									i_date = cns.readLine("Please enter the issuing date (yyyy-mm-dd): ");
									issuing = date.parse(i_date);
								}
								
								String e_date = cns.readLine("Please enter the expiring date (yyyy-mm-dd): ");
								//check the expiring date is valid or not
								Date expiring = date.parse(e_date);
								while(expiring.before(issuing)){
									System.out.println("Invaild expiring date.");
									e_date = cns.readLine("Please enter the expiring date (yyyy-mm-dd): ");
									expiring = date.parse(e_date);
								}
								
								//need to import a pic of the driver!!!
								PreparedStatement stmt = conn.prepareStatement("INSERT INTO drive_licence(licence_no, sin, class, photo, issuing_date, expiring_date)"
										+ " VALUES('" + licence_no + "','" + sin + "','" + dclass + "', ?, date '" + i_date + "', date '" + e_date + "')");
										//"insert into pictures values (" + pid + ", '" + title+ "', '" + place + "', ?)" );

							    stmt.clearParameters();
							    // Set the first parameter 
							    String pic = cns.readLine("Please select the photo to be inserted to the licence.");
							    File file = new File( pic );
							    System.out.println("the file length is " + file.length() );

							    stmt.setBinaryStream(1,new FileInputStream(file),(int)file.length());

							    // execute the insert statement
							    stmt.executeUpdate();
							    System.out.println( "the execution succeeds");
							    
								
//								//insert the given information into drive_licence
//								database.create_statement("INSERT INTO drive_licence(licence_no, sin, class, issuing_date, expiring_date)"
//										+ " VALUES('" + licence_no + "','" + sin + "','" + dclass + "',"+/*'" + picture + "', */"date '" + i_date + "', date '" + e_date + "')");
						}
						//Registeration complete
						System.out.println("Registeration completed.");
						
						//Ask if the user continue entering
						String next_op = cns.readLine("Would you like to do another operation?");
						while(next_op != null){
							if(next_op.toLowerCase()=="yes" || next_op.toLowerCase()=="y"){
								a = true;
							}
							else if(next_op.toLowerCase()=="no" || next_op.toLowerCase()=="n"){
								a = false;
								break;
							}
							else{
								System.out.println("Invalid input.");
								next_op = cns.readLine("Would you like to do another operation?");
							}
						}
						
						
					}
							
					
				} catch (Exception ee) {
					ee.printStackTrace();
				}

			}
		}
	}
}