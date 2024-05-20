package org.sopt.jumpit.resume.controller;


import lombok.RequiredArgsConstructor;
import org.sopt.jumpit.global.common.dto.SuccessResponse;
import org.sopt.jumpit.global.common.dto.message.SuccessMessage;
import org.sopt.jumpit.resume.domain.Resume;
import org.sopt.jumpit.resume.dto.ResumeCreateRequest;
import org.sopt.jumpit.resume.dto.ResumePrivateRequest;
import org.sopt.jumpit.resume.dto.ResumeSearchResponse;
import org.sopt.jumpit.resume.service.ResumeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;

    @PostMapping("/resumes")
    public ResponseEntity<SuccessResponse> createResume (
            @RequestBody ResumeCreateRequest resumeCreateRequest
    ) {
        Long resumeId = resumeService.createResume(resumeCreateRequest);
        URI location = URI.create("/api/v1/resumes/" + resumeId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .location(location)
                .body(SuccessResponse.of(SuccessMessage.RESUME_CREATED_COMPLETED_SUCCESS));
    }

    @GetMapping("/resumes/{userId}")
    public ResponseEntity<SuccessResponse<ResumeSearchResponse>> findResumeById(@PathVariable Long userId) {
        ResumeSearchResponse resumeSearchResponse = resumeService.findResumeById(userId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(SuccessResponse.of(SuccessMessage.RESUME_SEARCH_COMPLETED_SUCCESS, resumeSearchResponse));
    }

    @PatchMapping("/resumes/{resumeId}")
    public ResponseEntity<SuccessResponse> updateIsPrivate(
            @PathVariable Long resumeId,
            @RequestBody ResumePrivateRequest request
    ) {
        resumeService.updateResumePrivate(resumeId, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(SuccessResponse.of(SuccessMessage.RESUME_PRIVATE_CHANGE_COMPLETED_SUCCESS));
    }
}
