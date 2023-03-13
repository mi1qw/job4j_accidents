package com.example.job4j_accidents.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class IndexController {
    @GetMapping("/")
    public String index(final Model model) {
        model.addAttribute("user", "Petr Arsentev");
        return "index";
    }
}
