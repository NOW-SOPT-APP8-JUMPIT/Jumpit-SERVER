package org.sopt.jumpit.category.domain;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "number")
    private int number;

    @Column(name = "modifiedAt")
    private LocalDateTime modifiedAt;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;
}