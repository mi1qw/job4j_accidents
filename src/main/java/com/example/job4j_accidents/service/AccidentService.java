package com.example.job4j_accidents.service;

import com.example.job4j_accidents.model.Accident;
import com.example.job4j_accidents.model.Rule;
import com.example.job4j_accidents.repository.AccidentMem;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccidentService {
    private final RuleService ruleService;
    private final AccidentTypeService typeService;
    private final AccidentMem accidentMem;

    public List<Accident> allAccidents() {
        return accidentMem.allAccidents();
    }

    public void create(final Accident accident,
                       final int typeId,
                       final List<Integer> rIds) {
        Accident acdnt = makeAccident(accident, typeId, rIds);
        accidentMem.create(acdnt);
    }

    public void update(final Accident accident,
                       final int typeId,
                       final List<Integer> rIds) {
        Accident acdnt = makeAccident(accident, typeId, rIds);
        accidentMem.update(acdnt);
    }

    public Optional<Accident> findById(final int id) {
        return accidentMem.findById(id);
    }

    private Accident makeAccident(final Accident accident,
                                  final int typeId,
                                  final List<Integer> rIds) {
        accident.setType(typeService.findById(typeId));
        Set<Rule> accidentRules = rIds.stream()
                .map(ruleService::findById)
                .collect(Collectors.toSet());
        accident.setRules(accidentRules);
        return accident;
    }
}
