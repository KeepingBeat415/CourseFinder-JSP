package com.example.soen387.controller;

import com.example.soen387.model.Person;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "DeletePersonServlet", value = "/DeletePersonServlet")
public class DeletePersonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        String error_msg = "<div class=\"alert alert-danger\" role=\"alert\">Oops! Something went wrong.</div>";

        Person person = new Person();

        if(person.deletePerson(username)){
            session.invalidate();
            response.sendRedirect("view/index.jsp");
        }else{
            request.setAttribute("error_msg", error_msg);
            request.getRequestDispatcher("view/admin/admin_delete.jsp").forward(request, response);
        }

    }
}
