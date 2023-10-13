package com.example.personalfinance.dao;

import com.example.personalfinance.model.Spending;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

public interface SpendingDao{
    public List<Spending> getAllSpendingById(int userId, int month, int year);
    public int getMonthSpending(int userId, int month, int year);

    public List<Spending> getAllSpendingByIdDesc(int userId, int month, int year);
    public List<Spending> getAllSpendingByIdAsc(int userId, int month, int year);
    public void deleteSpending(int userId, int id);

    public void addSpending(int amount, Date date_of_spending, String detail, String name, String note, int account_id);

    public Spending getSpendingInfo(int id, int accountId);

    public void realDelete(int userId, int id);
}
