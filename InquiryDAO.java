package com.travelagency.dao;

import com. travelagency.models. Inquiry;
import com.travelagency.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InquiryDAO {

    public boolean createInquiry(Inquiry inquiry) {
        String sql = "INSERT INTO inquiries (name, email, phone, subject, message) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, inquiry. getName());
            pstmt. setString(2, inquiry.getEmail());
            pstmt. setString(3, inquiry.getPhone());
            pstmt. setString(4, inquiry.getSubject());
            pstmt.setString(5, inquiry. getMessage());
            pstmt. executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Inquiry> getAllInquiries() {
        List<Inquiry> inquiries = new ArrayList<>();
        String sql = "SELECT * FROM inquiries ORDER BY created_at DESC";
        try (Connection conn = DatabaseConnection. getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                inquiries.add(mapResultSetToInquiry(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inquiries;
    }

    public List<Inquiry> getInqueriesByStatus(String status) {
        List<Inquiry> inquiries = new ArrayList<>();
        String sql = "SELECT * FROM inquiries WHERE status = ? ORDER BY created_at DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn. prepareStatement(sql)) {
            pstmt.setString(1, status);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                inquiries.add(mapResultSetToInquiry(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inquiries;
    }

    public boolean updateInquiryStatus(int inquiryId, String status) {
        String sql = "UPDATE inquiries SET status = ? WHERE inquiry_id = ? ";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, status);
            pstmt.setInt(2, inquiryId);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Inquiry mapResultSetToInquiry(ResultSet rs) throws SQLException {
        Inquiry inquiry = new Inquiry();
        inquiry.setInquiryId(rs. getInt("inquiry_id"));
        inquiry.setName(rs. getString("name"));
        inquiry.setEmail(rs.getString("email"));
        inquiry.setPhone(rs.getString("phone"));
        inquiry.setSubject(rs. getString("subject"));
        inquiry.setMessage(rs.getString("message"));
        inquiry.setStatus(rs.getString("status"));
        return inquiry;
    }
}
