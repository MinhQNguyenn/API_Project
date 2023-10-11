package com.example.personalfinance.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;

public class SavingDaoImpl implements SavingDao{
    JdbcTemplate jdbcTemplate = new JdbcTemplate();
    private final String url = "jdbc:postgresql://localhost:5432/PersonalFinance";
    private final String user = "sa";
    private final String password = "password";
    @Override
    public int getTotalSaving(int userId) {
        String sql = "Select getTotalSaving(?)";
        int totalSaving = 0;
//        Object[] args = new Object[] { userId};
//        totalSpending = Integer.parseInt(jdbcTemplate.queryForObject(sql, String.class));
//        return totalSaving;
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                totalSaving = (rs.getInt(1));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }return totalSaving;
    }

    private Connection connect() throws SQLException{
        return DriverManager.getConnection(url, user, password);
    }
}
