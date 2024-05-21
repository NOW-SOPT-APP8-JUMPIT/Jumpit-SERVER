package org.sopt.jumpit.resume.dto;

import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)

public record ResumeSearchResponse(
        long userId,
        List<ResumeResponse> resumes
) {

    public static ResumeSearchResponse of(long userId, List<ResumeResponse> resumes) {
        return ResumeSearchResponse.builder()
                .userId(userId)
                .resumes(resumes)
                .build();
    }


}
