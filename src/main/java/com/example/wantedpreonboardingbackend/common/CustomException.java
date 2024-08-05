package com.example.wantedpreonboardingbackend.common;

import com.example.wantedpreonboardingbackend.common.msg.ExceptionMsg;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {
    private final ExceptionMsg exceptionMsg;
}
