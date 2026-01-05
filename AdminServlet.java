package com.travelagency.servlets;

import com.travelagency. dao.BookingDAO;
import com.travelagency.dao.DestinationDAO;
import com. travelagency.dao.InquiryDAO;
import com.travelagency. models.Booking;
import com.travelagency.models. Destination;
import com.travelagency.models.Inquiry;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet. http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/admin-dashboard")
public class AdminServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userRole = (String) session.getAttribute("userRole");

        if (userRole == null || !userRole.equals("admin")) {
            response.sendRedirect("login");
            return;
        }

        String action = request.getParameter("action");
        
        if ("destinations".equals(action)) {
            DestinationDAO dao = new DestinationDAO();
            List<Destination> destinations = dao.getAllDestinations();
            request.setAttribute("destinations", destinations);
            request.getRequestDispatcher("/jsp/admin-destinations.jsp").forward(request, response);
        } else if ("bookings".equals(action)) {
            BookingDAO dao = new BookingDAO();
            List<Booking> bookings = dao.getAllBookings();
            request.setAttribute("bookings", bookings);
            request.getRequestDispatcher("/jsp/admin-bookings.jsp").forward(request, response);
        } else if ("inquiries". equals(action)) {
            InquiryDAO dao = new InquiryDAO();
            List<Inquiry> inquiries = dao.getAllInquiries();
            request.setAttribute("inquiries", inquiries);
            request.getRequestDispatcher("/jsp/admin-inquiries.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/jsp/admin-dashboard.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("add-destination".equals(action)) {
            String name = request.getParameter("name");
            String description = request. getParameter("description");
            String country = request.getParameter("country");
            String city = request. getParameter("city");
            BigDecimal pricePerDay = new BigDecimal(request.getParameter("pricePerDay"));
            int durationDays = Integer.parseInt(request.getParameter("durationDays"));
            String imageUrl = request.getParameter("imageUrl");
            String highlights = request.getParameter("highlights");
            String bestSeason = request.getParameter("bestSeason");

            Destination destination = new Destination();
            destination.setName(name);
            destination.setDescription(description);
            destination.setCountry(country);
            destination.setCity(city);
            destination. setPricePerDay(pricePerDay);
            destination.setDurationDays(durationDays);
            destination.setImageUrl(imageUrl);
            destination. setHighlights(highlights);
            destination.setBestSeason(bestSeason);

            DestinationDAO dao = new DestinationDAO();
            if (dao.createDestination(destination)) {
                request.setAttribute("success", "Destination added successfully!");
            }
            doGet(request, response);
        } else if ("confirm-booking".equals(action)) {
            int bookingId = Integer.parseInt(request.getParameter("bookingId"));
            BookingDAO dao = new BookingDAO();
            if (dao.updateBookingStatus(bookingId, "confirmed")) {
                request. setAttribute("success", "Booking confirmed!");
            }
            request.getParameter("action");
            doGet(request, response);
        }
    }
}
