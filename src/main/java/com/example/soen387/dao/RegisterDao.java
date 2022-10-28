package com.example.soen387.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.soen387.model.User;
import com.example.soen387.service.DBConnection;

public class RegisterDao {

    public String registerUser(User user)
    {
        String username = user.getUsername();
        String password = user.getPassword();
        String first_name = user.getFirst_name();
        String last_name = user.getLast_name();
        String address = user.getAddress();
        String email = user.getEmail();
        String phone_number = user.getPhone_number();
        String DOB = user.getDOB();
        String user_type = user.getUser_type();

        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {

            conn = DBConnection.getConnection();

            String query_existed = "SELECT id FROM Users WHERE Users.username = ?";

            preparedStatement = conn.prepareStatement(query_existed);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();

//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery(query_existed);
            System.out.println(rs);

            if(rs.next()){
                return "EXISTED";
            }

            String query = "INSERT INTO Users (username,password,first_name,last_name,address,email,phone_number,DOB,user_type) values (?,?,?,?,?,?,?,?,?)";
            preparedStatement = conn.prepareStatement(query);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, first_name);
            preparedStatement.setString(4, last_name);
            preparedStatement.setString(5, address);
            preparedStatement.setString(6, email);
            preparedStatement.setString(7, phone_number);
            preparedStatement.setString(8, DOB);
            preparedStatement.setString(9, user_type);

            int count = preparedStatement.executeUpdate();

            if (count!=0)
                return "SUCCESS";

            DBConnection.closeConnection();

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return "ERROR";
    }


}
