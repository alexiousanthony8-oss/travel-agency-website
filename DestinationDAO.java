package com.travelagency.dao;

import com.travelagency.models.Destination;
import com.travelagency. utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DestinationDAO {

    public Destination getDestinationById(int destinationId) {
        String sql = "SELECT * FROM destinations WHERE destination_id = ?";
        try (Connection conn = DatabaseConnection. getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, destinationId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToDestination(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Destination> getAllDestinations() {
        List<Destination> destinations = new ArrayList<>();
        String sql = "SELECT * FROM destinations";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt. executeQuery(sql);
            while (rs.next()) {
                destinations.add(mapResultSetToDestination(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return destinations;
    }

    public List<Destination> getDestinationsByCountry(String country) {
        List<Destination> destinations = new ArrayList<>();
        String sql = "SELECT * FROM destinations WHERE country = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn. prepareStatement(sql)) {
            pstmt.setString(1, country);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                destinations.add(mapResultSetToDestination(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return destinations;
    }

    public boolean createDestination(Destination destination) {
        String sql = "INSERT INTO destinations (name, description, country, city, price_per_day, duration_days, image_url, highlights, best_season) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt. setString(1, destination.getName());
            pstmt.setString(2, destination.getDescription());
            pstmt.setString(3, destination.getCountry());
            pstmt. setString(4, destination.getCity());
            pstmt. setBigDecimal(5, destination. getPricePerDay());
            pstmt.setInt(6, destination.getDurationDays());
            pstmt.setString(7, destination.getImageUrl());
            pstmt.setString(8, destination.getHighlights());
            pstmt.setString(9, destination. getBestSeason());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateDestination(Destination destination) {
        String sql = "UPDATE destinations SET name = ?, description = ?, country = ?, city = ?, price_per_day = ?, duration_days = ?, image_url = ?, highlights = ?, best_season = ?  WHERE destination_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, destination.getName());
            pstmt.setString(2, destination.getDescription());
            pstmt.setString(3, destination.getCountry());
            pstmt.setString(4, destination.getCity());
            pstmt.setBigDecimal(5, destination.getPricePerDay());
            pstmt.setInt(6, destination. getDurationDays());
            pstmt.setString(7, destination.getImageUrl());
            pstmt.setString(8, destination.getHighlights());
            pstmt.setString(9, destination.getBestSeason());
            pstmt. setInt(10, destination.getDestinationId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteDestination(int destinationId) {
        String sql = "DELETE FROM destinations WHERE destination_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, destinationId);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Destination mapResultSetToDestination(ResultSet rs) throws SQLException {
        Destination destination = new Destination();
        destination.setDestinationId(rs.getInt("destination_id"));
        destination.setName(rs.getString("name"));
        destination.setDescription(rs.getString("description"));
        destination.setCountry(rs.getString("country"));
        destination.setCity(rs.getString("city"));
        destination.setPricePerDay(rs.getBigDecimal("price_per_day"));
        destination. setDurationDays(rs. getInt("duration_days"));
        destination.setImageUrl(rs.getString("image_url"));
        destination.setHighlights(rs.getString("highlights"));
        destination.setBestSeason(rs.getString("best_season"));
        return destination;
    }
}
