package org.sopt.jumpit.global.exception;

import lombok.Getter;
import org.sopt.jumpit.global.common.dto.message.ErrorMessage;

@Getter
public class BusinessException extends RuntimeException {
    private ErrorMessage errorMessage;

    public BusinessException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }
}
