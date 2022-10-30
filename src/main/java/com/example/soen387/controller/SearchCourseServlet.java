package com.example.soen387.controller;

import com.example.soen387.dao.UserDao;
import com.example.soen387.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "SearchCourseServlet", value = "/SearchCourseServlet")
public class SearchCourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String course_code = request.getParameter("course_code");

        String course_not_existed = "<div class=\"alert alert-secondary\" style=\"position: absolute; margin-top: 60px;\" role=\"alert\">" +
                "Oops! No course matching your search.</div>";
        String student_not_existed = "<div class=\"alert alert-secondary\" style=\"position: absolute; margin-top: 60px;\" role=\"alert\">" +
                "Oops! No student enroll " + course_code + " yet.</div>";

        // Create Data Access Object
        UserDao userDao = new UserDao();

        ArrayList<User> user_list = userDao.searchCourse(course_code);

        if(user_list.isEmpty()){
            request.setAttribute("student_not_existed", student_not_existed);
            request.getRequestDispatcher("view/admin/admin_home.jsp").forward(request, response);
        }
        else{
            String result = "<table class=\"table table-striped\">" +
                    "<thead>" +
                    "<tr>" +
                    "<th scope=\"col\">Student ID</th>" +
                    "<th scope=\"col\">Name</th>" +
                    "<th scope=\"col\">Phone</th>" +
                    "<th scope=\"col\">Email</th>" +
                    "</tr>" +
                    "</thead>";
            for(User user : user_list){
                result += "<tbody>" +
                        "<tr>" +
                        "<td>" + user.getId() + "</td>" +
                        "<td>" + user.getFirst_name() + " " + user.getLast_name() + "</td>" +
                        "<td>" + user.getPhone_number() + "</td>" +
                        "<td>" + user.getPhone_number() + "</td>" +
                        "</tr>" +
                        "</tbody>";
            }
            result += "</table>";

            request.setAttribute("search_course", result);
            request.getRequestDispatcher("view/admin/admin_home.jsp").forward(request, response);
        }

    }
}
