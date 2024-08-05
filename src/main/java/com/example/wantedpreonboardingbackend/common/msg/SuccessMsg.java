package com.example.wantedpreonboardingbackend.common.msg;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Getter
@AllArgsConstructor
public enum SuccessMsg {
    NOTICE_CREATE_SUCCESS(CREATED, "채용 공고가 성공적으로 등록되었습니다."),
    NOTICE_UPDATE_SUCCESS(OK, "채용 공고가 성공적으로 수정되었습니다."),
    NOTICE_DELETE_SUCCESS(OK, "채용 공고가 성공적으로 삭제되었습니다."),
    APPLICATION_SUCCESS(OK, "지원되셨습니다.");

    private final HttpStatus httpStatus;
    private final String detail;
}
