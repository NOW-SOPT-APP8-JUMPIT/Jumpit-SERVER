package org.sopt.jumpit.resume.repository;

import org.sopt.jumpit.resume.domain.Resume;
import org.sopt.jumpit.resume.dto.ResumeResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {
    List<Resume> findByOwnerId(Long userId);
}
