package com.example.soen387.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.soen387.model.User;
import com.example.soen387.service.DBConnection;
import org.mindrot.jbcrypt.BCrypt;

public class UserDao {

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
            // Validate username whether exist
            String query_existed = "SELECT id FROM Users WHERE Users.username = ?";
            // Prepared SQL Statement
            preparedStatement = conn.prepareStatement(query_existed);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){
                return "EXISTED";
            }
            // Insert New User into Database
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

    public String loginUser(User user){

        String username = user.getUsername();
        String password = user.getPassword();
        String result = "error_msg";

        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {

            conn = DBConnection.getConnection();

            String query_existed = "SELECT password, user_type FROM Users WHERE Users.username = ?";
            // Prepared SQL Statement
            preparedStatement = conn.prepareStatement(query_existed);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();

            // Validate username whether exist
            if(rs.next()){
                // Validate password whether match
                if (BCrypt.checkpw(password, rs.getString(1))) {
                    result = rs.getString(2);
                }
                else{
                    result = "password_err";
                }
            }
            else{
                result = "username_err";
            }

            DBConnection.closeConnection();

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}