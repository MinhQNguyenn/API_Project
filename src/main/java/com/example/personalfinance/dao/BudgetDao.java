package com.example.personalfinance.dao;

public interface BudgetDao {
    public int getMonthBudget(int userId, int month, int year);
    public void setBudget(int userId, int month, int year, int amount);
}
