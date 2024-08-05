package com.example.wantedpreonboardingbackend.notice.entity;

import com.example.wantedpreonboardingbackend.company.entity.Company;
import com.example.wantedpreonboardingbackend.notice.dto.NoticeCreateDto;
import com.example.wantedpreonboardingbackend.notice.dto.NoticeUpdateDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "notice")
public class Notice {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String position;

    @Column(nullable = false)
    private Integer compensation;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TechRole techRole;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    public Notice(NoticeCreateDto noticeCreateDto, Company company) {
        this.position = noticeCreateDto.getPosition();
        this.compensation = noticeCreateDto.getCompensation();
        this.techRole = noticeCreateDto.getTech();
        this.content = noticeCreateDto.getContent();
        this.company = company;
    }

    public Notice(NoticeUpdateDto noticeUpdateDto, Company company) {
        this.position = noticeUpdateDto.getPosition();
        this.compensation = noticeUpdateDto.getCompensation();
        this.techRole = noticeUpdateDto.getTech();
        this.content = noticeUpdateDto.getContent();
        this.company = company;
    }

    public void update(String position, Integer compensation, String content, TechRole techRole) {
        if (position != null) {
            this.position = position;
        }
        if (compensation != null) {
            this.compensation = compensation;
        }
        if (content != null) {
            this.content = content;
        }
        if (techRole != null) {
            this.techRole = techRole;
        }
    }
}
