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
		String stm  = "SELECT ppl.name name, dl.licence_no ln, ppl.addr addr, ppl.birthday bd, dl.class class, dc.description des, dl.expiring_date ed "
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
}
