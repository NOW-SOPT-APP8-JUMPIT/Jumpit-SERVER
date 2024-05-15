package org.sopt.jumpit.positionsearchapi.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Set;


@Entity
@Getter
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @OneToMany(mappedBy = "position", fetch = FetchType.LAZY)
    private Set<PositionSkill> positionSkills;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;
}

