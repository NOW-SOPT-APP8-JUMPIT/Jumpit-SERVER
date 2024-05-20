package org.sopt.jumpit.position.service;

import lombok.RequiredArgsConstructor;
import org.sopt.jumpit.company.service.CompanyService;
import org.sopt.jumpit.global.common.dto.message.ErrorMessage;
import org.sopt.jumpit.global.exception.NotFoundException;
import org.sopt.jumpit.position.domain.Position;
import org.sopt.jumpit.position.dto.PartialPositionFindResponse;
import org.sopt.jumpit.position.dto.PositionsFindResponse;
import org.sopt.jumpit.position.repository.PositionRepository;
import org.sopt.jumpit.relationship.domain.PositionCategory;
import org.sopt.jumpit.skill.domain.Skill;
import org.sopt.jumpit.skill.dto.SkillResponse;
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
    public PositionsFindResponse findFilteredPositionsByKeyword(String keyword, List<Long> categories) {
        // PositionCategory에서 category 넣고 position id값 List에 저장
        List<Long> postionInCategories = new ArrayList<>();
        for (Long categoryNumber : categories) {
            List<PositionCategory> pc = positionCategoryService.findPositionByCategory(categoryNumber);
            for (PositionCategory positionCategory : pc) {
                positionInCategories.add(positionCategory.getPosition().getId());
            }
        }
        // Position에서 keyword로 검색
        return PositionsFindResponse.of(positionRepository.findPositionsByTitleContaining(keyword.trim())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.SEARCH_FAILED))
                .stream()
                // 카테고리에 맞는 포지션들만 filtering
                .map(position -> {
                    if(postionInCategories.contains(position.getId())) {
                        return PartialPositionFindResponse.of(
                                position,
                                SkillResponse.ofList(skillService.findByOwnerId(position.getId())),
                                position.getCompany());
                    }
                    return null;
                })
                .collect(Collectors.toList())
        );
    }

        );

}
