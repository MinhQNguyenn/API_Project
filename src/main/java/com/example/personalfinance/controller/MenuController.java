package com.example.personalfinance.controller;

import com.example.personalfinance.model.Spending;
import dao.BudgetDaoImpl;
import dao.SavingDaoImpl;
import dao.SpendingDao;
import dao.SpendingDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Controller
public class MenuController {
    SpendingDaoImpl spendingDao = new SpendingDaoImpl();
    BudgetDaoImpl budgetDao = new BudgetDaoImpl();
    SavingDaoImpl savingDao = new SavingDaoImpl();
    int m_budget = 0, m_spending=0, saving=0;
    @GetMapping("/userspending")
    public String spending(Model model){
        LocalDate localDate = LocalDate.now();
        List<Spending> spendingList = spendingDao.getAllSpendingById(1, localDate.getMonthValue(), localDate.getYear());
        m_budget = budgetDao.getMonthBudget(1, localDate.getMonthValue(), localDate.getYear());
        m_spending = spendingDao.getMonthSpending(1, localDate.getMonthValue(), localDate.getYear());
        saving = savingDao.getTotalSaving(1);
        model.addAttribute("m_budget", m_budget);
        model.addAttribute("m_spending", m_spending);
        model.addAttribute("m_saving", m_budget-m_spending);
        model.addAttribute("totalSaving", saving);
        model.addAttribute("spendingList",spendingList);
        return "spending";
    }

    @Configuration
    public class ApplicationNoSecurity {

        @Bean
        public WebSecurityCustomizer webSecurityCustomizer() {
            return (web) -> web.ignoring()
                    .antMatchers("/**");
        }
    }
}
