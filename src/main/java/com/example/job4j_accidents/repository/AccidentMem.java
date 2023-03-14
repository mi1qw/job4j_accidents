package com.example.job4j_accidents.repository;

import com.example.job4j_accidents.model.Accident;
import com.example.job4j_accidents.model.AccidentType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@Slf4j
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger(0);

    {
        int id = this.id.getAndIncrement();
        accidents.put(id, new Accident(id, "Нурушение парковки",
                "Парковка транспортных средств на местах, "
                        + "предназначенных для бесплатной парковки "
                        + "транспортных средств, лицами, которые не имеют"
                        + " соответствующих льгот", "ул.Васина д1",
                new AccidentType(2, "Машина и человек")));
        id = this.id.getAndIncrement();
        accidents.put(id, new Accident(id, "Нурушение парковки",
                "Парковка транспортных средств на местах,  "
                        + "предназначенных для бесплатной парковки "
                        + "транспортных средств, лицами, которые не имеют"
                        + " соответствующих льгот", "ул.Анны д1",
                new AccidentType(2, "Машина и человек")));
        id = this.id.getAndIncrement();
        accidents.put(id, new Accident(id, "Нарушение перезда перекрёстка",
                "Не уступил дорогу транспортным средствам, приближающимся по "
                        + "главной дороге",
                "ул.Петра д1",
                new AccidentType(1, "Две машины")));
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

