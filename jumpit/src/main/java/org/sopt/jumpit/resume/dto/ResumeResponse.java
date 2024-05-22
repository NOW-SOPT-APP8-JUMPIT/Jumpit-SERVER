package org.sopt.jumpit.resume.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.sopt.jumpit.resume.domain.Resume;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record ResumeResponse(
        Long id,
        String title,
        Boolean isPrivate
) {

    public static ResumeResponse of(Resume resume) {
        return ResumeResponse.builder()
                .id(resume.getId())
                .title(resume.getTitle())
                .isPrivate(resume.isPrivate())
                .build();

    }
}
