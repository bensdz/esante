package esanteInterface;
import java.sql.*;
public class ESante {
    public static String user;
    public static String pw;
    public static String consul;
    public static int codeemp;
    public static void main(String[] args) {
       Connection conn = MySQLConnection.getConnection();
        
        LoginP blogin= new LoginP();
        blogin.setVisible(true);

        // Close the connection to the database
        MySQLConnection.closeConnection();
        
    }
    
}
