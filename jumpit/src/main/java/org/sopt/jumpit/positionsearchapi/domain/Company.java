package org.sopt.jumpit.positionsearchapi.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String image;

}
