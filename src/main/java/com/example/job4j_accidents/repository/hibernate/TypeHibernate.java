package com.example.job4j_accidents.repository.hibernate;

import com.example.job4j_accidents.model.AccidentType;
import com.example.job4j_accidents.repository.SessionWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class TypeHibernate {
    private final SessionWrapper wrapper;

    public List<AccidentType> findAll() {
        return wrapper.tx(session ->
                session.createNativeQuery("select * from type",
                                AccidentType.class)
                        .list());
    }
}
