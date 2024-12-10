package com.javaprojet.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProgrammeurController {

    @GetMapping("/")
    public String home() {
        return "home";
    }
}
