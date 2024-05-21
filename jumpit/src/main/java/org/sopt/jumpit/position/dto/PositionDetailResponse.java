package org.sopt.jumpit.position.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.sopt.jumpit.company.domain.Company;
import org.sopt.jumpit.company.dto.CompanyResponse;
import org.sopt.jumpit.skill.dto.SkillResponse;

import java.util.List;

public record PositionDetailResponse(
        @JsonProperty("positions")
        PositionContents positionContents,
        List<SkillResponse> skills,
        CompanyResponse companyResponse
) {

    public static PositionDetailResponse of(
            PositionContents positionContents,
            List<SkillResponse> skills,
            Company company) {

        return new PositionDetailResponse(positionContents, skills, CompanyResponse.of(company));
    }
}
