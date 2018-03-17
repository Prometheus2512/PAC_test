package sample.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    private String url = "jdbc:mysql://localhost:3306/PawsAndClaws";
    private String login = "user";
    private String pwd ="password";
    private Connection con;
    private static DBConnection instance = null;

    private DBConnection() {
        try
        {
            con = DriverManager.getConnection(url, login, pwd);
            System.out.println("connexion ok");
        } catch (SQLException ex)
        {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);        }
    }
    public static DBConnection getInstance()
    {
        if(instance == null)
        {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getCon() {
        return con;
    }


}
