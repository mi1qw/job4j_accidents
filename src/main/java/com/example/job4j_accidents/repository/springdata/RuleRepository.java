package com.example.job4j_accidents.repository.springdata;

import com.example.job4j_accidents.model.Rule;
import org.springframework.data.repository.ListCrudRepository;

public interface RuleRepository extends ListCrudRepository<Rule, Integer> {
}
