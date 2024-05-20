package org.sopt.jumpit.relationship.service;

import lombok.RequiredArgsConstructor;
import org.sopt.jumpit.global.common.dto.message.ErrorMessage;
import org.sopt.jumpit.global.exception.NotFoundException;
import org.sopt.jumpit.relationship.domain.PositionCategory;
import org.sopt.jumpit.relationship.repository.PositionCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class PositionCategoryService {
    private final PositionCategoryRepository positionCategoryRepository;

    public List<PositionCategory> findCategoryByPosition(Long positionId) {
        return positionCategoryRepository.findByPositionId(positionId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.CATEGORIES_NOT_FOUND_BY_POSITION_EXCEPTION)
        );
    }

    public List<PositionCategory> findPositionByCategory(Long categoryId) {
        return positionCategoryRepository.findByCategoryId(categoryId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.POSITION_NOT_FOUND_BY_CATEGORIES_EXCEPTION)
        );
    }
}
