package com.example.personalfinance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {

    @GetMapping ("/login")
    public String home(){
        return "redirect:/login";
    }
//
//    @GetMapping("/secured")
//    public String secured(){
//        return "Welcome secured";
//    }


}
