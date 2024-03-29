package com.example.job4j_accidents.repository.mem;

import com.example.job4j_accidents.model.Accident;
import com.example.job4j_accidents.model.AccidentType;
import com.example.job4j_accidents.model.Rule;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger(0);

    {
        create(new Accident(0, "Нурушение парковки",
                "Парковка транспортных средств на местах, "
                        + "предназначенных для бесплатной парковки "
                        + "транспортных средств, лицами, которые не имеют"
                        + " соответствующих льгот", "ул.Васина д1",
                new AccidentType(2, "Машина и человек"),
                Set.of(new Rule(1, "Статья. 1"))));
        create(new Accident(0, "Нурушение парковки",
                "Парковка транспортных средств на местах,  "
                        + "предназначенных для бесплатной парковки "
                        + "транспортных средств, лицами, которые не имеют"
                        + " соответствующих льгот", "ул.Анны д1",
                new AccidentType(2, "Машина и человек"),
                Set.of(new Rule(1, "Статья. 1"))));
        create(new Accident(0, "Нарушение переезда перекрёстка",
                "Не уступил дорогу транспортным средствам, приближающимся по "
                        + "главной дороге",
                "ул.Петра д1",
                new AccidentType(1, "Две машины"),
                Set.of(new Rule(1, "Статья. 1"),
                        new Rule(2, "Статья. 2"))));
    }

    public List<Accident> allAccidents() {
        return accidents.values().stream().toList();
    }

    public void create(final Accident accident) {
        accident.setId(id.getAndIncrement());
        accidents.put(accident.getId(), accident);
    }

    public void update(final Accident accident) {
        accidents.put(accident.getId(), accident);
    }

    public Optional<Accident> findById(final int id) {
        return Optional.ofNullable(accidents.get(id));
    }
}

