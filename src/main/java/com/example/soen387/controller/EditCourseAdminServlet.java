package com.example.soen387.controller;

import com.example.soen387.model.Course;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "EditCourseAdminServlet", value = "/EditCourseAdminServlet")

public class EditCourseAdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");

        Course course = new Course();

        ArrayList<Course> courseList = course.searchCourseByCode(code);
        Course found = courseList.get(0);

        req.setAttribute("code", found.getCode());
        req.setAttribute("title", found.getTitle());
        req.setAttribute("semester", found.getSemester());
        req.setAttribute("days", found.getDays());
        req.setAttribute("time", found.getTime());
        req.setAttribute("instructor", found.getInstructor());
        req.setAttribute("room", found.getRoom());

        req.getRequestDispatcher("view/admin/course_modify.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
