package com.example.personalfinance.dao;

import com.example.personalfinance.model.Spending;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class SpendingImpl implements SpendingDao
{
    HttpSession session;
    private final String url = "jdbc:postgresql://localhost:5432/PersonalFinance";
    private final String user = "sa";
    private final String password = "password";
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<Spending> getAllSpendingById(int userId, int month, int year) {
        //SQL command for this function
        String sql = "SELECT * FROM getuserspending(?, ?, ?)";
//        Object[] args = new Object[] { userId, month, year};
//        List<Spending> spendingList = jdbcTemplate.query(sql, new SpendingRowMapper());
//        return spendingList;
        List<Spending> spendingList = new ArrayList<>();

        //Open connection & Retrieve data from database
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
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return spendingList;
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
                totalSpending += (rs.getInt(1));
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return totalSpending;
    }

    @Override
    public List<Spending> getAllSpendingByIdDesc(int userId, int month, int year) {
        String sql = "SELECT * FROM getuserspendingdesc(?, ?, ?)";
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
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }return spendingList;
    }

    @Override
    public List<Spending> getAllSpendingByIdAsc(int userId, int month, int year) {
        String sql = "SELECT * FROM getuserspendingasc(?, ?, ?)";
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
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }return spendingList;
    }

    public void deleteSpending(int userId, int id) {
        String sql = "SELECT FROM deleteSpending(?, ?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, id);
            pstmt.execute();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void addSpending(int amount, Date date_of_spending, String detail, String name, String note, int account_id) {
        String sql = "SELECT FROM addSpending(?, ?, ?, ?, ?, ?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, amount);
            pstmt.setDate(2, new java.sql.Date(date_of_spending.getTime()) );
            pstmt.setString(3, detail);
            pstmt.setString(4, name);
            pstmt.setString(5, note);
            pstmt.setInt(6, account_id);
            pstmt.execute();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Spending getSpendingInfo(int id, int accountId) {
        String sql = "SELECT * FROM getSpending(?, ?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setInt(2, accountId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int amount = (rs.getInt("amount"));
                Date dateOfSpending = (rs.getDate("date_of_spending"));
                String detail = (rs.getString("detail"));
                int mode = (rs.getInt("mode"));
                String name = (rs.getString("name"));
                String note = (rs.getString("note"));
                return new Spending(id, amount, name, dateOfSpending, detail, mode, note);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }return null;
    }

    @Override
    public void realDelete(int userId, int id) {
        String sql = "SELECT FROM realDelete(?, ?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, id);
            pstmt.execute();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Integer> getAvailableYear(int userId) {
        List<Integer> yearList = new ArrayList<>();
        String sql = "select distinct EXTRACT(YEAR FROM date_of_spending) as year from spending where account_id=?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                yearList.add(rs.getInt("year"));
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return yearList;
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
