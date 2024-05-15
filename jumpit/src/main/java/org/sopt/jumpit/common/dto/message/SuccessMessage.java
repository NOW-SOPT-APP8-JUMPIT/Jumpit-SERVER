package org.sopt.jumpit.common.dto.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum SuccessMessage {
    SEARCH_COMPLETED_SUCCESS(HttpStatus.OK.value(), "[SUCCESS] 채용 공고 검색이 완료되었습니다."),
    FILTER_SEARCH_COMPLETED_SUCCESS(HttpStatus.OK.value(), "[SUCCESS] 채용 공고 필터 검색이 완료되었습니다.");

    private final int status;
    private final String message;
}
