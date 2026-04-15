package br.com.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


// no persistence.xml coloque:
//<property name="javax.persistence.jdbc.user" value="${DB_USER}"/>
//<property name="javax.persistence.jdbc.password" value="${DB_PASSWORD}"/>

// e para usar:
//Windows(abra o terminal e digite):
//set DB_USER=root
//set DB_PASSWORD=123456

//linux/mac(abra o terminal e digite):
//export DB_USER=root
//export DB_PASSWORD=123456


public class Database {
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            String url = "jdbc:mysql://db:3306/gelatos_hibernate";
            String username = "root";
            String password = "123456";
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
