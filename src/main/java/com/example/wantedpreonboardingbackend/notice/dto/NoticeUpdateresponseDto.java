package com.example.wantedpreonboardingbackend.notice.dto;

import com.example.wantedpreonboardingbackend.notice.entity.TechRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NoticeUpdateresponseDto {
    private Long companyId;
    private String position;
    private int compensation;
    private String content;
    private TechRole tech;
}
