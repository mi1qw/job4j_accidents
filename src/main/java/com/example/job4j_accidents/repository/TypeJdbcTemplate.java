package com.example.job4j_accidents.repository;

import com.example.job4j_accidents.model.AccidentType;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class TypeJdbcTemplate {
    private final JdbcTemplate jdbc;

    public List<AccidentType> findAll() {
        return jdbc.query("select * from type", (rs, rw) -> {
            AccidentType type = new AccidentType();
            type.setId(rs.getInt("id"));
            type.setName(rs.getString("name"));
            return type;
        });
    }
}
