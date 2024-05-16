package org.sopt.jumpit.resume.repository;

import org.sopt.jumpit.resume.domain.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
}
