package CarRental;

import java.io.Console;
import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class VRecord {

	public static void VRecord(Console cns) throws SQLException {

		Db database = Db.getMeMyDBPlx();
		Boolean a;
		ArrayList<ArrayList<String>> vtype;
		ArrayList<String> ab;
		String abc;
		
		cns.printf("\n\nWelcome to the Violatin Record function\n");
		while (a=true) {
			String ticket_no = cns.readLine("Please enter the ticket number: ");
			if (ticket_no.equals("exit")) {
				break;
			}
			if (ticket_no.equals("")) {
				System.out.println("This is Empty.");
			} else {
				try {
					ResultSet check_ticket = database.create_statement("SELECT ticket_no FROM ticket WHERE ticket_no = '" + ticket_no + "'");
						if (check_ticket.next()) {
							System.out.println("This ticket is already entered");
						} else {
													
							String violator_no = cns.readLine("Please enter the violator's number: ");
							//check if the violator number is valid or not
							ResultSet check_violator = database.create_statement("SELECT sin FROM people WHERE sin = '" + violator_no + "'");
							while (!(check_violator.next())) {
								System.out.println("Invaid violator number.\n");
								check_violator = database.create_statement("SELECT sin FROM people WHERE sin = '" + violator_no + "'");
							}
							
							String vehicle_id = cns.readLine("Please enter the violator's number: ");
							//check if the vehicle id is valid or not
							ResultSet check_vehicle = database.create_statement("SELECT serial_no FROM vehicle WHERE serial_no = '" + vehicle_id + "'");
							while (!(check_vehicle.next())) {
								System.out.println("Invaid vehicle id.\n");
								check_vehicle = database.create_statement("SELECT serial_no FROM vehicle WHERE serial_no = '" + vehicle_id + "'");
							}
							
							String office_no = cns.readLine("Please enter the officer's number: ");
							//check if the officer number is valid or not
							ResultSet check_office = database.create_statement("SELECT sin FROM people WHERE sin = '" + office_no + "'");
							while (!(check_office.next())) {
								System.out.println("Invaid officer number.\n");
								check_office = database.create_statement("SELECT sin FROM people WHERE sin = '" + office_no + "'");
							}
							while (violator_no.equals(office_no)){
								System.out.println("Invaid officer number.\n");
								office_no = cns.readLine("Please enter the officer's number: ");
							}
								
							
							vtype= getQueryResult("select vtype from ticket_type");
							System.out.print("Here are all the violation tyes,\n");
								
							for (int i=1;i<vtype.size();i++){
								ab=vtype.get(i);
								for (int j=0;j<ab.size();j++){
									abc = ab.get(j);
									System.out.print(abc+"\n");
								}
							}
								
							String choice=cns.readLine("Please enter the appropriate type.");
								
							ResultSet type = database.create_statement("select vtype from ticket_type where vtype = '" + choice + "'");
							while (!(type.next())){
								vtype= getQueryResult("select vtype from ticket_type");
								System.out.print("Here are all the violation tyes,\n");
								
								for (int i=1;i<vtype.size();i++){
									ab=vtype.get(i);
									for (int j=0;j<ab.size();j++){
										abc = ab.get(j);
										System.out.print(abc+"\n");
									}
								}
								System.out.println("The input is invalid");
								choice=cns.readLine("Please enter the appropriate type.");
								
								type = database.create_statement("select v_type from ticket_type where v_type = '" + choice + "'");
							}
							
							String vdate = cns.readLine("Please enter the violation date.");
							String place = cns.readLine("Please enter where the violation took place.");
							String descriptions = cns.readLine("Please enter the violation description.");
								
							database.create_statement("INSERT INTO ticket(ticket_no, violateor_no, vehicle_id, office_no, vtype, vdate, place, descriptios)"
									+ " VALUES('" + ticket_no +"','" + violator_no + "','" + vehicle_id + "','" + office_no + "','"
									+ choice + "', date'" + vdate + "', '" + place + "', '" + descriptions + "')");
						}
									
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