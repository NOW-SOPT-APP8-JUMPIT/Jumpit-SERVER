package org.sopt.jumpit.company.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "Company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "description")
    private String description;

    @Column(name = "modifiedAt")
    private LocalDateTime modifiedAt;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;
}