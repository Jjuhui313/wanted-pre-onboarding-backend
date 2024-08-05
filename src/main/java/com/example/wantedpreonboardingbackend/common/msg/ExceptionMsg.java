package com.example.wantedpreonboardingbackend.common.msg;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ExceptionMsg {
    /* 400 BAD_REQUEST : 잘못된 요청 */
    ALREADY_APPLIED(BAD_REQUEST, "이미 지원하신 채용공고 입니다."),

    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
    UNAUTHORIZED_UPDATE_OR_DELETE(UNAUTHORIZED,"채용공고는 해당 채용 공고를 올린 회사만 수정 / 삭제할 수 있습니다."),

    /* 403 FORBIDDEN : 권한 없음 */
    USER_FORBIDDEN(FORBIDDEN, "권한이 없습니다."),

    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
    USER_NOT_FOUND(NOT_FOUND, "존재하지 않는 사용자입니다."),
    COMPANY_NOT_FOUND(NOT_FOUND, "존재하지 않는 회사입니다."),
    NOTICE_NOT_FOUND(NOT_FOUND, "존재하지 않는 채용공고입니다.."),

    /* 409 CONFLICT : Resource 의 현재 상태와 충돌. 보통 중복된 데이터 존재 */
    DUPLICATE_USER(CONFLICT,"이미 가입된 사용자입니다.");


    private final HttpStatus httpStatus;
    private final String detail;
}
