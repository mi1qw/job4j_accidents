package com.example.job4j_accidents.service;

import com.example.job4j_accidents.model.Accident;
import com.example.job4j_accidents.model.Rule;
import com.example.job4j_accidents.repository.hibernate.AccidentHibernate;
import com.example.job4j_accidents.repository.jdbctemplate.AccidentJdbcTemplate;
import com.example.job4j_accidents.repository.mem.AccidentMem;
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
    private final AccidentHibernate accidentHibernate;
    private final AccidentJdbcTemplate jdbcTemplate;

    public List<Accident> allAccidents() {
        return accidentHibernate.getAll();
    }

    public void create(final Accident accident,
                       final int typeId,
                       final List<Integer> rIds) {
        Accident acdnt = makeAccident(accident, typeId, rIds);
        accidentHibernate.save(acdnt);
    }

    public void update(final Accident accident,
                       final int typeId,
                       final List<Integer> rIds) {
        Accident acdnt = makeAccident(accident, typeId, rIds);
        accidentHibernate.update(acdnt);
    }

    public Optional<Accident> findById(final int id) {
        return accidentHibernate.findById(id);
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
