package com.example.job4j_accidents.repository;

import com.example.job4j_accidents.model.AccidentType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccidentTypeMem {
    private final Map<Integer, AccidentType> accidentTypes = new ConcurrentHashMap<>();

    public AccidentTypeMem() {
        accidentTypes.put(1, new AccidentType(1, "Две машины"));
        accidentTypes.put(2, new AccidentType(2, "Машина и человек"));
        accidentTypes.put(3, new AccidentType(3, "Машина и велосипед"));
    }

    public List<AccidentType> findAll() {
        return accidentTypes.values().stream().toList();
    }

    public AccidentType findById(final int id) {
        return accidentTypes.get(id);
    }
}
