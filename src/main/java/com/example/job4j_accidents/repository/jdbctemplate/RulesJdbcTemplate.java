package com.example.job4j_accidents.repository.jdbctemplate;

import com.example.job4j_accidents.model.Rule;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
@AllArgsConstructor
public class RulesJdbcTemplate {
    private final JdbcTemplate jdbc;

    public List<Rule> findAll() {
        return jdbc.query("select * from rule", this::ruleRawMapper);
    }

    public Set<Rule> findByAccident(final int id) {
        List<Rule> list = jdbc.query("""
                        select r.id, r.name from accident_rule ar
                        join rule r on r.id = ar.rule_id
                        where accident_id = ?""",
                this::ruleRawMapper, id);
        return new HashSet<>(list);
    }

    public void saveByAccident(final int id, final Set<Rule> rules) {
        rules.forEach(rule -> jdbc.update("""
                        insert into accident_rule (accident_id, rule_id)
                        values (?, ?)""",
                id, rule.getId()));
    }

    public void deleteByAccident(final int id) {
        jdbc.update("delete from accident_rule where accident_id = ?", id);
    }

    private Rule ruleRawMapper(final ResultSet rs, final int id)
            throws SQLException {
        Rule rule = new Rule();
        rule.setId(rs.getInt("id"));
        rule.setName(rs.getString("name"));
        return rule;
    }
}
