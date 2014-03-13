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

}
