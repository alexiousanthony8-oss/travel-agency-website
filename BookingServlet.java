package com.travelagency.servlets;

import com.travelagency.dao.BookingDAO;
import com.travelagency.dao. DestinationDAO;
import com.travelagency.models. Booking;
import com.travelagency.models.Destination;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet. http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/booking")
public class BookingServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("login");
            return;
        }

        if ("mybookings".equals(action)) {
            BookingDAO dao = new BookingDAO();
            List<Booking> bookings = dao.getBookingsByUserId(userId);
            request.setAttribute("bookings", bookings);
            request.getRequestDispatcher("/jsp/my-bookings.jsp").forward(request, response);
        } else {
            int destinationId = Integer.parseInt(request.getParameter("destinationId"));
            DestinationDAO destDao = new DestinationDAO();
            Destination destination = destDao.getDestinationById(destinationId);
            request.setAttribute("destination", destination);
            request.getRequestDispatcher("/jsp/booking. jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("login");
            return;
        }

        int destinationId = Integer.parseInt(request.getParameter("destinationId"));
        int numberOfPeople = Integer.parseInt(request.getParameter("numberOfPeople"));
        LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
        LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));
        String specialRequests = request.getParameter("specialRequests");

        DestinationDAO destDao = new DestinationDAO();
        Destination destination = destDao.getDestinationById(destinationId);

        long days = java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate);
        BigDecimal totalPrice = destination.getPricePerDay().multiply(BigDecimal.valueOf(days * numberOfPeople));

        Booking booking = new Booking();
        booking.setUserId(userId);
        booking.setDestinationId(destinationId);
        booking.setNumberOfPeople(numberOfPeople);
        booking.setStartDate(startDate);
        booking.setEndDate(endDate);
        booking.setTotalPrice(totalPrice);
        booking.setSpecialRequests(specialRequests);

        BookingDAO bookingDAO = new BookingDAO();
        if (bookingDAO.createBooking(booking)) {
            request.setAttribute("success", "Booking created successfully!");
            doGet(request, response);
        } else {
            request.setAttribute("error", "Booking failed.  Please try again.");
            request. getRequestDispatcher("/jsp/booking.jsp").forward(request, response);
        }
    }
}
