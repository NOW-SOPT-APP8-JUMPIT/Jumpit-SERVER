package org.sopt.jumpit.company.service;

import lombok.RequiredArgsConstructor;
import org.sopt.jumpit.company.domain.Company;
import org.sopt.jumpit.company.repository.CompanyRepository;
import org.sopt.jumpit.global.common.dto.message.ErrorMessage;
import org.sopt.jumpit.global.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public Company findById(Long id) {
        return companyRepository.findById(id).orElseThrow(
        () -> new NotFoundException(ErrorMessage.COMPANY_NOT_FOUND_BY_ID_EXCEPTION));
    }
}
