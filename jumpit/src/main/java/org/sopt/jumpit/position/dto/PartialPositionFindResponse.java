package org.sopt.jumpit.position.dto;

import org.sopt.jumpit.company.domain.Company;
import org.sopt.jumpit.company.dto.CompanyResponse;
import org.sopt.jumpit.position.domain.Position;
import org.sopt.jumpit.skill.domain.Skill;
import org.sopt.jumpit.skill.dto.SkillResponse;

import java.util.List;
import java.util.stream.Collectors;

public record PartialPositionFindResponse(
        Long id,
        String title,
        List<SkillResponse> skills,
        CompanyResponse companyResponse

) {
    public static PartialPositionFindResponse of(final Position position,
                                                 final List<SkillResponse> skills,
                                                 final Company company) {

        return new PartialPositionFindResponse(
                position.getId(),
                position.getTitle(),
                skills,
                CompanyResponse.of(company)
        );
    }


}
