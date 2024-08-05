package com.example.wantedpreonboardingbackend.supportDetails.dto;

import lombok.Getter;

@Getter
public class SupportDetailsRequestDto {
    private Long noticeId;
    private Long userId;

    public SupportDetailsRequestDto(Long noticeId, Long userId) {
        this.noticeId = noticeId;
        this.userId = userId;
    }
}
