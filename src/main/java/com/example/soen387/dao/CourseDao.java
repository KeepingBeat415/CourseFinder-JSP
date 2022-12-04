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

            String query = "SELECT Course.code, Course.title, Course.semester, Course.days, Course.time, Course.instructor, Course.room FROM Course JOIN Enrolled_In ON Course.id = Enrolled_In.course_id WHERE Enrolled_In.student_id = ? ";
            // Prepared SQL Statement
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, student_id);

            ResultSet rs = preparedStatement.executeQuery();

            // Add Search Enrolled Course by student ID
            while (rs.next()) {
                course_list.add(new Course(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
            }

            DBConnection.closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course_list;
    }

    public ArrayList<Course> searchCourseByCode(String code) {

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ArrayList<Course> course_list = new ArrayList<>();

        try {

            conn = DBConnection.getConnection();

            String query = "SELECT code, title, semester, days, Course.time, instructor, room, start_date, end_date FROM Course WHERE  code = ? ";
            // Prepared SQL Statement
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, code);

            ResultSet rs = preparedStatement.executeQuery();

            // Add Search Enrolled Course by student ID
            while (rs.next()) {
                course_list.add(new Course(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
            }

            DBConnection.closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course_list;
    }

    public boolean createCourse(Course course) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            conn = DBConnection.getConnection();
            String query = "INSERT INTO Course (code, title, semester, days, time, instructor, room, start_date, end_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, course.getCode());
            preparedStatement.setString(2, course.getTitle());
            preparedStatement.setString(3, course.getSemester());
            preparedStatement.setString(4, course.getDays());
            preparedStatement.setString(5, course.getTime());
            preparedStatement.setString(6, course.getInstructor());
            preparedStatement.setString(7, course.getRoom());
            preparedStatement.setString(8, course.getStart_date());
            preparedStatement.setString(9, course.getEnd_date());

            int count = preparedStatement.executeUpdate();

            if (count != 0) {
                return true;
            }

            DBConnection.closeConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateCourse(Course course) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            conn = DBConnection.getConnection();
            String query = "UPDATE Course SET title=?, semester=?, days=?, time=?, instructor=?, room=?, start_date=?, end_date=? WHERE code=?";

            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(9, course.getCode());
            preparedStatement.setString(1, course.getTitle());
            preparedStatement.setString(2, course.getSemester());
            preparedStatement.setString(3, course.getDays());
            preparedStatement.setString(4, course.getTime());
            preparedStatement.setString(5, course.getInstructor());
            preparedStatement.setString(6, course.getRoom());
            preparedStatement.setString(7, course.getStart_date());
            preparedStatement.setString(8, course.getEnd_date());

            int count = preparedStatement.executeUpdate();

            if (count != 0) {
                return true;
            }

            DBConnection.closeConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
