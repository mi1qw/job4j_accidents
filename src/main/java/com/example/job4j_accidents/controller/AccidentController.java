package com.example.job4j_accidents.controller;

import com.example.job4j_accidents.model.Accident;
import com.example.job4j_accidents.service.AccidentService;
import com.example.job4j_accidents.service.AccidentTypeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@Slf4j
@AllArgsConstructor
public class AccidentController {
    private final AccidentService accidentService;
    private final AccidentTypeService types;

    @GetMapping("/createAccident")
    public String viewCreateAccident(final Model model) {
        model.addAttribute("types", types.allAccidentTypes());
        return "createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(final @ModelAttribute Accident accident,
                       final @RequestParam("type.id") int typeId) {
        accident.setType(types.findById(typeId));
        accidentService.create(accident);
        return "redirect:/";
    }

    @GetMapping("/formUpdateAccident")
    public String update(final @RequestParam("id") int id, final Model model) {
        model.addAttribute("accident", accidentService.findById(id).get());
        model.addAttribute("types", types.allAccidentTypes());
        return "formUpdateAccident";
    }

    @PostMapping("/updateAccident")
    public String update(final @ModelAttribute Accident accident,
                         final @RequestParam(name = "type.id") int typeId) {
        accident.setType(types.findById(typeId));
        accidentService.update(accident);
        return "redirect:/";
    }
}
