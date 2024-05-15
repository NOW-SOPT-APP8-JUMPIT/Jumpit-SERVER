package org.sopt.jumpit.positionsearchapi.repository;

import org.sopt.jumpit.positionsearchapi.domain.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {

}
