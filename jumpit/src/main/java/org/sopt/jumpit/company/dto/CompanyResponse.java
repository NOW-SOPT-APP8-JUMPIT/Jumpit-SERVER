package org.sopt.jumpit.company.dto;

import org.sopt.jumpit.company.domain.Company;

public record CompanyResponse(
        Long id,
        String name,
        String image,
        String description) {
    public static CompanyResponse of(Company company) {
        return new CompanyResponse(company.getId(), company.getName(), company.getImage(), company.getDescription());
    }
}
