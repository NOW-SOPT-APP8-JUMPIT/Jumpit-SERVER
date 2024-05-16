package org.sopt.jumpit.resume.dto;

public record ResumeCreateRequest(
        String title,
        Long userId
) {
}
