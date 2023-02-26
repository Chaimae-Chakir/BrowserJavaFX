package ma.enset.browser.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class SignletonConnexionDB {

    private static Connection connection;
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/browser","root","");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static Connection getConnection() {
        return connection;
    }
}