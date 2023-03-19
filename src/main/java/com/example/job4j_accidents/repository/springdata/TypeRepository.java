package com.example.job4j_accidents.repository.springdata;

import com.example.job4j_accidents.model.AccidentType;
import org.springframework.data.repository.ListCrudRepository;

public interface TypeRepository extends ListCrudRepository<AccidentType, Integer> {
}
