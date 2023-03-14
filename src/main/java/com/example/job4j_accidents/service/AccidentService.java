package com.example.job4j_accidents.service;

import com.example.job4j_accidents.model.Accident;
import com.example.job4j_accidents.repository.AccidentMem;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class AccidentService {
    private final AccidentMem accidentMem;

    public List<Accident> allAccidents() {
        return accidentMem.allAccidents();
    }
}
