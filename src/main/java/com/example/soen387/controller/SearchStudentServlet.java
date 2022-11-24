package com.example.soen387.controller;

import com.example.soen387.dao.CourseDao;
import com.example.soen387.dao.PersonDao;
import com.example.soen387.model.Course;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "SearchStudentServlet", value = "/SearchStudentServlet")
public class SearchStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String student_id = request.getParameter("student_id");
        String result = "";

        String student_not_existed = "<div class=\"alert alert-secondary\" style=\"position: absolute; margin-top: 60px;\" role=\"alert\">" +
                "Oops! No student ID matching your search.</div>";

        String student_not_enrolled = "<div class=\"alert alert-secondary\" style=\"position: absolute; margin-top: 60px;\" role=\"alert\">" +
                "Oops! The student did not enrolled any courses.</div>";

        String error_msg = "<div class=\"alert alert-secondary\" style=\"position: absolute; margin-top: 60px;\" role=\"alert\">" +
                "Oops! Something went wrong. Please try again later.</div>";

        // Create Data Access Object
        PersonDao personDao = new PersonDao();

        String search_student_result = personDao.searchStudent(student_id);

        // Student ID invalided
        if(search_student_result.equals("None")){
            request.setAttribute("student_not_existed", student_not_existed);
        }// MySql Error
        else if (search_student_result.equals("ERROR")) {
            request.setAttribute("error_msg", error_msg);
        }else{

            String student_name = "<h4> Name: " + search_student_result + "</h4>";
            request.setAttribute("student_name", student_name);
            CourseDao courseDao = new CourseDao();
            // Student enrolled course list
            ArrayList<Course> course_list = courseDao.searchStudentEnrolledCourse(student_id);

            if(course_list.isEmpty()){
                request.setAttribute("student_not_enrolled", student_not_enrolled);
                //request.getRequestDispatcher("view/admin/admin_home.jsp").forward(request, response);
            }
            else {

                result = "<table class=\"table table-striped\">" +
                        "<thead>" +
                        "<tr>" +
                        "<th scope=\"col\">Course</th>" +
                        "<th scope=\"col\">Title</th>" +
                        "<th scope=\"col\">Semester</th>" +
                        "<th scope=\"col\">Schedule</th>" +
                        "<th scope=\"col\">Location</th>" +
                        "</tr>" +
                        "</thead>";

                for (Course course : course_list) {
                    result += "<tbody>" +
                            "<tr>" +
                            "<td>" + course.getCode() + "</td>" +
                            "<td>" + course.getTitle() + "</td>" +
                            "<td>" + course.getSemester() + "</td>" +
                            "<td>" + course.getDays() + "-" + course.getTime() + "</td>" +
                            "<td>" + course.getRoom() + "</td>" +
                            "</tr>" +
                            "</tbody>";
                }
                result += "</table>";
                request.setAttribute("result", result);
            }
        }
        request.getRequestDispatcher("view/admin/admin_home.jsp").forward(request, response);

    }
}
