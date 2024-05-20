package org.sopt.jumpit.position.repository;

import org.sopt.jumpit.position.domain.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
    Optional<List<Position>> findPositionsByTitleContaining(String keyword);
}
