package com.example.personalfinance.dao;

public interface BudgetDao {
    public int getMonthBudget(int userId, int month, int year);
}
