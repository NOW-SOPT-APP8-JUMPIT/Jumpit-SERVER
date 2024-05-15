package org.sopt.jumpit.positionsearchapi.common.dto;

import org.springframework.http.HttpStatus;

public record ErrorResponse(int status, String message) {
    public static ErrorResponse of(int status, String message) {
        return new ErrorResponse(status, message);
    }

    public static ErrorResponse from(String message) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), message);
    }
}
