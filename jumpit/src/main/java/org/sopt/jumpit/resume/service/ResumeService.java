package org.sopt.jumpit.resume.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.sopt.jumpit.global.common.dto.message.ErrorMessage;
import org.sopt.jumpit.global.exception.NotFoundException;
import org.sopt.jumpit.resume.domain.Resume;
import org.sopt.jumpit.resume.dto.ResumeCreateRequest;
import org.sopt.jumpit.resume.dto.ResumePrivateRequest;
import org.sopt.jumpit.resume.repository.ResumeRepository;
import org.sopt.jumpit.user.domain.User;
import org.sopt.jumpit.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResumeService {
    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long createResume(
            ResumeCreateRequest resumeCreateRequest
    ) {
        User findUser = userRepository.findById(resumeCreateRequest.userId()).orElseThrow(
                () -> new NotFoundException(ErrorMessage.USER_NOT_FOUND_BY_ID_EXCEPTION)
        );
        Resume resume = Resume.create(findUser, "내 이력서");
        resumeRepository.save(resume);
        return resume.getId();
    }

    public Resume findResumeById(Long resumeId) {
        return resumeRepository.findById(resumeId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.USER_NOT_FOUND_BY_ID_EXCEPTION)
        );
    }

    @Transactional
    public void updateResumePrivate(
            Long resumeId,
            ResumePrivateRequest resumePrivateRequest
    ) {
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.RESUME_NOT_FOUND_BY_ID_EXCEPTION)
        );
        resume.updateIsPrivate(resumePrivateRequest.isPrivate());
    }
}
