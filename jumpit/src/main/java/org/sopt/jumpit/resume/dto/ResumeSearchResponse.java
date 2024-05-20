package org.sopt.jumpit.resume.dto;

import java.util.List;


public record ResumeSearchResponse(
        Long userId,
        List<ResumeResponse> resume
) {


}
