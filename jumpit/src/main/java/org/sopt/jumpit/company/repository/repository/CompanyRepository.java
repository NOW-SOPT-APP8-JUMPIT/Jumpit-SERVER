package org.sopt.jumpit.company.repository.repository;

import org.sopt.jumpit.company.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
