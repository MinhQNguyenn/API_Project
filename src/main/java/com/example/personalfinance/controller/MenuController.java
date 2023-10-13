package com.example.personalfinance.controller;

import com.example.personalfinance.model.Spending;
import com.example.personalfinance.dao.BudgetImpl;
import com.example.personalfinance.dao.SavingImpl;
import com.example.personalfinance.dao.SpendingImpl;
import com.example.personalfinance.service.SpendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class MenuController {
    private SpendingService spendingService = new SpendingService();
    private SpendingImpl spendingDao = new SpendingImpl();
    private BudgetImpl budgetDao = new BudgetImpl();
    private SavingImpl savingDao = new SavingImpl();
    private LocalDate localDate = LocalDate.now();
    private int m_budget = 0, m_spending= 0, saving= 0;

    //For checking whether the update request is sent
    private boolean isUpdate = false;
    //Setting the id for the updated expense
    private int updateId=0;
    @GetMapping("/spending")
    public String spending(Model model,
                           @RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size,
                           @SessionAttribute("uid") int uid){
        //Assume automatically if the current page and its size is not specified
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        //Getting the content for the expense list of the user
        Page<Spending> spendingList = spendingService.findPaginated(PageRequest.of(currentPage - 1, pageSize), 1, localDate.getMonthValue(), localDate.getYear());

        //Getting the content for the 4 charts
        m_budget = budgetDao.getMonthBudget(1, localDate.getMonthValue(), localDate.getYear());
        m_spending = spendingDao.getMonthSpending(1, localDate.getMonthValue(), localDate.getYear());
        saving = savingDao.getTotalSaving(1);

        //Configure the contents to send to the view page
        model.addAttribute("m_budget", moneyconfig(m_budget));
        model.addAttribute("m_spending", moneyconfig(m_spending));
        model.addAttribute("m_saving", moneyconfig(m_budget-m_spending));
        model.addAttribute("totalSaving", moneyconfig(saving));
        model.addAttribute("spendingList",spendingList);

        //If the user want to update
        if(isUpdate){
            Spending spending = spendingDao.getSpendingInfo(updateId, 1);
            model.addAttribute("spendingInfo", spending);
            isUpdate=false;
        }

        int totalPages = spendingList.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "Testing";
    }

    @PostMapping("/spending/add")
    public String addNewSpending(@RequestParam("name") String name,
                                 @RequestParam("detail") String detail,
                                 @RequestParam("amount") String amount,
                                 @RequestParam("date") String date,
                                 @RequestParam("note") String note){
        //Check whether the note is empty and assign value to it if it's
        if(note.isEmpty()) note ="";

        int price = Integer.parseInt(amount);
        Date dateOfSpending = dateStringConvertor(date);

        spendingDao.addSpending(price, dateOfSpending, detail, name, note, 1);
        return "redirect:/spending";
    }

    @PostMapping("/spending/update")
    public String getExpenseInfo(@RequestParam("name") String name,
                                 @RequestParam("detail") String detail,
                                 @RequestParam("amount") String amount,
                                 @RequestParam("date") String date,
                                 @RequestParam("note") String note){
        //Check whether the note is empty and assign value to it if it's
        if(note.isEmpty()) note ="";
        int price = Integer.parseInt(amount);
        Date dateOfSpending = dateStringConvertor(date);

        //Check if there's any change in the data
        Spending spending = spendingDao.getSpendingInfo(updateId, 1);
        Spending temp = new Spending(updateId, price, name, dateOfSpending, detail, 1, note);
        if(!temp.equals(spending)){
            //Delete the expense from the database
            spendingDao.realDelete(1, updateId);

            //Add a new expense with the value given as before
            spendingDao.addSpending(price, dateOfSpending, detail, name, note, 1);
        }else System.out.println("No change made!!!!!!!!!");
        return "redirect:/spending";
    }

    @GetMapping("/spending/update/{id}")
    public String getExpenseInfo(@PathVariable String id, Model model){
        isUpdate = true;
        updateId = Integer.parseInt(id);
//        Spending spending = spendingDao.getSpendingInfo(newID, 1);
//        model.addAttribute("spendingInfo", spending);
        return "redirect:/spending";
    }

    @GetMapping("/spending/budget")
    public String setBudget(@RequestParam("budget") String budget){
        budgetDao.setBudget(1, localDate.getMonthValue(), localDate.getYear(), Integer.parseInt(budget));
        return "redirect:/spending";
    }

//    @Configuration
//    public class ApplicationNoSecurity {
//        @Bean
//        public WebSecurityCustomizer webSecurityCustomizer() {
//            return (web) -> web.ignoring()
//                    .antMatchers("/**");
//        }
//    }

    private Date dateStringConvertor(String s){
        String formatPattern = "yyyy-MM-dd";
        Date date;
        try {
            DateFormat dateFormat = new SimpleDateFormat(formatPattern);
            date = dateFormat.parse(s);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }return null;
    }

//    @GetMapping("/spending/filter/{filter}")
//    public String filterSpending(@PathVariable String filter, Model model){
//        List<Spending> spendingList;
//        if(filter.compareTo("0")==0){
//            spendingList = spendingDao.getAllSpendingByIdDesc(1, localDate.getMonthValue(), localDate.getYear());
//        }else{
//            spendingList = spendingDao.getAllSpendingByIdAsc(1, localDate.getMonthValue(), localDate.getYear());
//        }
//        m_budget = budgetDao.getMonthBudget(1, localDate.getMonthValue(), localDate.getYear());
//        m_spending = spendingDao.getMonthSpending(1, localDate.getMonthValue(), localDate.getYear());
//        saving = savingDao.getTotalSaving(1);
//        model.addAttribute("m_budget", moneyconfig(m_budget));
//        model.addAttribute("m_spending", moneyconfig(m_spending));
//        model.addAttribute("m_saving", moneyconfig(m_budget-m_spending));
//        model.addAttribute("totalSaving", moneyconfig(saving));
//        model.addAttribute("spendingList",spendingList);
//
//        return "Testing";
//    }

    @GetMapping("/spending/delete/{id}")
    public String deleteSpending(@PathVariable String id){
        int temp = Integer.parseInt(id);
        spendingDao.deleteSpending(1, temp);
        return "redirect:/spending";
    }



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
