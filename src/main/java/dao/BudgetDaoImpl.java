package dao;

import com.example.personalfinance.model.Budget;
import com.example.personalfinance.model.Spending;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.*;
import java.util.Date;
import java.util.List;

public class BudgetDaoImpl implements BudgetDao{
    JdbcTemplate jdbcTemplate = new JdbcTemplate();
    private final String url = "jdbc:postgresql://localhost:5432/PersonalFinance";
    private final String user = "sa";
    private final String password = "password";
    @Override
    public int getMonthBudget(int userId, int month, int year) {
        String sql = "select getMonthBudget(?, ?, ?)";
//        Object[] args = new Object[] { userId, month, year};
//        int budget = Integer.parseInt(jdbcTemplate.queryForObject(sql, String.class));
//        return budget;
        int budget =0;
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            pstmt.setInt(2,month);
            pstmt.setInt(3,year);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                budget = (rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }return budget;
    }

    private Connection connect() throws SQLException{
        return DriverManager.getConnection(url, user, password);
    }
}

