package main;

import java.sql.*;
import java.io.*;

import CarRental.Search;

public class Main{

    public static void main(String[] args)
    {
        String m_url="dbc:oracle:thin:@gwynne.cs.ualberta.ca:1521:CRS";
        String m_driverName="oracle.jdbc.driver.OracleDriver";
        Console cns = System.console();
        String m_username = grabUsername(cns);
        String m_password = grabPassword(cns);
        boolean c_password = false;
        
    	while (c_password == false) {
	        try{
	            Class drvClass = Class.forName(m_driverName);
	            Connection con = DriverManager.getConnection(m_url,
	                                m_username,m_password);
	            con.close();
	            c_password = true;
	        }
	        catch(Exception e){
	        	cns.printf("Wrong username or password.\n"+"Plese try again.\n");
	        	grabUsername(cns);
	            grabPassword(cns);
	        }
    	}
    	
    	printStartMessage(cns);
    	String input;
        while (true) {
        	input = cns.readLine("Please enter a command: ");
        	if (input.equals("quit")) {
        		break;
        	}
        	if (input.equals("search")) {
        		Search.search(m_username, m_password, cns);
        		printStartMessage(cns);
        	}
        }
    }
    
    public static String grabUsername(Console cns) {
        return cns.readLine("Username: ");
    }
    
    public static String grabPassword(Console cns) {
        return new String(cns.readPassword("Password: "));
    }
    
    public static void printStartMessage(Console cns) {
    	cns.printf("\n\n\n" + "Enter the word 'quit' when you want to exit.\n");
    	cns.printf("Enter one of the 5 commands to perform an action.\n");
    	cns.printf("'nvReg' for a new vehicle registrations.\n");
    	cns.printf("'autoT' for an automobile transaction.\n");
    	cns.printf("'dlReg' for a driver license registration.\n");
    	cns.printf("'vRecord' for a violation record.\n");
    	cns.printf("'search' for a search.\n\n");
    }
}
