package org.sopt.jumpit.common.dto;

import org.sopt.jumpit.common.dto.message.SuccessMessage;

public record SuccessResponse(int status, String message, Object data) {

    public static SuccessResponse from(SuccessMessage successMessage, Object data) {
        return new SuccessResponse(successMessage.getStatus(), successMessage.getMessage(), data);
    }

}
