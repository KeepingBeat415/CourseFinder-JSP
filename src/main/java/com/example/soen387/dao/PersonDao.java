package com.example.soen387.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.soen387.model.Person;
import com.example.soen387.service.DBConnection;
import org.mindrot.jbcrypt.BCrypt;

public class PersonDao {

    public String registerUser(Person person)
    {
        String username = person.getUsername();
        String password = person.getPassword();
        String first_name = person.getFirst_name();
        String last_name = person.getLast_name();
        String address = person.getAddress();
        String email = person.getEmail();
        String phone_number = person.getPhone_number();
        String DOB = person.getDOB();
        String user_type = person.getUser_type();

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

    public String loginPerson(Person person){

        String username = person.getUsername();
        String password = person.getPassword();
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

    public ArrayList<Person> searchCourse(String course_code) {

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ArrayList<Person> person_list = new ArrayList<>();

        try {

            conn = DBConnection.getConnection();

            String query = "SELECT Users.id, Users.first_name, Users.last_name, Users.phone_number,Users.email FROM Users JOIN Enrolled_In ON Users.id = Enrolled_In.student_id JOIN Course ON Course.id = Enrolled_In.course_id WHERE BINARY Course.code = ?";
            // Prepared SQL Statement
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, course_code);

            ResultSet rs = preparedStatement.executeQuery();

            // Add Search Enrolled Student's info into ArrayList
            while (rs.next()) {
                person_list.add(new Person(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }

            DBConnection.closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person_list;
    }

    public String searchStudent(String student_id){

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        String search_result = "None";

        try {

            conn = DBConnection.getConnection();

            String query = "SELECT first_name, last_name FROM Users WHERE id = ? AND user_type = 'student'";
            // Prepared SQL Statement
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, student_id);

            ResultSet rs = preparedStatement.executeQuery();

            // Search student info by id
            if (rs.next()) {
                search_result = rs.getString(1) + " " + rs.getString(2);
            }

            DBConnection.closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
            search_result = "ERROR";
        }
        return search_result;
    }

    public Person findPersonInfo(String username){

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Person person = null;
        try {

            conn = DBConnection.getConnection();

            String query = "SELECT first_name, last_name, address, email, phone_number, DOB, user_type, username FROM Users WHERE username = ?";
            // Prepared SQL Statement
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, username);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                // Search info by id
                person = new Person(rs.getString(8), "", rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));

                DBConnection.closeConnection();
            }

            return person;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String updatePersonInfo(Person person){

        String username = person.getUsername();
        String first_name = person.getFirst_name();
        String last_name = person.getLast_name();
        String address = person.getAddress();
        String email = person.getEmail();
        String phone_number = person.getPhone_number();
        String DOB = person.getDOB();

        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {

            conn = DBConnection.getConnection();

            // Update person profile
            String query = "UPDATE Users SET first_name=?, last_name=?, address=?, email=?, phone_number=?, DOB=? where username=?";
            preparedStatement = conn.prepareStatement(query);

            preparedStatement.setString(1, first_name);
            preparedStatement.setString(2, last_name);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, phone_number);
            preparedStatement.setString(6, DOB);
            preparedStatement.setString(7, username);

            int count = preparedStatement.executeUpdate();

            if (count!=0)
                return "SUCCESS";

            DBConnection.closeConnection();

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return "ERROR";
    }

    public boolean deletePerson(String username){

        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {

            conn = DBConnection.getConnection();

            String query = "DELETE FROM Users WHERE username = ?";
            // Prepared SQL Statement
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, username);

            int count = preparedStatement.executeUpdate();

            if (count != 0) {
                return true;
            }

            DBConnection.closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
