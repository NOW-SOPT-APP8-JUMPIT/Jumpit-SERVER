package org.sopt.jumpit.exception;

import org.sopt.jumpit.common.dto.message.ErrorMessage;

public class NotFoundException extends BusinessException {
    public NotFoundException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
