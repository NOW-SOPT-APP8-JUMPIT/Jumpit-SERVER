package org.sopt.jumpit.global.common.dto.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum SuccessMessage {
    SEARCH_COMPLETED_SUCCESS(HttpStatus.OK.value(), "[SUCCESS] 채용 공고 검색이 완료되었습니다."),
    FILTER_SEARCH_COMPLETED_SUCCESS(HttpStatus.OK.value(), "[SUCCESS] 채용 공고 필터 검색이 완료되었습니다."),
    RESUME_CREATED_COMPLETED_SUCCESS(HttpStatus.CREATED.value(), "[SUCCESS] 이력서 등록을 완료하였습니다."),
    RESUME_SEARCH_COMPLETED_SUCCESS(HttpStatus.OK.value(), "[SUCCESS] 이력서 조회를 성공했습니다."),
    RESUME_PRIVATE_CHANGE_COMPLETED_SUCCESS(HttpStatus.OK.value(), "[SUCCESS] 이력서 공개 범위 변경이 완료되었습니다.");

    private final int status;
    private final String message;
}
