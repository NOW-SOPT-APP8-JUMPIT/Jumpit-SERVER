package org.sopt.jumpit.global.exception;

import org.sopt.jumpit.global.common.dto.message.ErrorMessage;

public class ParseException extends BusinessException{
    public ParseException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
