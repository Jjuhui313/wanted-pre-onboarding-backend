package com.example.wantedpreonboardingbackend.notice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class NoticeDetailResponseDto {
    private Long noticeId;
    private String companyName;
    private String country;
    private String region;
    private String position;
    private Integer compensation;
    private String tech;
    private String content;
    private List<OtherNoticeDto> otherNoticeDto;
}
