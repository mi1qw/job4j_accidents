package com.example.job4j_accidents.controller;

import com.example.job4j_accidents.model.Accident;
import com.example.job4j_accidents.service.AccidentService;
import com.example.job4j_accidents.service.AccidentTypeService;
import com.example.job4j_accidents.service.RuleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@Slf4j
@AllArgsConstructor
public class AccidentController {
    private final AccidentService accidentService;
    private final AccidentTypeService types;
    private final RuleService rules;

    @GetMapping("/createAccident")
    public String viewCreateAccident(final Model model) {
        model.addAttribute("types", types.findAll());
        model.addAttribute("rules", rules.findAll());
        return "createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(final @ModelAttribute("accident") Accident accident,
                       final @RequestParam("type.id") int typeId,
                       final @RequestParam("rIds") List<Integer> rIds) {
        accidentService.create(accident, typeId, rIds);
        return "redirect:/";
    }

    @GetMapping("/formUpdateAccident")
    public String update(final @RequestParam("id") int id, final Model model) {
        model.addAttribute("accident", accidentService.findById(id).get());
        model.addAttribute("types", types.findAll());
        model.addAttribute("rulesAll", rules.findAll());
        return "formUpdateAccident";
    }

    @PostMapping("/updateAccident")
    public String update(final @ModelAttribute("accident") Accident accident,
                         final @RequestParam(name = "type.id") int typeId,
                         final @RequestParam("rIds") List<Integer> rIds) {
        accidentService.update(accident, typeId, rIds);
        return "redirect:/";
    }
}
