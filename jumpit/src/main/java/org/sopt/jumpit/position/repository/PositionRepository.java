package org.sopt.jumpit.position.repository;

import org.sopt.jumpit.position.domain.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {
}
