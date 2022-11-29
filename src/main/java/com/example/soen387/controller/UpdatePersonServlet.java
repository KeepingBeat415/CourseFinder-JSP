package com.example.soen387.controller;
import com.example.soen387.model.Person;
import com.example.soen387.model.Administrator;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UpdatePersonServlet", value = "/UpdatePersonServlet")
public class UpdatePersonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Session
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String phone_number = request.getParameter("phone_number");
        String DOB = request.getParameter("DOB");
        String user_type = request.getParameter("user_type");

        // Check whether person information modified
        Person original = new Person();
        original = original.findByUsername(username);

        String ori_first_name = original.getFirst_name();
        String ori_last_name = original.getLast_name();
        String ori_address = original.getAddress();
        String ori_email = original.getEmail();
        String ori_phone_number = original.getPhone_number();
        String ori_DOB = original.getDOB();

        String success_msg = "<div class='form-row'>" +
                "<div class='form-group col-md-12'>" +
                "<div class='alert alert-success' role='alert' style='text-align: center;'>Profile Modify Successfully.</div>" +
                "</div>" +
                "</div>";

        String error_msg = "<div class='form-row'>" +
                "<div class='form-group col-md-12'>" +
                "<div class='alert alert-danger' role='alert' style='text-align: center;'>Oops! Something went wrong. Please try again later.</div>" +
                "</div>" +
                "</div>";

        String same_msg = "<div class='form-row'>" +
                "<div class='form-group col-md-12'>" +
                "<div class='alert alert-warning' role='alert' style='text-align: center;'>Oops! Nothing to update.</div>" +
                "</div>" +
                "</div>";
        // If submit request nothing to change
        if(first_name.equals(ori_first_name) && last_name.equals(ori_last_name) && address.equals(ori_address) && email.equals(ori_email) && phone_number.equals(ori_phone_number) && DOB.equals(ori_DOB)){
            request.setAttribute("same_msg", same_msg);
            request.setAttribute("person_info", original);
            request.getRequestDispatcher("/view/admin/admin_profile.jsp").forward(request, response);
        }

        String personUpdated = "";
        Person person = null;

        switch (user_type){
            case "admin":
                person = new Administrator(username, "", first_name, last_name, address, email, phone_number, DOB);
                personUpdated = person.updatePerson();
                break;
                // Space holder for student profile
            case "student":
                break;
        }

        if ("SUCCESS".equals(personUpdated)) {
            request.setAttribute("success_msg", success_msg);
        } else {
            request.setAttribute("error_msg", error_msg);
        }

        request.setAttribute("person_info", person);
        request.getRequestDispatcher("/view/admin/admin_profile.jsp").forward(request, response);

    }
}
