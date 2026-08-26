
package com.example.fullness.stationary.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.fullness.stationary.exception.ProductSearchException;

@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(ProductSearchException.class)
    public String handleProductSearchException(ProductSearchException e,
            RedirectAttributes redrirectAttributes) {
        redrirectAttributes.addFlashAttribute(
                "errorMessage", e.getMessage());
        return "redirect:/error";
    }

}