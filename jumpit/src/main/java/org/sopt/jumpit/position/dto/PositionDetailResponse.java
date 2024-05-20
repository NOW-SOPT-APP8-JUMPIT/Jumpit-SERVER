package org.sopt.jumpit.position.dto;

import org.sopt.jumpit.company.domain.Company;
import org.sopt.jumpit.skill.dto.SkillResponse;

import java.util.List;

public record PositionDetailResponse(
        PositionContents positionContents,
        List<SkillResponse> skills,
        Company company
) {

    public static PositionDetailResponse of(
            PositionContents positionContents,
            List<SkillResponse> skills,
            Company company) {

        return new PositionDetailResponse(positionContents, skills, company);
    }
}
