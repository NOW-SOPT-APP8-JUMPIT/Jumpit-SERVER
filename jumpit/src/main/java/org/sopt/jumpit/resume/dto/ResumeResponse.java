package org.sopt.jumpit.resume.dto;

import java.time.LocalDateTime;

public record ResumeResponse(
        Long id,
        String title,
        Boolean isPrivate,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
}
