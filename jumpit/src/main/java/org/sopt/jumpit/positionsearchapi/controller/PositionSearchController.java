package org.sopt.jumpit.positionsearchapi.controller;

import org.sopt.jumpit.positionsearchapi.exception.NotFoundException;
import org.sopt.jumpit.positionsearchapi.service.dto.PositionResponse;
import org.sopt.jumpit.positionsearchapi.common.dto.message.ErrorMessage;
import org.sopt.jumpit.positionsearchapi.service.dto.PositionSearchFilter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import org.sopt.jumpit.positionsearchapi.common.dto.SuccessStatusResponse;
import org.sopt.jumpit.positionsearchapi.service.PositionSearchService;
import org.sopt.jumpit.positionsearchapi.common.dto.message.SuccessMessage;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PositionSearchController {

    private final PositionSearchService positionSearchService;

    @GetMapping("/positions")
    public ResponseEntity<Object> getAllPositions() {
        List<PositionResponse.PositionData> positions = positionSearchService.searchAllPositions();
        if (positions.isEmpty()) {
            throw new NotFoundException(ErrorMessage.SEARCH_FAILED);
        }
        SuccessStatusResponse response = SuccessStatusResponse.from(SuccessMessage.SEARCH_COMPLETED_SUCCESS, positions);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Object> searchFilteredPositions(
            @RequestParam String keyword,
            @RequestParam List<Integer> categories) {
        PositionSearchFilter filter = new PositionSearchFilter(keyword, categories);
        List<PositionResponse.PositionData> positions = positionSearchService.searchPositions(filter);
        if (positions.isEmpty()) {
            throw new NotFoundException(ErrorMessage.SEARCH_FAILED);
        }
        SuccessStatusResponse response = SuccessStatusResponse.from(SuccessMessage.FILTER_SEARCH_COMPLETED_SUCCESS, positions);
        return ResponseEntity.ok(response);
    }

}
