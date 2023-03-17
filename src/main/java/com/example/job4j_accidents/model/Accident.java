package com.example.job4j_accidents.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@NamedEntityGraph(
        name = "AccidentTypeRules",
        attributeNodes = {
                @NamedAttributeNode("type"),
                @NamedAttributeNode("rules")
        })
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "accident")
public class Accident {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String text;
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    private AccidentType type;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "accident_rule",
            joinColumns = @JoinColumn(name = "accident_id"),
            inverseJoinColumns = @JoinColumn(name = "rule_id"))
    private Set<Rule> rules;
}
