package org.sopt.jumpit.position.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.sopt.jumpit.company.domain.Company;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@Table(name = "Positions")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "contents", columnDefinition = "json")
    private String contents;

    @ManyToOne
    @JoinColumn(name = "companyId", referencedColumnName = "id")
    private Company company;

    @Column(name = "modifiedAt")
    private LocalDateTime modifiedAt;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;
}