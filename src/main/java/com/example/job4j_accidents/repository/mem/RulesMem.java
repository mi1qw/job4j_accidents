package com.example.job4j_accidents.repository.mem;

import com.example.job4j_accidents.model.Rule;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class RulesMem {
    private final Map<Integer, Rule> rules = new ConcurrentHashMap<>();

    public RulesMem() {
        rules.put(1, new Rule(1, "Статья. 1"));
        rules.put(2, new Rule(2, "Статья. 2"));
        rules.put(3, new Rule(3, "Статья. 3"));
    }

    public Set<Rule> findAll() {
        return new HashSet<>(rules.values());
    }

    public Rule findById(final int id) {
        return rules.get(id);
    }
}
