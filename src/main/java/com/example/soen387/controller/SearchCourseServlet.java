package com.example.soen387.controller;

import com.example.soen387.dao.CourseDao;
import com.example.soen387.dao.PersonDao;
import com.example.soen387.model.Person;

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
        String student_not_enrolled = "<div class=\"alert alert-secondary\" style=\"position: absolute; margin-top: 60px;\" role=\"alert\">" +
                "Oops! No student enroll " + course_code + " yet.</div>";

        CourseDao courseDao = new CourseDao();

        boolean is_course_existed = courseDao.isCourseExisted(course_code);

        // checking whether search course existed
        if(!is_course_existed){
            request.setAttribute("course_not_existed", course_not_existed);
            request.getRequestDispatcher("view/admin/admin_home.jsp").forward(request, response);
        }else{
            // Create Data Access Object
            PersonDao personDao = new PersonDao();

            ArrayList<Person> person_list = personDao.searchCourse(course_code);

            if(person_list.isEmpty()){
                request.setAttribute("student_not_enrolled", student_not_enrolled);
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
                for(Person person : person_list){
                    result += "<tbody>" +
                            "<tr>" +
                            "<td>" + person.getId() + "</td>" +
                            "<td>" + person.getFirst_name() + " " + person.getLast_name() + "</td>" +
                            "<td>" + person.getPhone_number() + "</td>" +
                            "<td>" + person.getPhone_number() + "</td>" +
                            "</tr>" +
                            "</tbody>";
                }
                result += "</table>";

                request.setAttribute("search_course", result);
                request.getRequestDispatcher("view/admin/admin_home.jsp").forward(request, response);
            }
        }



    }
}
