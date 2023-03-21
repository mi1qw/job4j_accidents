package com.example.job4j_accidents.repository.springdata;

import com.example.job4j_accidents.model.Accident;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface AccidentRepository extends ListCrudRepository<Accident, Integer> {
    @EntityGraph(value = "AccidentTypeRules")
    List<Accident> findAll();

    @EntityGraph(attributePaths = {"type", "rules"})
    Optional<Accident> findById(int id);
}
