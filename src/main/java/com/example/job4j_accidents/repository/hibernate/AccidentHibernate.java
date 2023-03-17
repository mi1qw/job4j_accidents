package com.example.job4j_accidents.repository.hibernate;

import com.example.job4j_accidents.model.Accident;
import com.example.job4j_accidents.repository.SessionWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentHibernate {
    private final SessionWrapper wrapper;

    public Accident save(final Accident accident) {
        wrapper.run(session -> session.persist(accident));
        return accident;
    }

    public void update(final Accident accident) {
        wrapper.run(session -> session.merge(accident));
    }

    public List<Accident> getAll() {
        return wrapper.tx(session ->
                session.createQuery("from Accident", Accident.class)
                        .setHint("jakarta.persistence.loadgraph",
                                session.getEntityGraph("AccidentTypeRules"))
                        .list());
    }

    public Optional<Accident> findById(final int id) {
        Accident accident = wrapper.tx(session -> session.find(Accident.class, id,
                Map.of("javax.persistence.loadgraph",
                        session.getEntityGraph("AccidentTypeRules"))));
        return Optional.ofNullable(accident);
    }
}
