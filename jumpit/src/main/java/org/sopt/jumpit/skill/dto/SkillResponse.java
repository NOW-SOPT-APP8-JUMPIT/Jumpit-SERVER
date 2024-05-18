package org.sopt.jumpit.skill.dto;

import org.sopt.jumpit.skill.domain.Skill;

import java.util.List;
import java.util.stream.Collectors;

public record SkillResponse(
        String name,
        String image
) {

    public static List<SkillResponse> ofList (List<Skill> skills) {
        return skills.stream()
                .map(skill -> new SkillResponse(skill.getName(), skill.getImage()))
                .collect(Collectors.toList());
    }
}
