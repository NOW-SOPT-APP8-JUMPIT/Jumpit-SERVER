package org.sopt.jumpit.resume.controller;


import lombok.RequiredArgsConstructor;
import org.sopt.jumpit.global.common.dto.SuccessResponse;
import org.sopt.jumpit.resume.dto.ResumeCreateRequest;
import org.sopt.jumpit.resume.service.ResumeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/resumes")
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;

    @PostMapping
    public ResponseEntity<SuccessResponse> createResume (
            @RequestBody ResumeCreateRequest resumeCreateRequest
    ) {
        resumeService.createResume(resumeCreateRequest);
        return ResponseEntity.ok()
                .build();
    }
}
