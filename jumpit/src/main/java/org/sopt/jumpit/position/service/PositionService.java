package org.sopt.jumpit.position.service;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.sopt.jumpit.global.common.dto.message.ErrorMessage;
import org.sopt.jumpit.global.exception.NotFoundException;
import org.sopt.jumpit.global.exception.ParseException;
import org.sopt.jumpit.position.PositionContentsEnum;
import org.sopt.jumpit.position.domain.Position;
import org.sopt.jumpit.position.dto.PositionContents;
import org.sopt.jumpit.position.dto.PositionDetailResponse;
import org.sopt.jumpit.position.dto.PartialPositionFindResponse;
import org.sopt.jumpit.position.dto.PositionsFindResponse;
import org.sopt.jumpit.position.repository.PositionRepository;
import org.sopt.jumpit.relationship.domain.PositionCategory;
import org.sopt.jumpit.relationship.service.PositionCategoryService;
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
    private final PositionCategoryService positionCategoryService;

    public PositionsFindResponse findPositionsByKeyword(String keyword) {
        return PositionsFindResponse.of(positionRepository.findPositionsByTitleContaining(keyword.trim())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.SEARCH_FAILED))
                .stream()
                .map(position -> PartialPositionFindResponse.of(
                        position,
                        SkillResponse.ofList(skillService.findByOwnerId(position.getId())),
                        position.getCompany()))
                .collect(Collectors.toList()));
    }

    public PositionsFindResponse findFilteredPositionsByKeyword(String keyword, List<Long> categories) {
        // PositionCategory에서 category 넣고 position id값 List에 저장
        List<Long> positionInCategories = new ArrayList<>();
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
                    if(positionInCategories.contains(position.getId())) {
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

    public PositionDetailResponse findPositionDetail(Long positionId) {
        Position position = positionRepository.findById(positionId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.POSITION_NOT_FOUND_EXCEPTION)
        );
        return PositionDetailResponse.of(parse(position),
                SkillResponse.ofList(skillService.findByOwnerId(position.getId())),
                position.getCompany());
    }

    public PositionContents parse(Position position) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(position.getContents());
            String career = (String)object.get(PositionContentsEnum.CAREER.getValue());
            String education = (String)object.get(PositionContentsEnum.EDUCATION.getValue());
            String deadline = (String)object.get(PositionContentsEnum.DEADLINE.getValue());
            String location = (String)object.get(PositionContentsEnum.LOCATION.getValue());
            String responsibilities = (String)object.get(PositionContentsEnum.RESPONSIBILITIES.getValue());
            String qualifications = (String)object.get(PositionContentsEnum.QUALIFICATIONS.getValue());
            String preferred = (String)object.get(PositionContentsEnum.PREFERRED.getValue());
            String benefits = (String)object.get(PositionContentsEnum.BENEFITS.getValue());
            String notice = (String)object.get(PositionContentsEnum.NOTICE.getValue());

            return new PositionContents(position.getId(), position.getTitle(),
                    career, education, deadline,location, responsibilities,
                    qualifications, preferred, benefits, notice);
        }
        catch (org.json.simple.parser.ParseException e) {
            throw new ParseException(ErrorMessage.PARSE_EXCEPTION);
        }
    }
}
