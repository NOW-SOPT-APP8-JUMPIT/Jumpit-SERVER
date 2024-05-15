package org.sopt.jumpit.positionsearchapi.service.dto;


import java.util.List;

public record PositionResponse(int status, String message, List<PositionData> data) {

    public record PositionData(Long id, String title, List<SkillDTO> skills, CompanyData company) {

        public record CompanyData(Long id, String name, String image) {
        }
    }
}