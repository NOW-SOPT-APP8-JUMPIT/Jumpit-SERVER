package org.sopt.jumpit.skill.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.sopt.jumpit.position.domain.Position;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "Skills")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "positionId", referencedColumnName = "id")
    private Position position;

    @Column(name = "modifiedAt")
    private LocalDateTime modifiedAt;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;
}
