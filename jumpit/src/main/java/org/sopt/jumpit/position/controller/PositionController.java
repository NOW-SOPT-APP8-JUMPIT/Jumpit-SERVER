package org.sopt.jumpit.position.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.jumpit.global.common.dto.SuccessResponse;
import org.sopt.jumpit.global.common.dto.message.SuccessMessage;
import org.sopt.jumpit.position.dto.PartialPositionFindResponse;
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
    public ResponseEntity<SuccessResponse<List<PartialPositionFindResponse>>> getAllPositions(
            @RequestParam String keyword
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(SuccessResponse.of(SuccessMessage.SEARCH_COMPLETED_SUCCESS, positionService.findPositionsByKeyword(keyword)));
    }

}
