package org.sopt.jumpit.position.dto;

import org.sopt.jumpit.company.domain.Company;
import org.sopt.jumpit.position.domain.Position;
import org.sopt.jumpit.skill.domain.Skill;

import java.util.List;

public record PartialPositionFindResponse(
        Long id,
        String title,
        List<Skill> skills,
        Company company

) {
    public static PartialPositionFindResponse of(final Position position,
                                                 final List<Skill> skills,
                                                 final Company company) {
        return new PartialPositionFindResponse(
                position.getId(),
                position.getTitle(),
                skills,
                company
        );
    }


}
