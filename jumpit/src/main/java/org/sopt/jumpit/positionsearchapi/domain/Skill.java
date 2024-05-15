package org.sopt.jumpit.positionsearchapi.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
}
