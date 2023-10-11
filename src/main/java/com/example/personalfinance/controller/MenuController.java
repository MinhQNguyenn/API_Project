package com.example.personalfinance.controller;

import com.example.personalfinance.model.Spending;
import com.example.personalfinance.dao.BudgetDaoImpl;
import com.example.personalfinance.dao.SavingDaoImpl;
import com.example.personalfinance.dao.SpendingDaoImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class MenuController {
    SpendingDaoImpl spendingDao = new SpendingDaoImpl();
    BudgetDaoImpl budgetDao = new BudgetDaoImpl();
    SavingDaoImpl savingDao = new SavingDaoImpl();
    int m_budget = 0, m_spending= 0, saving= 0;
    @GetMapping("/spending")
    public String spending(Model model){
        LocalDate localDate = LocalDate.now();
        List<Spending> spendingList = spendingDao.getAllSpendingById(1, localDate.getMonthValue(), localDate.getYear());
        m_budget = budgetDao.getMonthBudget(1, localDate.getMonthValue(), localDate.getYear());
        m_spending = spendingDao.getMonthSpending(1, localDate.getMonthValue(), localDate.getYear());
        saving = savingDao.getTotalSaving(1);
        model.addAttribute("m_budget", moneyconfig(m_budget));
        model.addAttribute("m_spending", moneyconfig(m_spending));
        model.addAttribute("m_saving", moneyconfig(m_budget-m_spending));
        model.addAttribute("totalSaving", moneyconfig(saving));
        model.addAttribute("spendingList",spendingList);

//      spendingList.forEach((n) -> System.out.println(n.toString()));
        return "Testing";
    }

    @PostMapping("/spending/add")
    public String addNewSpending(Model model){

        return "spending";
    }

//    @Configuration
//    public class ApplicationNoSecurity {
//        @Bean
//        public WebSecurityCustomizer webSecurityCustomizer() {
//            return (web) -> web.ignoring()
//                    .antMatchers("/**");
//        }
//    }

    public String moneyconfig(int number) {
        String old = "" + number;
        StringBuilder temp = new StringBuilder();
        int count = 0;
        for (int i = old.length() - 1; i >= 0; i--) {
            if (count % 3 == 0 && i != old.length() - 1) {
                temp.append(",").append(old.charAt(i));
            } else {
                temp.append(old.charAt(i));
            }
            count++;
        }
        return reverseString(temp.toString()) + " VND";
    }

    public String reverseString(String temp) {
        StringBuilder a = new StringBuilder();
        for (int i = temp.length() - 1; i >= 0; i--) {
            a.append(temp.charAt(i));
        }
        return a.toString();
    }
}
