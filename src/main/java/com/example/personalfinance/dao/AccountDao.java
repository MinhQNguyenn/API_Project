package com.example.personalfinance.dao;

import com.example.personalfinance.model.Account;

public interface AccountDao {
    public Account getAccount(String email, String password);
}
