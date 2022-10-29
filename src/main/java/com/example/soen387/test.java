package com.example.soen387;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class test {

    static Connection conn = null;

    public static void main(String[] args){

        System.out.println("Hello World");

//        String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
//        String DB_URL = "jdbc:mysql://localhost:3306/SOEN387";
//        String DB_USER = "root";
//        String DB_PASSWORD = "rootpassword";
//
//        try{
//            Class.forName(JDBC_DRIVER);
//            conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
//            System.out.println("Connected...");
//
//            PreparedStatement preparedStatement = null;
//
//            String query = "INSERT INTO Users (username,password,first_name,last_name,address,email,phone_number,DOB,user_type) values (?,?,?,?,?,?,?,?,?)";
//
//            preparedStatement = conn.prepareStatement(query);
//
//            preparedStatement.setString(1, "username");
//            preparedStatement.setString(2, "password");
//            preparedStatement.setString(3, "first_name");
//            preparedStatement.setString(4, "last_name");
//            preparedStatement.setString(5, "address");
//            preparedStatement.setString(6, "email");
//            preparedStatement.setString(7, "phone_number");
//            preparedStatement.setString(8, "2022-10-11");
//            preparedStatement.setString(9, "user_type");
//
//            int count = preparedStatement.executeUpdate();
//
//            if (count!=0)
//                System.out.println("Inserted..");
//            //return conn;
//        } catch (SQLException e) {
//            throw new RuntimeException("Error connecting to database",e);
//        } catch (ClassNotFoundException e){
//            throw new RuntimeException("Error Class Not Found",e);
//        }
        String hashed_password = BCrypt.hashpw("rootpassword", BCrypt.gensalt(12));

        System.out.println(hashed_password);

    }
}
