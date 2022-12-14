
package tictactoeserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.derby.jdbc.ClientDriver;


public class DatabaseAccessLayer {

    public static Connection con;

    
    
    
    private static Connection startConnection() {
        try {
            DriverManager.registerDriver(new ClientDriver());
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/RegisteredUsers", "root", "root");
            System.out.println("Connected to DataBase (in try of connection to database)");
        } catch (SQLException ex) {
            System.out.println("in catch of connecting to database");
            Logger.getLogger(DatabaseAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return con;
    }
    
    public static Boolean query(String username){
        try {
            con = startConnection();
            PreparedStatement stmt = con.prepareStatement("Select * FROM USERS WHERE username=?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                System.out.println("found the user");
               return true;
            }
            else{
                System.out.println("Didnt find the user");
                return false;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
    }

}