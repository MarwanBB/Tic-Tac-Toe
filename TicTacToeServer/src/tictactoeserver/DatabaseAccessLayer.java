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
        //driver
        //connect DB
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

    public static Boolean signUp(String username, String password) {

        if (!query(username)) {
            try {
                con = startConnection();
                PreparedStatement stmt = con.prepareStatement("insert into USERS (username,password,isOnline) values(?,?,?)");
                stmt.setString(1, username);
                stmt.setString(2, password);
                stmt.setBoolean(3, false);
                stmt.executeUpdate();
                stmt.close();
                con.close();
                return true;
                // do we need to commit?

            } catch (SQLException ex) {
                Logger.getLogger(DatabaseAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
                return false;

            }
        } else {
            return false;
        }

    }

    public static boolean signIn(String username, String password) {

        try {
            con = startConnection();
            PreparedStatement stmt = con.prepareStatement("Select * FROM USERS WHERE username=? and password=?");
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("user for sign in is found in database");
                stmt.close();
                con.close();
                return true;

            } else {
                System.out.println("user for sign in is NOT found in database");
                stmt.close();
                con.close();
                return false;
            }
            // do we need to commit?
        } catch (SQLException ex) {
            System.out.println("catch of sign in in database");
            Logger.getLogger(DatabaseAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public static Boolean query(String username) {
        try {
            con = startConnection();
            PreparedStatement stmt = con.prepareStatement("Select * FROM USERS WHERE username=?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("found the user");
                return true;
            } else {
                System.out.println("Didnt find the user");
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ResultSet getOnlinePlayers() {
        ResultSet rs = null;
        try {

            PreparedStatement stmt = con.prepareStatement("Select name,Score FROM USERS where isOnline = true ", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rs;

    }

    public static int getNumberOfOnlinePlayers() {
        con = startConnection();

        int online = 0;
        try {
            ResultSet resultSet;

            PreparedStatement query = con.prepareStatement("Select * FROM USERS WHERE isOnline = true", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            resultSet = query.executeQuery();
            resultSet.last();
            online = resultSet.getRow();
            query.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return online;
    }

    public static int getNumberOfOFFlinePlayers() {
        con = startConnection();

        int counter = 0;
        try {
            ResultSet resultSet = null;

            PreparedStatement query = con.prepareStatement("Select * FROM USERS WHERE isOnline = false", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            resultSet = query.executeQuery();
            resultSet.last();
            counter = resultSet.getRow();
            query.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return counter;
    }

}
