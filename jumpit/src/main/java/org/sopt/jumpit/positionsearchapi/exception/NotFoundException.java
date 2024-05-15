package org.sopt.jumpit.positionsearchapi.exception;

import org.sopt.jumpit.positionsearchapi.common.dto.message.ErrorMessage;

public class NotFoundException extends BusinessException {
    public NotFoundException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
