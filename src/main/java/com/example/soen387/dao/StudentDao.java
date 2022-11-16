package com.example.soen387.dao;

import com.example.soen387.model.Course;
import com.example.soen387.service.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDao {
    public ArrayList<Course> registeredCourse(String username) {
        Connection conn = null;
        PreparedStatement ppstm = null;
        ArrayList<Course> registered_course = new ArrayList<>();

        try {
            conn = DBConnection.getConnection();
            String query = "SELECT c.id, c.code, c.title, c.semester, c.days, c.time, c.instructor, c.room from Course c where c.id in (select e.course_id from Enrolled_In e, Users u where u.id = e.student_id and u.username = ?);";
            ppstm = conn.prepareStatement(query);
            ppstm.setString(1, username);
            ResultSet rs = ppstm.executeQuery();
            while (rs.next()) {
                registered_course.add(new Course(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }
            DBConnection.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registered_course;
    }

    public ArrayList<Course> availableCourse(String username) {
        Connection conn = null;
        PreparedStatement ppstm = null;
        ArrayList<Course> available_course = new ArrayList<>();

        try {
            conn = DBConnection.getConnection();
            String query = "SELECT c.id, c.code, c.title, c.semester, c.days, c.time, c.instructor, c.room from Course c where c.id not in (select e.course_id from Enrolled_In e, Users u where u.id = e.student_id and u.username = ?);";
            ppstm = conn.prepareStatement(query);
            ppstm.setString(1, username);
            ResultSet rs = ppstm.executeQuery();
            while (rs.next()) {
                available_course.add(new Course(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }
            DBConnection.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return available_course;
    }

    public boolean deleteCourse(String username, int courseID) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {

            conn = DBConnection.getConnection();

            String query = "DELETE e FROM Enrolled_In e, Users u where e.course_id = ? and e.student_id = u.id and u.username = ?;";
            // Prepared SQL Statement
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, courseID);
            preparedStatement.setString(2, username);

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

    public boolean addCourse(String username, int courseID) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {

            conn = DBConnection.getConnection();

            String query = "INSERT INTO Enrolled_In (course_id, student_id) select ?, u.id from Users u where u.username = ?;";
            // Prepared SQL Statement
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, courseID);
            preparedStatement.setString(2, username);

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
