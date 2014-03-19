package CarRental;

import java.io.*;
import java.sql.*;

public class DLReg {

	public static void DLReg(Console cns) throws SQLException {
		
		Db database = Db.getMeMyDBPlx();

		cns.printf("\n\nWelcome to the Driver Licence Registration function.\n");
		while (true) {
			String licence_no = cns
					.readLine("Please enter the licence number of the driver: ");
			if (licence_no.equals("exit")) {
				break;
			}
			if (licence_no.equals("")) {
				System.out.println("This is Empty.");
				licence_no = cns
						.readLine("Please enter the licence number of the driver: ");
			} else {
				try {
					ResultSet res = database
							.create_statement("SELECT licence_no FROM drive_licence WHERE licence_no = '"
									+ licence_no + "'");
					if (res.next()) {
						System.out.println("This driver is already registed");
					} else {
						
						String sin = cns.readLine("Please enter the driver's sin: ");
						ResultSet check_sin = database.create_statement("SELECT sin FROM people WHERE sin = '" + sin + "'");
						
						if (check_sin.next()) {
							String dclass = cns.readLine("Please enter the class of the driver belongs: ");
							
							//need to import a pic of the driver!!!
//							PreparedStatement stmt = conn.prepareStatement(
//					                "insert into pictures values (" + pid + ", '" + title+ "', '" + place + "', ?)" );
//
//						    stmt.clearParameters();
//					            // Set the first parameter 
//					            File file = new File( file1 );
//						    System.out.println("the file length is " + file.length() );
//
//					          stmt.setBinaryStream(1,new FileInputStream(file),(int)file.length());
//
//					            // execute the insert statement
//					            stmt.executeUpdate();
//					            System.out.println( "the execution succeeds");
						    
							
							String i_date = cns.readLine("Please enter the issuing date (yyyy-mm-dd): ");
							String e_date = cns.readLine("Please enter the expiring date (yyyy-mm-dd): ");
				
							database.create_statement("INSERT INTO drive_licence(licence_no, sin, class, photo, issuing_date, expiring_date)"
									+ " VALUES('" + licence_no + "','" + sin + "','" + dclass + "','" + /*picture +*/ "', date '" + i_date + "','" + "', date '" + e_date + "')");
						}
						else{
							System.out.println("This sin is not exist.");
							sin = cns.readLine("Please enter the driver's sin: ");
						}
							
					}
				} catch (Exception ee) {
					ee.printStackTrace();
				}

			}
		}
	}
}