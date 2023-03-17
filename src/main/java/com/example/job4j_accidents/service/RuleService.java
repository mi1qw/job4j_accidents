package com.example.job4j_accidents.service;

import com.example.job4j_accidents.model.Rule;
import com.example.job4j_accidents.repository.hibernate.RulesHibernate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RuleService {
    private final Map<Integer, Rule> rules = new ConcurrentHashMap<>();
    private final RulesHibernate rulesHibernate;

    public RuleService(final RulesHibernate rulesHibernate) {
        this.rulesHibernate = rulesHibernate;
        init();
    }

    private void init() {
        rulesHibernate.findAll()
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
