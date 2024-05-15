package org.sopt.jumpit.positionsearchapi.service.dto;

import java.util.List;

public record PositionSearchFilter(String keyword, List<Integer> categories) {
}
