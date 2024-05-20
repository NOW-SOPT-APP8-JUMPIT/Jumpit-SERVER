package org.sopt.jumpit.resume.controller;


import lombok.RequiredArgsConstructor;
import org.sopt.jumpit.global.common.dto.SuccessResponse;
import org.sopt.jumpit.resume.domain.Resume;
import org.sopt.jumpit.resume.dto.ResumeCreateRequest;
import org.sopt.jumpit.resume.dto.ResumePrivateRequest;
import org.sopt.jumpit.resume.dto.ResumeSearchResponse;
import org.sopt.jumpit.resume.service.ResumeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;

    @PostMapping("/resumes")
    public ResponseEntity<SuccessResponse> createResume (
            @RequestBody ResumeCreateRequest resumeCreateRequest
    ) {
        resumeService.createResume(resumeCreateRequest);
        return ResponseEntity.ok()
                .build();
    }

    @GetMapping("/resumes/{userId}")
    public ResponseEntity<Resume> findResumeById(@PathVariable Long userId) {
        return ResponseEntity.ok(resumeService.findResumeById(userId));
    }

    @PatchMapping("/resumes/{resumeId}")
    public ResponseEntity<SuccessResponse> updateIsPrivate(
            @PathVariable Long resumeId,
            @RequestBody ResumePrivateRequest resumePrivateRequest
    ) {
        resumeService.updateResumePrivate(resumeId, resumePrivateRequest);
        return ResponseEntity.ok()
                .build();
    }
}
