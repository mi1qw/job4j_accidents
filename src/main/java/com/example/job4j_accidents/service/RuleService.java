package com.example.job4j_accidents.service;

import com.example.job4j_accidents.model.Rule;
import com.example.job4j_accidents.repository.RulesMem;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class RuleService {
    private final RulesMem rulesMem;

    public Set<Rule> findAll() {
        return rulesMem.findAll();
    }

    public Rule findById(final int id) {
        return rulesMem.findById(id);
    }
}
