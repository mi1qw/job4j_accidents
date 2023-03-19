package com.example.job4j_accidents.repository.springdata;

import com.example.job4j_accidents.model.Accident;
import org.springframework.data.repository.ListCrudRepository;

public interface AccidentRepository extends ListCrudRepository<Accident, Integer> {
}
