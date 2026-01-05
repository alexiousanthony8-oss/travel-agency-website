package com.travelagency. servlets;

import com.travelagency.dao.DestinationDAO;
import com. travelagency.models. Destination;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet. http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/destinations")
public class DestinationServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("detail".equals(action)) {
            int destinationId = Integer.parseInt(request.getParameter("id"));
            DestinationDAO dao = new DestinationDAO();
            Destination destination = dao. getDestinationById(destinationId);
            request.setAttribute("destination", destination);
            request.getRequestDispatcher("/jsp/destination-detail.jsp").forward(request, response);
        } else {
            DestinationDAO dao = new DestinationDAO();
            List<Destination> destinations = dao. getAllDestinations();
            request. setAttribute("destinations", destinations);
            request.getRequestDispatcher("/jsp/destinations.jsp").forward(request, response);
        }
    }
}
