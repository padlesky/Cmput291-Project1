package CarRental;

import java.sql.*;
import java.io.*;

public class Db {
	
	private Connection con = null;
    private String m_url="dbc:oracle:thin:@gwynne.cs.ualberta.ca:1521:CRS";
    private String m_driverName="oracle.jdbc.driver.OracleDriver";
    
	/**
	* Instantiating a Db object will perform connection
	*/
	public Db(String m_username, String m_password) {
		try {
			Class drvClass = Class.forName(m_driverName);
			DriverManager.registerDriver((Driver)drvClass.newInstance());
			this.con =  DriverManager.getConnection(m_url, m_username, m_password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void people() throws SQLException{
		Statement s = con.createStatement();
		ResultSet vihcile = s.executeQuery(	"CREATE TABLE  people ("
				  +"sin           CHAR(15), " 
				  +"name          VARCHAR(40),"
				  +"height        number(5,2),"
				  +"weight        number(5,2),"
				  +"eyecolor      VARCHAR (10),"
				  +"haircolor     VARCHAR(10),"
				  +"addr          VARCHAR2(50),"
				  +"gender        CHAR,"
				  +"birthday      DATE,"
				  +"PRIMARY KEY (sin),"
				  +"CHECK ( gender IN ('m', 'f') );");
	}
	public void Veh(String maker, String model,int serial_no, int year, String color) throws SQLException{
		Statement s = con.createStatement();
		ResultSet vihcile = s.executeQuery("CREATE TABLE vehicle (serial_no CHAR(15), "
				+"maker VARCHAR(20),model VARCHAR(20),year number(4,0),"
				+"color VARCHAR(10)"
				+"PRIMARY KEY (serial_no),);");
		}

}
