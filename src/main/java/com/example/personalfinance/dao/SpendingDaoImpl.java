package com.example.personalfinance.dao;

import com.example.personalfinance.model.Spending;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SpendingDaoImpl implements SpendingDao
{
    private final String url = "jdbc:postgresql://localhost:5432/PersonalFinance";
    private final String user = "sa";
    private final String password = "password";
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<Spending> getAllSpendingById(int userId, int month, int year) {
        String sql = "SELECT * FROM getuserspending(?, ?, ?)";
//        Object[] args = new Object[] { userId, month, year};
//        List<Spending> spendingList = jdbcTemplate.query(sql, new SpendingRowMapper());
//        return spendingList;
        List<Spending> spendingList = new ArrayList<>();
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            pstmt.setInt(2,month);
            pstmt.setInt(3,year);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = (rs.getInt("id"));
                int amount = (rs.getInt("amount"));
                Date dateOfSpending = (rs.getDate("date_of_spending"));
                String detail = (rs.getString("detail"));
                int mode = (rs.getInt("mode"));
                String name = (rs.getString("name"));
                String note = (rs.getString("note"));
                spendingList.add(new Spending(id, amount, name, dateOfSpending, detail, mode, note));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }return spendingList;
     }

    private Connection connect() throws SQLException{
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public int getMonthSpending(int userId, int month, int year) {
        String sql = "select getMonthSpending(?, ?, ?)";
//        Object[] args = new Object[] { userId, month, year};
//        int totalSpending = jdbcTemplate.queryForObject(sql, Integer.class);
//        return totalSpending;
        int totalSpending =0;
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            pstmt.setInt(2,month);
            pstmt.setInt(3,year);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                totalSpending = (rs.getInt(1));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }return totalSpending;
    }

    private class SpendingRowMapper implements RowMapper<Spending> {
        @Override
        public Spending mapRow(ResultSet rs, int rowNum) throws SQLException {
            Spending spending = new Spending();
            spending.setId(rs.getInt("id"));
            spending.setAmount(rs.getInt("amount"));
            spending.setDateOfSpending(rs.getDate("date_of_spending"));
            spending.setDetail(rs.getString("detail"));
            spending.setMode(rs.getInt("mode"));
            spending.setName(rs.getString("name"));
            spending.setNote(rs.getString("note"));
            return spending;
        }
    }

}
