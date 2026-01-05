package com. travelagency.servlets;

import com.travelagency.dao.UserDAO;
import com. travelagency.models.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDAO userDAO = new UserDAO();
        User user = userDAO. getUserByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            HttpSession session = request.getSession();
            session. setAttribute("userId", user.getUserId());
            session.setAttribute("username", user. getUsername());
            session.setAttribute("userRole", user.getRole());
            session.setAttribute("userEmail", user.getEmail());

            if ("admin".equals(user.getRole())) {
                response.sendRedirect("admin-dashboard");
            } else {
                response.sendRedirect("destinations");
            }
        } else {
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        }
    }
}
