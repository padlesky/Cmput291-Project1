package CarRental;

import java.sql.*;
import java.util.ArrayList;
import java.io.*;

public class Db {

	private Connection con = null;
	private static String m_url = "dbc:oracle:thin:@gwynne.cs.ualberta.ca:1521:CRS";
	private static String m_driverName = "oracle.jdbc.driver.OracleDriver";
	private static Db instance;

	public static Db getMeMyDBPlx() {
		if (instance == null) {
			instance = new Db();
		}
		return instance;
	}
	
	
	/**
	 * Instantiating a Db object will perform connection
	 * @return 
	 */
	public void init(String m_username, String m_password) {
		try {
			Class drvClass = Class.forName(m_driverName);
			DriverManager.registerDriver((Driver) drvClass.newInstance());
			this.con = DriverManager.getConnection(m_url, m_username,
					m_password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet performQuery(String stmt) {
		Statement st = null;
		try {
			st = this.con.createStatement();
			ResultSet rs= st.executeQuery(stmt);
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ResultSet create_statement(String statement) throws SQLException{
		Statement s = con.createStatement();
		ResultSet vihcile = s.executeQuery(statement);
		return vihcile;
	}
	
	
	public ArrayList<FirstSearchContainer> driverGN(String givenName) {
		String stm  = "SELECT ppl.name name, dl.licence_no ln, ppl.addr addr, ppl.birthday bd, "
				+ "dl.class class, dc.description des, dl.expiring_date ed "
				+ "FROM drive_licence dl, people ppl, restriction rst, driving_condition dc "
				+ "WHERE dl.sin = ppl.sin AND dl.licence_no = rst.licence_no "
				+ "AND rst.r_id = dc.c_id";
		ResultSet rs = performQuery(stm);
		ArrayList<FirstSearchContainer> fSCList = new ArrayList<FirstSearchContainer>();
		try {
			while (rs.next()) {
				if (rs.getString("name").equalsIgnoreCase(givenName)) {
					FirstSearchContainer result = new FirstSearchContainer (rs.getString("name"), rs.getString("ln"), 
																			rs.getString("addr"), rs.getDate("bd"), rs.getString("class"), 
																			rs.getString("des"), rs.getDate("ed"));
					fSCList.add(result);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (fSCList.size() == 0) {
			return null;
		} else {
			return fSCList;
		}
	}
	
	public ArrayList<FirstSearchContainer> driverLN(String licence_no) {
		String stm  = "SELECT ppl.name name, dl.licence_no ln, ppl.addr addr, ppl.birthday bd, "
				+ "dl.class class, dc.description des, dl.expiring_date ed "
				+ "FROM drive_licence dl, people ppl, restriction rst, driving_condition dc "
				+ "WHERE dl.sin = ppl.sin AND dl.licence_no = rst.licence_no "
				+ "AND rst.r_id = dc.c_id AND dl.licence_no = '" + licence_no + "'";
		ResultSet rs = performQuery(stm);
		ArrayList<FirstSearchContainer> fSCList = new ArrayList<FirstSearchContainer>();
		FirstSearchContainer result = null;
		try {
			while (rs.next()) {
				result = new FirstSearchContainer (rs.getString("name"), rs.getString("ln"), 
														rs.getString("addr"), rs.getDate("bd"), rs.getString("class"), 
														rs.getString("des"), rs.getDate("ed"));
				fSCList.add(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fSCList;
	}


	public ArrayList<SecondSearchContainer> violationSin(String sin) {
		String stm = "SELECT * FROM ticket WHERE ticket.violator_no = '" + sin + "'";
		ResultSet rs = performQuery(stm);
		ArrayList<SecondSearchContainer> sSCList = new ArrayList<SecondSearchContainer>();
		try {
			while (rs.next()) {
				SecondSearchContainer result = new SecondSearchContainer (rs.getInt("ticket_no"), rs.getString("violator_no"),
															rs.getString("vehicle_id"), rs.getString("office_no"), rs.getString("vtype"),
															rs.getDate("vdate").toString(), rs.getString("place"), rs.getString("descriptions"));
				sSCList.add(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (sSCList.size() == 0) {
			return null;
		} else {
			return sSCList;
		}
	}
	
	public ArrayList<SecondSearchContainer> violationLn(String ln) {
		String stm = "SELECT t.ticket_no, t.violator_no, t.vehicle_id, t.office_no, t.vtype, t.vdate, t.place, t.descriptions FROM ticket t, people p, " +
				"drive_licence dl WHERE t.violator_no = p.sin AND p.sin = dl.sin AND dl.licence_no = '" + ln + "'";
		ResultSet rs = performQuery(stm);
		ArrayList<SecondSearchContainer> sSCList = new ArrayList<SecondSearchContainer>();
		try {
			while (rs.next()) {
				SecondSearchContainer result = new SecondSearchContainer (rs.getInt("ticket_no"), rs.getString("violator_no"),
															rs.getString("vehicle_id"), rs.getString("office_no"), rs.getString("vtype"),
															rs.getDate("vdate").toString(), rs.getString("place"), rs.getString("descriptions"));
				sSCList.add(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (sSCList.size() == 0) {;
			return null;
		} else {
			return sSCList;
		}
	}


	public ArrayList<ThirdSearchContainer> vehicleHistory(String sn) {
		String stm = "SELECT  h.serial_no vehicle_no, count(DISTINCT transaction_id) number_sales, avg(price) avg_price, count(DISTINCT t.ticket_no) total_tickets " +
				"FROM vehicle h, auto_sale a, ticket t WHERE t.vehicle_id (+) = h.serial_no AND a.vehicle_id (+) = h.serial_no GROUP BY h.serial_no";
		ResultSet rs = performQuery(stm);
		ArrayList<ThirdSearchContainer> tSCList = new ArrayList<ThirdSearchContainer>();
		try {
			while (rs.next()) {
				ThirdSearchContainer result = new ThirdSearchContainer (rs.getString("vehicle_no"), rs.getString("number_sales"), rs.getString("avg_price"), rs.getString("total_tickets"));
				tSCList.add(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (tSCList.size() == 0) {;
			return null;
		} else {
			return tSCList;
		}
	}
}
