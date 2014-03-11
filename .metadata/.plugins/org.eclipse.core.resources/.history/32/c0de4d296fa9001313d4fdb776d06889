import java.sql.*;
import java.io.*;

public class Tutorial{

    public static void main(String[] args)
    {
        String m_url="dbc:oracle:thin:@gwynne.cs.ualberta.ca:1521:CRS";
        String m_driverName="oracle.jdbc.driver.OracleDriver";
        Console cns = System.console();
        String m_username = cns.readLine("Username: ");
        String m_password = new String(cns.readPassword("Password: "));

        try{
            Class drvClass = Class.forName(m_driverName);
            Connection con = DriverManager.getConnection(m_url,
                                m_username,m_password);
            
            Statement stmt = con.createStatement();

            String statement="DROP TABLE MOVIE";
            stmt.executeUpdate(statement);

            statement=" CREATE TABLE MOVIE(title char(20) "
                            + " ,movie_number INTEGER "
                            + " ,primary key(movie_number)) ";
            stmt.executeUpdate(statement);

            statement= "INSERT INTO MOVIE VALUES('Chicago', 1)";
            stmt.executeUpdate(statement);


            String query ="SELECT title, movie_number FROM MOVIE";


            ResultSet rs =stmt.executeQuery(query);

            
            while(rs.next()){
                System.out.println(rs.getString("title")+","
                                   +rs.getInt("movie_number"));
            }

            











            stmt.close();
            con.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        

        
    }
}
