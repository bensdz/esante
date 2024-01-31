/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package esanteInterface;

import java.sql.*;

public class MySQLConnection {
    private static Connection conn;
    private static final String url = "jdbc:mysql://localhost:3306/esante";
    private static final String user = "root";
    private static final String password = "farouk159357";
    
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to MySQL database.");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    public static void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Connection to MySQL database closed.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void executeUpdate(String query) {
        try {
            Statement stmt = conn.createStatement();
            int rows = stmt.executeUpdate(query);
            System.out.println(rows + " rows affected.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static ResultSet executeQuery(String query) {
        ResultSet rs = null;
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }
}
