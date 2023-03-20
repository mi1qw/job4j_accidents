package com.example.job4j_accidents.controller;

import com.example.job4j_accidents.service.AccidentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@AllArgsConstructor
public class IndexController {
    private final AccidentService accidentService;
    @GetMapping("/")
    public String index(final Model model) {
        User principal = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        model.addAttribute("user", principal.getUsername());
        model.addAttribute("accidents", accidentService.allAccidents());
        return "index";
    }
}
