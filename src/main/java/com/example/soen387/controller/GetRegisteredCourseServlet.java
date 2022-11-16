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

@WebServlet(name = "GetRegisteredCourseServlet", urlPatterns = "/student_home")
public class GetRegisteredCourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
