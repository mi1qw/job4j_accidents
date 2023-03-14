package com.example.job4j_accidents.repository;

import com.example.job4j_accidents.model.Accident;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Slf4j
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    {
        accidents.put(1, new Accident(1, "Нурушение парковки",
                "Парковка транспортных средств на местах, "
                        + "предназначенных для бесплатной парковки "
                        + "транспортных средств, лицами, которые не имеют"
                        + " соответствующих льгот", "ул.Васина д1"));
        accidents.put(2, new Accident(2, "Нурушение парковки",
                "Парковка транспортных средств на местах, "
                        + "предназначенных для бесплатной парковки "
                        + "транспортных средств, лицами, которые не имеют"
                        + " соответствующих льгот", "ул.Анны д1"));
        accidents.put(3, new Accident(3, "Нарушение перезда перекрёстка",
                "Не уступил дорогу транспортным средствам, приближающимся по "
                        + "главной дороге",
                "ул.Петра д1"));
    }

    public List<Accident> allAccidents() {
        return accidents.values().stream().toList();
    }
}

