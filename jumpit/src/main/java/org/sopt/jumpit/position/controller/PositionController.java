package org.sopt.jumpit.position.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.jumpit.global.common.dto.SuccessResponse;
import org.sopt.jumpit.global.common.dto.message.SuccessMessage;
import org.sopt.jumpit.position.dto.PositionDetailResponse;
import org.sopt.jumpit.position.dto.PositionsFindResponse;
import org.sopt.jumpit.position.service.PositionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PositionController {

    private final PositionService positionService;

    @GetMapping("/positions")
    public ResponseEntity<SuccessResponse<PositionsFindResponse>> getAllPositions(
            @RequestParam String keyword
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(SuccessResponse.of(SuccessMessage.SEARCH_COMPLETED_SUCCESS, positionService.findPositionsByKeyword(keyword)));
    }

    @GetMapping("/positions/filter")
    public ResponseEntity<SuccessResponse<PositionsFindResponse>> getFilteredPositions(
            @RequestParam String keyword,
            @RequestParam List<Long> categories
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(SuccessResponse.of(SuccessMessage.SEARCH_COMPLETED_SUCCESS, positionService.findFilteredPositionsByKeyword(keyword, categories)));
    }

    @GetMapping("/positions/{positionId}")
    public ResponseEntity<SuccessResponse<PositionDetailResponse>> getPositionDetail(
            @PathVariable Long positionId
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(SuccessResponse.of(SuccessMessage.SEARCH_COMPLETED_SUCCESS, positionService.findPositionDetail(positionId)));
    }



}
