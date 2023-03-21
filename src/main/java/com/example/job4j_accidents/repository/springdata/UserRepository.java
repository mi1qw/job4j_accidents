package com.example.job4j_accidents.repository.springdata;

import com.example.job4j_accidents.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
