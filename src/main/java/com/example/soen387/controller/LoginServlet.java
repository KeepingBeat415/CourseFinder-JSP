package com.example.soen387.controller;

import com.example.soen387.dao.UserDao;
import com.example.soen387.model.User;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Input variables
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String username_err = "<div class=\"alert alert-danger\" role=\"alert\">Username is not exist.</div>";

        String password_err = "<div class=\"alert alert-danger\" role=\"alert\">Password is incorrect.</div>";

        String error_msg = "<div class=\"alert alert-danger\" role=\"alert\">Oops! Something went wrong.</div>";

        // Create User with input variables
        User login_user = new User(username, password);

        // Create Data Access Object
        UserDao user = new UserDao();

        // Insert User data into the database
        String login_result = user.loginUser(login_user);

        // Session
        HttpSession session = request.getSession();

        switch (login_result){
            case "username_err":
                request.setAttribute("username_err", username_err);
                request.getRequestDispatcher("view/index.jsp").forward(request, response);
                break;
            case "password_err":
                request.setAttribute("password_err", password_err);
                request.getRequestDispatcher("view/index.jsp").forward(request, response);
                break;
            case "admin":
                session.setAttribute("username", username);
                response.sendRedirect("view/admin/admin_home.jsp");
                break;
//            case "student":
//                session.setAttribute("username",username);
//                response.sendRedirect("student/student_home.jsp");
//                break;
            default:
                request.setAttribute("error_msg", error_msg);
                request.getRequestDispatcher("view/index.jsp").forward(request, response);
        }

    }
}
