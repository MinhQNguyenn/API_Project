package com.example.personalfinance.controller;

import com.example.personalfinance.dao.AccountImpl;
import com.example.personalfinance.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private HttpServletRequest request;
    private final AccountImpl account = new AccountImpl();
    @PostMapping("/login")
    public String accessController(HttpSession session,
                                   @RequestParam("email")String email,
                                   @RequestParam("password")String password){
        boolean check=false;
        Account login = account.getAccount(email, password);
        if(login != null) check=true;
        if(check){
            session.setAttribute("uid", login.getId());
            session.setAttribute("uname", login.getName());
            return "redirect:/spending";
        }
        else{
            return "loginPage";
        }
    }


    @GetMapping("/login")
    public String loginRedirect(){
        return "loginPage";
    }

    @GetMapping("/logout")
    public String logout(){
        HttpSession session = request.getSession(false);
        if(session != null) session.invalidate();
        return "loginPage";
    }
}
