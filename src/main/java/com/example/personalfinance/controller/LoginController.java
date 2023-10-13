package com.example.personalfinance.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

public class LoginController {
    @PostMapping("/login")
    public String loginController(HttpSession session){
        boolean check=true;
        if(check){
            session.setAttribute("uid", 1);
            return "redirect:/spending";
        }
        else{
            return "loginPage";
        }
    }

    @GetMapping("/login")
    public String loginRedirect(){
        System.out.println("Here ");
        return "loginPage";
    }
}
