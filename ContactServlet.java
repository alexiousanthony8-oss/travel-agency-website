package com. travelagency.servlets;

import com.travelagency.dao.InquiryDAO;
import com.travelagency.models. Inquiry;
import javax.servlet.ServletException;
import javax. servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/contact")
public class ContactServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/contact.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request. getParameter("email");
        String phone = request.getParameter("phone");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");

        Inquiry inquiry = new Inquiry();
        inquiry.setName(name);
        inquiry.setEmail(email);
        inquiry.setPhone(phone);
        inquiry.setSubject(subject);
        inquiry.setMessage(message);

        InquiryDAO dao = new InquiryDAO();
        if (dao.createInquiry(inquiry)) {
            request.setAttribute("success", "Thank you!  We'll get back to you soon.");
            request.getRequestDispatcher("/jsp/contact.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Failed to send inquiry. Please try again.");
            request.getRequestDispatcher("/jsp/contact.jsp").forward(request, response);
        }
    }
}
