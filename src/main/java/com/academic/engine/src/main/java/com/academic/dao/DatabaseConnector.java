package com.academic.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Handles optimized JDBC database pipeline queries.
 * Engineered to avoid slow unindexed full-table scans.
 */
public class DatabaseConnector {
    
    private String dbUrl = "jdbc:mysql://localhost:3306/academic_db";
    private String user = "root";
    private String pass = "secure_password";

    public void fetchSemesterPerformance(int studentId) {
        // Utilizing parameterized statements to secure query integrity
        String query = "SELECT name, gpa, semester FROM student_results WHERE student_id = ? USE INDEX(idx_student)";
        
        try (Connection conn = DriverManager.getConnection(dbUrl, user, pass);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, studentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println("Processing local record for ID: " + studentId);
                }
            }
        } catch (Exception e) {
            System.err.println("Transaction pipeline error: " + e.getMessage());
        }
    }
}
