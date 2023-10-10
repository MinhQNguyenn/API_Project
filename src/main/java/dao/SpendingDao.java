package dao;

import com.example.personalfinance.model.Spending;

import java.util.List;

public interface SpendingDao {
    public List<Spending> getAllSpendingById(int userId, int month, int year);
    public int getMonthSpending(int userId, int month, int year);
}
