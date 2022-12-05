package com.example.soen387.controller;

import com.example.soen387.model.Course;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateCourseServlet", value = "/UpdateCourseServlet")

public class UpdateCourseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String success_msg = "<div class='form-row'>" +
                "<div class='form-group col-md-12'>" +
                "<div class='alert alert-success' role='alert' style='text-align: center;'>Course modified successfully.</div>" +
                "</div>" +
                "</div>";
        String error_msg = "<div class='form-row'>" +
                "<div class='form-group col-md-12'>" +
                "<div class='alert alert-danger' role='alert' style='text-align: center;'>Oops! Something went wrong. Please try again later.</div>" +
                "</div>" +
                "</div>";

        String code = req.getParameter("code");
        String title = req.getParameter("title");
        String semester = req.getParameter("semester");
        String days = req.getParameter("days");
        String time = req.getParameter("time");
        String instructor = req.getParameter("instructor");
        String room = req.getParameter("room");

        Course course = new Course(code, title, semester, days, time, instructor, room);

        boolean update_course_result = course.updateCourse();

        if (update_course_result) {
            req.setAttribute("success_msg", success_msg);
        } else {
            req.setAttribute("error_msg", error_msg);
        }
        req.setAttribute("code", code);
        req.setAttribute("title", title);
        req.setAttribute("semester", semester);
        req.setAttribute("days", days);
        req.setAttribute("time", time);
        req.setAttribute("instructor", instructor);
        req.setAttribute("room", room);

        req.getRequestDispatcher("view/admin/course_modify.jsp").forward(req, resp);
    }
}
