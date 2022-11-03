package com.example.soen387.dao;

import com.example.soen387.model.Course;
import com.example.soen387.service.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseDao {

    public boolean isCourseExisted(String course_code){

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        boolean result = false;

        try {

            conn = DBConnection.getConnection();

            String query = "SELECT * FROM Course WHERE code = ?";
            // Prepared SQL Statement
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, course_code);

            ResultSet rs = preparedStatement.executeQuery();

            // Add Search Enrolled Course by student ID
            if (rs.next()) {
               result = true;
            }

            DBConnection.closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<Course> searchStudentEnrolledCourse(String student_id) {

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ArrayList<Course> course_list = new ArrayList<>();

        try {

            conn = DBConnection.getConnection();

            String query = "SELECT Course.code, Course.title, Course.semester, Course.days, Course.time, Course.room FROM Course JOIN Enrolled_In ON Course.id = Enrolled_In.course_id WHERE Enrolled_In.student_id = ? ";
            // Prepared SQL Statement
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, student_id);

            ResultSet rs = preparedStatement.executeQuery();

            // Add Search Enrolled Course by student ID
            while (rs.next()) {
                course_list.add(new Course(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }

            DBConnection.closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course_list;
    }
}
