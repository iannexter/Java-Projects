package br.com.gelatos.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


//cadastro usuarios no mysql


public class Database {
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            String url = "jdbc:mysql://localhost:3307/gelatos_hibernate";
            String username = "root";
            String password = "123456";
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
