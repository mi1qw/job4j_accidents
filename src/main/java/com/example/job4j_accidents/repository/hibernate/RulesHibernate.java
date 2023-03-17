package com.example.job4j_accidents.repository.hibernate;

import com.example.job4j_accidents.model.Rule;
import com.example.job4j_accidents.repository.SessionWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class RulesHibernate {
    private final SessionWrapper wrapper;

    public List<Rule> findAll() {
        return wrapper.tx(session ->
                session.createQuery("from Rule", Rule.class)
                        .list());
    }
}
