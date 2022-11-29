package com.example.soen387.controller;

import com.example.soen387.model.Person;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "GetPersonInfoServlet", value = "/GetPersonInfoServlet")
public class GetPersonInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");

        // Session
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        Person person = new Person();

        request.setAttribute("person_info", person.findByUsername(username));
        request.getRequestDispatcher("/view/admin/admin_profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
