package com.example.job4j_accidents.service;

import com.example.job4j_accidents.model.AccidentType;
import com.example.job4j_accidents.repository.AccidentTypeMem;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccidentTypeService {
    private final AccidentTypeMem accidentTypeMem;

    public List<AccidentType> findAll() {
        return accidentTypeMem.findAll();
    }

    public AccidentType findById(final int id) {
        return accidentTypeMem.findById(id);
    }
}
