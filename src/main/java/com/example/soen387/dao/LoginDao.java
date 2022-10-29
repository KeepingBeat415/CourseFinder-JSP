package com.example.soen387.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.soen387.model.User;
import com.example.soen387.service.DBConnection;
import org.mindrot.jbcrypt.BCrypt;

public class LoginDao {
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
