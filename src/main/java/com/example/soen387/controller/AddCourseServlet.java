package com.example.soen387.controller;

import com.example.soen387.dao.StudentDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AddCourseServlet", urlPatterns = "/add_course")
public class AddCourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = "";
        HttpSession session = request.getSession();
        response.setContentType("text/html");
        String username = (String) session.getAttribute("username");
        System.out.println(username);
        int course_id = Integer.parseInt(request.getParameter("course_id"));
        System.out.println(course_id);
        StudentDao studentDao = new StudentDao();
        if (studentDao.addCourse(username, course_id)) {
            result = "add_true";
        } else {
            result = "add_false";
        }

        response.sendRedirect(request.getContextPath() + "/student_home?message="+result);


    }
}
