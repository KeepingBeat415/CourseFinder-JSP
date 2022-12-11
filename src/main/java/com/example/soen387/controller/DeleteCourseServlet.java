package com.example.soen387.controller;

import com.example.soen387.model.Course;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "DeleteCourseServlet", value = "/DeleteCourseServlet")
public class DeleteCourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String course_code = request.getParameter("code");

        String c_error_msg = "<div class=\"alert alert-secondary\" style=\"position: absolute; margin-top: 60px;\" role=\"alert\">Oops! Something went wrong.</div>";
        String c_success_msg = "<div class=\"alert alert-secondary\" style=\"position: absolute; margin-top: 60px;\" role=\"alert\">Course deleted successfully.</div>";

        Course course = new Course();

        if(course.deleteCourse(course_code)){
            request.setAttribute("c_success_msg", c_success_msg);
        }else{
            request.setAttribute("c_error_msg", c_error_msg);
        }
        request.getRequestDispatcher("view/admin/admin_home.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
