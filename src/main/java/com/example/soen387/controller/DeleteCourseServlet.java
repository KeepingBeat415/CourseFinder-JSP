package com.example.soen387.controller;

import com.example.soen387.dao.StudentDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "DeleteCourseServlet", urlPatterns = "/delete_course")
public class DeleteCourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String delete_success = "<div class='form-row'>" +
                "<div class='form-group col-md-12'>" +
                "<div class='alert alert-success' role='alert' style='text-align: center;'>Course created successfully.</div>" +
                "</div>" +
                "</div>";
        ;
        String delete_error = "<div class='form-row'>" +
                "<div class='form-group col-md-12'>" +
                "<div class='alert alert-danger' role='alert' style='text-align: center;'>Oops! Something went wrong. Please try again later.</div>" +
                "</div>" +
                "</div>";

        HttpSession session = request.getSession();
        response.setContentType("text/html");
        String username = (String) session.getAttribute("username");
        System.out.println(username);
        int course_id = Integer.parseInt(request.getParameter("course_id"));
        System.out.println(course_id);
        StudentDao studentDao = new StudentDao();
        if (studentDao.deleteCourse(username, course_id)) {
            request.setAttribute("delete_success", delete_success);
        } else {
            request.setAttribute("delete_error", delete_error);
        }
        request.getRequestDispatcher("/student_home").forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }
}
