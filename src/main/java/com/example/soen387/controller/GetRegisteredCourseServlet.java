package com.example.soen387.controller;

import com.example.soen387.dao.StudentDao;
import com.example.soen387.model.Course;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "GetRegisteredCourseServlet", urlPatterns = {"/student_home?message=delete_true", "/student_home?message=delete_false", "/student_home?message=add_true", "/student_home?message=add_false", "/student_home"})
public class GetRegisteredCourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = request.getParameter("message");

        String add_success = "<div class='form-row'>" +
                "<div class='form-group col-md-12'>" +
                "<div class='alert alert-success' role='alert' style='text-align: center;'>Course added successfully.</div>" +
                "</div>" +
                "</div>";
        ;
        String add_error = "<div class='form-row'>" +
                "<div class='form-group col-md-12'>" +
                "<div class='alert alert-danger' role='alert' style='text-align: center;'>Oops! Something went wrong. Please try again later.</div>" +
                "</div>" +
                "</div>";

        String delete_success = "<div class='form-row'>" +
                "<div class='form-group col-md-12'>" +
                "<div class='alert alert-success' role='alert' style='text-align: center;'>Course deleted successfully.</div>" +
                "</div>" +
                "</div>";

        String delete_error = "<div class='form-row'>" +
                "<div class='form-group col-md-12'>" +
                "<div class='alert alert-danger' role='alert' style='text-align: center;'>Oops! Something went wrong. Please try again later.</div>" +
                "</div>" +
                "</div>";

        if (!(result == null)) {
            switch (result) {
                case "delete_true":
                    request.setAttribute("delete_success", delete_success);
                    break;
                case "delete_false":
                    request.setAttribute("delete_error", delete_error);
                    break;
                case "add_true":
                    request.setAttribute("add_success", add_success);
                    break;
                case "add_false":
                    request.setAttribute("add_error", add_error);
                    break;
            }
        }

        HttpSession session = request.getSession();
        response.setContentType("text/html");
        String username = (String) session.getAttribute("username");
        System.out.println(username);
        StudentDao studentDao = new StudentDao();
        ArrayList<Course> registered_course = studentDao.registeredCourse(username);
        request.setAttribute("registered_course", registered_course);
        request.getRequestDispatcher("/view/student/student_home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

}
