package com.example.soen387.controller;

import com.example.soen387.dao.StudentDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;


@WebServlet(name = "RemoveCourseServlet", urlPatterns = "/remove_course")
public class RemoveCourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = "";
        String end = request.getParameter("course_end_date");
        LocalDate end_date = LocalDate.parse(end);
        LocalDate today = LocalDate.now();
        if (end_date.isBefore(today)) {
            result = "deadline";
        } else {
            HttpSession session = request.getSession();
            response.setContentType("text/html");
            String username = (String) session.getAttribute("username");
            int course_id = Integer.parseInt(request.getParameter("course_id"));
            StudentDao studentDao = new StudentDao();
            if (studentDao.deleteCourse(username, course_id)) {
                result = "delete_true";
            } else {
                result = "delete_false";
            }
        }

        response.sendRedirect(request.getContextPath() + "/student_home?message=" + result);

    }
}
