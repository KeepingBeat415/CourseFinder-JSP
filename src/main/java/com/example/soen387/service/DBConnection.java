package com.example.soen387.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    static Connection conn = null;

    public static Connection getConnection() {
        // Set MySQL Parameters for Connection
        String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost:3306/SOEN387";
        String DB_USER = "root";
        String DB_PASSWORD = "";

        try{
            Class.forName(JDBC_DRIVER);
//            conn  = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            conn  = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            return conn;
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to database",e);
        } catch (ClassNotFoundException e){
            throw new RuntimeException("Error Class Not Found",e);
       }
    }

    public static void closeConnection() throws SQLException{
        if(conn!=null) conn.close();
    }

}
