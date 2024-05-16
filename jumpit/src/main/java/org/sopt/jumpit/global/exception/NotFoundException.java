package org.sopt.jumpit.global.exception;

import org.sopt.jumpit.global.common.dto.message.ErrorMessage;

public class NotFoundException extends BusinessException {
    public NotFoundException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
