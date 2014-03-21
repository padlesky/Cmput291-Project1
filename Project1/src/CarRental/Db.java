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
	public void people() throws SQLException {
		Statement s = con.createStatement();
		ResultSet vihcile = s.executeQuery("CREATE TABLE  people ("
				+ "sin           CHAR(15), " + "name          VARCHAR(40),"
				+ "height        number(5,2)," + "weight        number(5,2),"
				+ "eyecolor      VARCHAR (10)," + "haircolor     VARCHAR(10),"
				+ "addr          VARCHAR2(50)," + "gender        CHAR,"
				+ "birthday      DATE," + "PRIMARY KEY (sin),"
				+ "CHECK ( gender IN ('m', 'f') );");
	}

	public void Veh(String maker, String model, int serial_no, int year,
			String color) throws SQLException {
		Statement s = con.createStatement();
		ResultSet vihcile = s
				.executeQuery("CREATE TABLE vehicle (serial_no CHAR(15), "
						+ "maker VARCHAR(20),model VARCHAR(20),year number(4,0),"
						+ "color VARCHAR(10)" + "type_id integer,"
						+ "PRIMARY KEY (serial_no),"
						+ " FOREIGN KEY (type_id) REFERENCES vehicle_type);");
	}

	public void drive_licence() throws SQLException {
		Statement s = con.createStatement();
		ResultSet vihcile = s.executeQuery("CREATE TABLE drive_licence ("
				+ "licence_no      CHAR(15)," + "sin             char(15),"
				+ "class           VARCHAR(10)," + "photo           BLOB,"
				+ "issuing_date    DATE," + "expiring_date   DATE,"
				+ "PRIMARY KEY (licence_no)," + "UNIQUE (sin),"
				+ "FOREIGN KEY (sin) REFERENCES people" + "ON DELETE CASCADE");

	}

	public void driving_condition() throws SQLException {
		Statement s = con.createStatement();
		ResultSet vihcile = s.executeQuery("CREATE TABLE driving_condition ("
				+ "c_id        INTEGER," + "description VARCHAR(1024),"
				+ "PRIMARY KEY (c_id)" + ");");
	}

	public void restriction() throws SQLException {
		Statement s = con.createStatement();
		ResultSet vihcile = s.executeQuery("CREATE TABLE restriction("
				+ "licence_no   CHAR(15)," + "r_id         INTEGER,"
				+ "PRIMARY KEY (licence_no, r_id),"
				+ "FOREIGN KEY (licence_no) REFERENCES drive_licence,"
				+ "FOREIGN KEY (r_id) REFERENCES driving_condition" + ");");
	}

	public void vehicle_type() throws SQLException {
		Statement s = con.createStatement();
		ResultSet vihcile = s.executeQuery("CREATE TABLE vehicle_type ("
				+ "type_id       integer," + "type          CHAR(10),"
				+ "PRIMARY KEY (type_id)" + ");");
	}

	public void owner() throws SQLException {
		Statement s = con.createStatement();
		ResultSet vihcile = s.executeQuery("CREATE TABLE owner ("
				+ "owner_id          CHAR(15)," + "vehicle_id        CHAR(15),"
				+ "is_primary_owner  CHAR(1),"
				+ "PRIMARY KEY (owner_id, vehicle_id),"
				+ "FOREIGN KEY (owner_id) REFERENCES people,"
				+ "FOREIGN KEY (vehicle_id) REFERENCES vehicle,"
				+ "CHECK ( is_primary_owner IN ('y', 'n')));");
	}

	public void auto_sale() throws SQLException {
		Statement s = con.createStatement();
		ResultSet vihcile = s.executeQuery(" CREATE TABLE auto_sale ("
				+ "transaction_id  int," + "seller_id   CHAR(15),"
				+ "buyer_id    CHAR(15)," + "vehicle_id  CHAR(15),"
				+ "s_date      date," + "price       numeric(9,2),"
				+ "PRIMARY KEY (transaction_id),"
				+ "FOREIGN KEY (seller_id) REFERENCES people,"
				+ "FOREIGN KEY (buyer_id) REFERENCES people,"
				+ "FOREIGN KEY (vehicle_id) REFERENCES vehicle);");
	}

	public void ticket_type() throws SQLException {
		Statement s = con.createStatement();
		ResultSet vihcile = s.executeQuery("CREATE TABLE ticket_type ("
				+ "vtype     CHAR(10)," + "fine      number(5,2),"
				+ "PRIMARY KEY (vtype));");
	}

	public void ticket() throws SQLException {
		Statement s = con.createStatement();
		ResultSet vihcile = s
				.executeQuery("CREATE TABLE ticket ("
						+ "ticket_no     int,"
						+ "violator_no   CHAR(15),"
						+ "vehicle_id    CHAR(15),"
						+ "office_no     CHAR(15),"
						+ "vtype        char(10),"
						+ "vdate        date,"
						+ "place        varchar(20),"
						+ "descriptions varchar(1024),"
						+ "PRIMARY KEY (ticket_no),"
						+ "FOREIGN KEY (vtype) REFERENCES ticket_type,"
						+ "FOREIGN KEY (violator_no) REFERENCES people ON DELETE CASCADE,"
						+ "FOREIGN KEY (vehicle_id)  REFERENCES vehicle,"
						+ "FOREIGN KEY (office_no) REFERENCES people ON DELETE CASCADE);");
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
	
	public FirstSearchContainer driverLN(String licence_no) {
		String stm  = "SELECT ppl.name name, dl.licence_no ln, ppl.addr addr, ppl.birthday bd, "
				+ "dl.class class, dc.description des, dl.expiring_date ed "
				+ "FROM drive_licence dl, people ppl, restriction rst, driving_condition dc "
				+ "WHERE dl.sin = ppl.sin AND dl.licence_no = rst.licence_no "
				+ "AND rst.r_id = dc.c_id AND dl.licence_no = '" + licence_no + "'";
		ResultSet rs = performQuery(stm);
		FirstSearchContainer result = null;
		try {
			if (rs.next()) {
				result = new FirstSearchContainer (rs.getString("name"), rs.getString("ln"), 
														rs.getString("addr"), rs.getDate("bd"), rs.getString("class"), 
														rs.getString("des"), rs.getDate("ed"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
