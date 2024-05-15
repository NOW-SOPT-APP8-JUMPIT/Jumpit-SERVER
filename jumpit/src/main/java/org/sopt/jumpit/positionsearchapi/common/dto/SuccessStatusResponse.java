package org.sopt.jumpit.positionsearchapi.common.dto;

import org.sopt.jumpit.positionsearchapi.common.dto.message.SuccessMessage;

public record SuccessStatusResponse(int status, String message, Object data) {

    public static SuccessStatusResponse from(SuccessMessage successMessage, Object data) {
        return new SuccessStatusResponse(successMessage.getStatus(), successMessage.getMessage(), data);
    }


}
