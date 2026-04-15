//package br.com.login;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//
//public class Database {
//    public static Connection getConnection() {
//        Connection connection = null;
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver"); 
//            String url = "jdbc:mysql://db:3306/gelatos_hibernate";
//            String username = "root";
//            String password = "123456";
//            connection = DriverManager.getConnection(url, username, password);
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//        return connection;
//    }
//}
