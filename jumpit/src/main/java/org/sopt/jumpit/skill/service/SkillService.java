package org.sopt.jumpit.skill.service;

import lombok.RequiredArgsConstructor;
import org.sopt.jumpit.global.common.dto.message.ErrorMessage;
import org.sopt.jumpit.global.exception.NotFoundException;
import org.sopt.jumpit.skill.domain.Skill;
import org.sopt.jumpit.skill.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillService {
    private final SkillRepository skillRepository;

    public List<Skill> findByOwnerId(Long id) {
        return skillRepository.findByPositionId(id).orElseThrow(
                () -> new NotFoundException(ErrorMessage.SKILL_NOT_FOUND_BY_ID_EXCEPTION)
        );
    }
}
