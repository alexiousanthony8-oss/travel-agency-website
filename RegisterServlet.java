package com. travelagency.servlets;

import com.travelagency.dao.UserDAO;
import com.travelagency.models.User;
import javax.servlet.ServletException;
import javax. servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String country = request.getParameter("country");

        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "Passwords do not match");
            request.getRequestDispatcher("/jsp/register.jsp").forward(request, response);
            return;
        }

        UserDAO userDAO = new UserDAO();
        if (userDAO.getUserByUsername(username) != null) {
            request.setAttribute("error", "Username already exists");
            request.getRequestDispatcher("/jsp/register. jsp").forward(request, response);
            return;
        }

        if (userDAO.getUserByEmail(email) != null) {
            request. setAttribute("error", "Email already registered");
            request.getRequestDispatcher("/jsp/register.jsp").forward(request, response);
            return;
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhone(phone);
        user.setAddress(address);
        user.setCity(city);
        user.setCountry(country);

        if (userDAO.createUser(user)) {
            request.setAttribute("success", "Registration successful!  Please log in.");
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        } else {
            request. setAttribute("error", "Registration failed.  Please try again.");
            request.getRequestDispatcher("/jsp/register.jsp").forward(request, response);
        }
    }
}
