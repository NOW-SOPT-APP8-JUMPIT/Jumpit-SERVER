package org.sopt.jumpit.positionsearchapi.service;

import lombok.RequiredArgsConstructor;
import org.sopt.jumpit.positionsearchapi.domain.constant.Category;
import org.sopt.jumpit.positionsearchapi.domain.Position;
import org.sopt.jumpit.positionsearchapi.service.dto.PositionResponse.PositionData;
import org.sopt.jumpit.positionsearchapi.service.dto.PositionResponse.PositionData.CompanyData;
import org.sopt.jumpit.positionsearchapi.service.dto.PositionSearchFilter;
import org.sopt.jumpit.positionsearchapi.service.dto.SkillDTO;
import org.sopt.jumpit.positionsearchapi.repository.PositionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PositionSearchService {

    private final PositionRepository positionRepository;

    public List<PositionData> searchAllPositions() {
        return positionRepository.findAll().stream()
                .map(position -> new PositionData(
                        position.getId(),
                        position.getTitle(),
                        extractSkills(position),
                        new CompanyData(
                                position.getCompany().getId(),
                                position.getCompany().getName(),
                                position.getCompany().getImage()
                        )
                ))
                .collect(Collectors.toList());
    }

    public List<PositionData> searchPositions(PositionSearchFilter filter) {
        return positionRepository.findAll().stream()
                .filter(position -> position.getTitle().contains(filter.keyword()) ||
                        filter.categories().contains(Category.fromDescription(position.getTitle()).getId()))
                .map(this::convertToPositionData)
                .collect(Collectors.toList());
    }

    private PositionData convertToPositionData(Position position) {
        List<SkillDTO> skills = extractSkills(position);
        CompanyData companyData = new CompanyData(
                position.getCompany().getId(),
                position.getCompany().getName(),
                position.getCompany().getImage()
        );
        return new PositionData(
                position.getId(),
                position.getTitle(),
                skills,
                companyData
        );
    }

    private List<SkillDTO> extractSkills(Position position) {
        return position.getPositionSkills().stream()
                .map(ps -> new SkillDTO(ps.getSkill().getId(), ps.getSkill().getName()))
                .collect(Collectors.toList());
    }
}
