package com.example.job4j_accidents.controller;

import com.example.job4j_accidents.model.User;
import com.example.job4j_accidents.repository.springdata.AuthorityRepository;
import com.example.job4j_accidents.repository.springdata.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class RegController {
    private final PasswordEncoder encoder;
    private final UserRepository users;
    private final AuthorityRepository authorities;

    @PostMapping("/reg")
    public String regSave(final @ModelAttribute User user, final Model model) {
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(authorities.findByAuthority("ROLE_USER"));
        try {
            users.save(user);
            return "redirect:/login";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errorMessage",
                    "Пользователь с таким именем уже существует");
        }
        return "reg";
    }

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }
}
