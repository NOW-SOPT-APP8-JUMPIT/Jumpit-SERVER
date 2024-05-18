package org.sopt.jumpit.resume.dto;

import java.time.LocalDateTime;

public record Resume(
        Long id,
        String title,
        Boolean isPrivate,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
}
