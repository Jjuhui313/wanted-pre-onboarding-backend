package com.example.wantedpreonboardingbackend.notice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NoticeResponseDto {
    private Long noticeId;
    private String companyName;
    private String county;
    private String region;
    private String position;
    private int compensation;
    private String tech;
}
