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

@WebServlet(name = "GetAvailableCourse", urlPatterns = "/available_course")
public class GetAvailableCourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        response.setContentType("text/html");
        String username = (String) session.getAttribute("username");
        StudentDao studentDao = new StudentDao();
        ArrayList<Course> available_course = studentDao.availableCourse(username);
        request.setAttribute("available_course", available_course);
        request.getRequestDispatcher("/view/student/add_course.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

}
