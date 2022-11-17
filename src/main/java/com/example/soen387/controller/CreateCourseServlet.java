package com.example.soen387.controller;

import com.example.soen387.dao.CourseDao;
import com.example.soen387.model.Course;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

@WebServlet(name = "CreateCourseServlet", value = "/CreateCourseServlet")

public class CreateCourseServlet extends HttpServlet {


    ReentrantLock create_course_lock = new ReentrantLock();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String success_msg = "<div class='form-row'>" +
                "<div class='form-group col-md-12'>" +
                "<div class='alert alert-success' role='alert' style='text-align: center;'>Course created successfully.</div>" +
                "</div>" +
                "</div>";
        ;
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
        CourseDao courseDao = new CourseDao();

        create_course_lock.lock();
        boolean create_course_result = false;

        try{
            // Insert new course into the database
            create_course_result = courseDao.createCourse(course);
        }
        finally {
            create_course_lock.unlock();
        }

        if (create_course_result) {
            req.setAttribute("success_msg", success_msg);
            req.getRequestDispatcher("view/admin/course_create.jsp").forward(req, resp);
        } else {
            req.setAttribute("error_msg", error_msg);
            req.getRequestDispatcher("view/admin/course_create.jsp").forward(req, resp);
        }
    }
}
