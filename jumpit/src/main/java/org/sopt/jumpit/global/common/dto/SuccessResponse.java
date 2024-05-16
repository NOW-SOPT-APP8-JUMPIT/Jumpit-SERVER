package org.sopt.jumpit.global.common.dto;

import org.sopt.jumpit.global.common.dto.message.SuccessMessage;

public record SuccessResponse<T> (
        int status,
        String message,
        T data) {

    public static SuccessResponse<Void> of (SuccessMessage successMessage) {
        return new SuccessResponse<>(successMessage.getStatus(), successMessage.getMessage(), null);
    }

    public static <T> SuccessResponse<T> of(SuccessMessage successMessage, T data) {
        return new SuccessResponse(successMessage.getStatus(), successMessage.getMessage(), data);
    }

}
