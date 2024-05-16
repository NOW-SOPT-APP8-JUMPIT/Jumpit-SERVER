package org.sopt.jumpit.relationship.domain;

import jakarta.persistence.*;
import org.sopt.jumpit.category.domain.Category;
import org.sopt.jumpit.position.domain.Position;

@Entity
@Table(name = "Positions_Categories")
public class PositionCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "positionId", referencedColumnName = "id")
    private Position position;

    @ManyToOne
    @JoinColumn(name = "categoryId", referencedColumnName = "id")
    private Category category;
}