package com.example.soen387.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

import com.example.soen387.model.User;
import com.example.soen387.dao.RegisterDao;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Input variables
        String username = request.getParameter("username");
        // Hash password
        String password = BCrypt.hashpw(request.getParameter("password"), BCrypt.gensalt(12));
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String phone_number = request.getParameter("phone_number");
        String DOB = request.getParameter("DOB");
        String user_type = request.getParameter("user_type");

        String success_msg = "<div class='form-row'>" +
                "<div class='form-group col-md-12'>" +
                "<div class='alert alert-success' role='alert' style='text-align: center;'>Account created successfully.</div>" +
                "</div>" +
                "</div>";

        String exist_msg = "<div class='form-row'>" +
                "<div class='form-group col-md-12'>" +
                "<div class='alert alert-danger' role='alert' style='text-align: center;'>Username is already existed.</div>" +
                "</div>" +
                "</div>";

        String error_msg = "<div class='form-row'>" +
                "<div class='form-group col-md-12'>" +
                "<div class='alert alert-danger' role='alert' style='text-align: center;'>Oops! Something went wrong. Please try again later.</div>" +
                "</div>" +
                "</div>";

        // Create New User with input variables
        User user = new User(username,password,first_name,last_name,address,email,phone_number,DOB,user_type);

        // Create Data Access Object
        RegisterDao registerDao = new RegisterDao();

        // Insert User data into the database
        String userRegistered = registerDao.registerUser(user);

        if(userRegistered.equals("SUCCESS"))
        {
            request.setAttribute("success_msg", success_msg);
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
        else if (userRegistered.equals("EXISTED")){
            request.setAttribute("exist_msg", exist_msg);
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
        else
        {
            request.setAttribute("error_msg", error_msg);
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }
}
