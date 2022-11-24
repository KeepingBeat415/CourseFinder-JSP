package com.example.soen387.controller;

import com.example.soen387.dao.CourseDao;
import com.example.soen387.dao.PersonDao;
import com.example.soen387.model.Course;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "SearchCourseInfoServlet", value = "/SearchCourseInfoServlet")
public class SearchCourseInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String code = request.getParameter("course_info_code");

        String course_info_not_existed = "<div class=\"alert alert-secondary\" style=\"position: absolute; margin-top: 60px;\" role=\"alert\">" +
                "Oops! No course code matching your search.</div>";

        Course course = new Course();

        ArrayList<Course> result = course.searchCourseByCode(code);

        if(result.isEmpty()){
            request.setAttribute("course_info_not_existed", course_info_not_existed);
        }else{
            request.setAttribute("course_info_list", result);
        }
        request.getRequestDispatcher("view/admin/admin_home.jsp").forward(request, response);
    }
}
