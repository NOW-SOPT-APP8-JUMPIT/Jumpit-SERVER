package org.sopt.jumpit.skill.repository;

import org.sopt.jumpit.skill.domain.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SkillRepository extends JpaRepository<Skill, Long> {
    Optional<List<Skill>> findByPositionId(Long id);
}