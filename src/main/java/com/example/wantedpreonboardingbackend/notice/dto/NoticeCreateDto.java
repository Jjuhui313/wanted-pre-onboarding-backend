package com.example.wantedpreonboardingbackend.notice.dto;

import com.example.wantedpreonboardingbackend.notice.entity.TechRole;
import lombok.Getter;

@Getter
public class NoticeCreateDto {
    private Long companyId;
    private String position;
    private int compensation;
    private String content;
    private TechRole tech;

    public NoticeCreateDto(Long companyId, String position, int compensation, String content, TechRole tech) {
        this.companyId = companyId;
        this.position = position;
        this.compensation = compensation;
        this.content = content;
        this.tech = tech;
    }
}
