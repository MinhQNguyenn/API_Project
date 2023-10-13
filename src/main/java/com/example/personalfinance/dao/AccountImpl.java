package com.example.personalfinance.dao;

import com.example.personalfinance.model.Account;
import com.example.personalfinance.model.Spending;

import java.sql.*;
import java.util.Date;

public class AccountImpl implements AccountDao{
    private final String url = "jdbc:postgresql://localhost:5432/PersonalFinance";
    private final String user = "sa";
    private final String password = "password";
    @Override
    public Account getAccount(String email, String password) {
        String sql = "Select * from getAccountID(?,?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            pstmt.setString(2,password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = (rs.getInt(1));
                String name = (rs.getString(2));
                return new Account(id, name);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private Connection connect() throws SQLException{
        return DriverManager.getConnection(url, user, password);
    }

}
