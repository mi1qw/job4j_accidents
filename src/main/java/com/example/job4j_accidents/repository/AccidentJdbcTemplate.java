package com.example.job4j_accidents.repository;

import com.example.job4j_accidents.model.Accident;
import com.example.job4j_accidents.service.AccidentTypeService;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;
    private final AccidentTypeService typeService;
    private final RulesJdbcTemplate ruleJdbc;

    public Accident save(final Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("""
                            insert into accident (name, text, address, type_id)
                            values (?, ?, ?, ?)""",
                    new String[]{"id"});
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
            return ps;
        }, keyHolder);
        int id = keyHolder.getKey().intValue();
        accident.setId(id);
        ruleJdbc.saveByAccident(id, accident.getRules());
        return accident;
    }

    public void update(final Accident accident) {
        jdbc.update("""
                        update accident set name = ?, text = ?, address = ?, type_id = ?
                        where id = ?""",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId(),
                accident.getId());
        ruleJdbc.deleteByAccident(accident.getId());
        ruleJdbc.saveByAccident(accident.getId(), accident.getRules());
    }

    public Optional<Accident> findById(final int id) {
        Accident accident = jdbc.queryForObject("select * from accident where id = ?",
                this::accidentRawMapper, id);
        return Optional.ofNullable(accident);
    }

    public List<Accident> getAll() {
        return jdbc.query("select * from accident", this::accidentRawMapper);
    }

    private Accident accidentRawMapper(final ResultSet rs, final int rowNum)
            throws SQLException {
        Accident accident = new Accident();
        accident.setId(rs.getInt("id"));
        accident.setName(rs.getString("name"));
        accident.setText(rs.getString("text"));
        accident.setAddress(rs.getString("address"));
        accident.setType(typeService.findById(rs.getInt("type_id")));
        accident.setRules(
                ruleJdbc.findByAccident(accident.getId()));
        return accident;
    }
}
