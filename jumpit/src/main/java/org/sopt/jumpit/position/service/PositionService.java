package org.sopt.jumpit.position.service;

import lombok.RequiredArgsConstructor;
import org.sopt.jumpit.company.service.CompanyService;
import org.sopt.jumpit.global.common.dto.message.ErrorMessage;
import org.sopt.jumpit.global.exception.NotFoundException;
import org.sopt.jumpit.position.domain.Position;
import org.sopt.jumpit.position.dto.PartialPositionFindResponse;
import org.sopt.jumpit.position.repository.PositionRepository;
import org.sopt.jumpit.skill.domain.Skill;
import org.sopt.jumpit.skill.service.SkillService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PositionService {
    private final PositionRepository positionRepository;
    private final SkillService skillService;

    public List<PartialPositionFindResponse> findPositionsByKeyword(String keyword) {
        return positionRepository.findPositionsByTitleContaining(keyword.trim())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.SEARCH_FAILED))
                .stream()
                .map(position -> {
                    List<Skill> skills = skillService.findByOwnerId(position.getId());
                    return PartialPositionFindResponse.of(position, skills, position.getCompany());
                })
                .collect(Collectors.toList());
    }


}
