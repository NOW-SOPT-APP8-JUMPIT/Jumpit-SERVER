package org.sopt.jumpit.positionsearchapi.common.dto.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorMessage {
    SEARCH_FAILED(HttpStatus.BAD_REQUEST.value(), "[ERROR] 채용 공고 검색에 실패하였습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "[ERROR] 서버 내부 오류가 발생하였습니다.");

    private final int status;
    private final String message;
}
