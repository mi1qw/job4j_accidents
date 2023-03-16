package com.example.job4j_accidents.service;

import com.example.job4j_accidents.model.AccidentType;
import com.example.job4j_accidents.repository.TypeJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AccidentTypeService {
    private final Map<Integer, AccidentType> types = new ConcurrentHashMap<>();
    private final TypeJdbcTemplate typeJdbc;

    public AccidentTypeService(final TypeJdbcTemplate typeJdbc) {
        this.typeJdbc = typeJdbc;
        init();
    }

    private void init() {
        typeJdbc.findAll()
                .forEach(type -> types.put(type.getId(), type));
    }

    public List<AccidentType> findAll() {
        return types.values().stream().toList();
    }

    public AccidentType findById(final int id) {
        AccidentType type = types.get(id);
        return new AccidentType(type.getId(), type.getName());
    }
}
