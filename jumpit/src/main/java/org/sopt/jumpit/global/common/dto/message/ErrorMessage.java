package org.sopt.jumpit.global.common.dto.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorMessage {
    SEARCH_FAILED(HttpStatus.BAD_REQUEST.value(), "[ERROR] 채용 공고 검색에 실패하였습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "[ERROR] 서버 내부 오류가 발생하였습니다."),
    COMPANY_NOT_FOUND_BY_ID_EXCEPTION(HttpStatus.NOT_FOUND.value(), "[ERROR] ID에 해당하는 기업이 없습니다."),
    SKILL_NOT_FOUND_BY_ID_EXCEPTION(HttpStatus.NOT_FOUND.value(), "[ERROR] ID에 해당하는 기술스택이 없습니다.")
    ;

    private final int status;
    private final String message;
}

