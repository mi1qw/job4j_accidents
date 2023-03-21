package com.example.job4j_accidents.repository.springdata;

import com.example.job4j_accidents.model.Authority;
import org.springframework.data.repository.CrudRepository;

public interface AuthorityRepository extends CrudRepository<Authority, Integer> {
    Authority findByAuthority(String authority);
}
