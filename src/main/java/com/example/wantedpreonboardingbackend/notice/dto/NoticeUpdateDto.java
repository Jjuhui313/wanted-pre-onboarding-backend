package com.example.wantedpreonboardingbackend.notice.dto;

import com.example.wantedpreonboardingbackend.notice.entity.TechRole;
import lombok.Getter;

@Getter
public class NoticeUpdateDto {
    private Long companyId;
    private String position;
    private Integer compensation;
    private String content;
    private TechRole tech;

    public NoticeUpdateDto(Long companyId, String position, Integer compensation, String content, TechRole tech) {
        this.companyId = companyId;
        this.position = position;
        this.compensation = compensation;
        this.content = content;
        this.tech = tech;
    }
}
