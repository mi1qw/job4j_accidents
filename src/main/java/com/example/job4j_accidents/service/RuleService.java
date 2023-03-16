package com.example.job4j_accidents.service;

import com.example.job4j_accidents.model.Rule;
import com.example.job4j_accidents.repository.RulesJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RuleService {
    private final Map<Integer, Rule> rules = new ConcurrentHashMap<>();
    private final RulesJdbcTemplate rulesJdbc;

    public RuleService(final RulesJdbcTemplate rulesJdbc) {
        this.rulesJdbc = rulesJdbc;
        init();
    }

    private void init() {
        rulesJdbc.findAll()
                .forEach(rule -> rules.put(rule.getId(), rule));
    }

    public List<Rule> findAll() {
        return rules.values().stream().toList();
    }

    public Rule findById(final int id) {
        Rule rule = rules.get(id);
        return new Rule(rule.getId(), rule.getName());
    }
}
