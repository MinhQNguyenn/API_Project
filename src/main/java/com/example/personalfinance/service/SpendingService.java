package com.example.personalfinance.service;


import com.example.personalfinance.dao.SpendingImpl;
import com.example.personalfinance.model.Spending;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpendingService {

    SpendingImpl spending = new SpendingImpl();
    List<Spending> temp = new ArrayList<>();

    public Page<Spending> findPaginated(Pageable pageable, int userId, int month, int year){
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        //Get the data required for
        List<Spending> spendingList = spending.getAllSpendingByIdDesc(userId, month, year);

        //Reformat the data retrieved based on the page
        if(spendingList.size() < startItem){
            temp = Collections.emptyList();
        }else{
            int toIndex = Math.min(startItem + pageSize, spendingList.size());
            temp = spendingList.subList(startItem, toIndex);
        }

        return new PageImpl<Spending>(temp, PageRequest.of(currentPage, pageSize), spendingList.size());
    }

}
