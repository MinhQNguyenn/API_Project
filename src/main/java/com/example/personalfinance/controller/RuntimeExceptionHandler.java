package com.example.personalfinance.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

public class RuntimeExceptionHandler extends RuntimeException{
        @ExceptionHandler(RuntimeExceptionHandler.class)
        public String handleResourceNotFoundException(RuntimeExceptionHandler ex, RedirectAttributes redirectAttributes) {
            redirectAttributes.addFlashAttribute("error", "Resource not found");
            return "redirect:/error";
        }
        @GetMapping("/error")
        public String handleError(){
            return "404";
        }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleValidationException(MethodArgumentNotValidException ex) {
        return "400";
    }

}
