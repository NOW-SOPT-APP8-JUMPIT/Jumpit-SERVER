package org.sopt.jumpit.relationship.repository;

import org.sopt.jumpit.relationship.domain.PositionCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PositionCategoryRepository extends JpaRepository<PositionCategory, Long> {
    Optional<List<PositionCategory>> findByPositionId(Long positionId);
    Optional<List<PositionCategory>> findByCategoryId(Long categoryId);
}
