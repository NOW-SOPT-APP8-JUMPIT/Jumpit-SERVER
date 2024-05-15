package org.sopt.jumpit.positionsearchapi.domain;

import jakarta.persistence.*;
import lombok.Getter;

/**
 * 다대다를 풀기위한 클래스
 */

@Entity
@Getter
public class PositionSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id")
    private Position position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id")
    private Skill skill;
}
