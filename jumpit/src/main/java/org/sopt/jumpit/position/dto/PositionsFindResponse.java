package org.sopt.jumpit.position.dto;

import java.util.List;

public record PositionsFindResponse(
        List<PartialPositionFindResponse> position
) {
    public static PositionsFindResponse of(List<PartialPositionFindResponse> positionFindResponses) {
        return new PositionsFindResponse(positionFindResponses);
    }
}
