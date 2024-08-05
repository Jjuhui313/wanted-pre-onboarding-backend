package com.example.wantedpreonboardingbackend.notice.service;

import com.example.wantedpreonboardingbackend.common.CustomException;
import com.example.wantedpreonboardingbackend.common.msg.ExceptionMsg;
import com.example.wantedpreonboardingbackend.common.msg.SuccessMsg;
import com.example.wantedpreonboardingbackend.company.entity.Company;
import com.example.wantedpreonboardingbackend.company.repository.CompanyRepository;
import com.example.wantedpreonboardingbackend.notice.dto.*;
import com.example.wantedpreonboardingbackend.notice.entity.Notice;
import com.example.wantedpreonboardingbackend.notice.entity.TechRole;
import com.example.wantedpreonboardingbackend.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.wantedpreonboardingbackend.common.msg.ExceptionMsg.*;
import static com.example.wantedpreonboardingbackend.common.msg.SuccessMsg.NOTICE_CREATE_SUCCESS;
import static com.example.wantedpreonboardingbackend.common.msg.SuccessMsg.NOTICE_DELETE_SUCCESS;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;
    private final CompanyRepository companyRepository;

    @Transactional
    public String createNotice(NoticeCreateDto noticeCreateDto) {
        Company company = companyRepository.findById(noticeCreateDto.getCompanyId()).orElseThrow(
                () -> new CustomException(COMPANY_NOT_FOUND)
        );
        Notice notice = new Notice(noticeCreateDto, company);

        noticeRepository.save(notice);

        return NOTICE_CREATE_SUCCESS.getDetail();
    }

    @Transactional
    public NoticeUpdateresponseDto updateNotice(Long noticeId, NoticeUpdateDto noticeUpdateDto) {
        Notice notice = noticeRepository.findById(noticeId).orElseThrow(
                () -> new CustomException(NOTICE_NOT_FOUND)
        );

        Company company = companyRepository.findById(noticeUpdateDto.getCompanyId()).orElseThrow(
                () -> new CustomException(COMPANY_NOT_FOUND)
        );

        if (!company.getId().equals(notice.getCompany().getId())) {
            throw new CustomException(UNAUTHORIZED_UPDATE_OR_DELETE);
        }

        notice.update(noticeUpdateDto.getPosition(), noticeUpdateDto.getCompensation(), noticeUpdateDto.getContent(), noticeUpdateDto.getTech());

        return new NoticeUpdateresponseDto(
                company.getId(),
                notice.getPosition(),
                notice.getCompensation(),
                notice.getContent(),
                notice.getTechRole()
        );
    }

    @Transactional
    public String deleteNotice(Long noticeId, Long companyId) {
        Notice notice = noticeRepository.findById(noticeId).orElseThrow(
                () -> new CustomException(NOTICE_NOT_FOUND)
        );

        Company company = companyRepository.findById(companyId).orElseThrow(
                () -> new CustomException(COMPANY_NOT_FOUND)
        );

        if (!company.getId().equals(notice.getCompany().getId())) {
            throw new CustomException(UNAUTHORIZED_UPDATE_OR_DELETE);
        }

        noticeRepository.delete(notice);

        return NOTICE_DELETE_SUCCESS.getDetail();
    }

    @Transactional(readOnly = true)
    public List<NoticeResponseDto> getNotice() {
        List<Notice> notices = noticeRepository.findAll();
        return notices.stream()
                .map(notice -> new NoticeResponseDto(
                        notice.getId(),
                        notice.getCompany().getCompanyName(),
                        notice.getCompany().getCountryRole().toString(),
                        notice.getCompany().getRegion(),
                        notice.getPosition(),
                        notice.getCompensation(),
                        notice.getTechRole().getEnName()
                ))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<NoticeResponseDto> searchNotices(String search) {
        List<Notice> notices = noticeRepository.searchNotices(search.toUpperCase());
        return notices.stream()
                .map(notice -> new NoticeResponseDto(
                        notice.getId(),
                        notice.getCompany().getCompanyName(),
                        notice.getCompany().getCountryRole().toString(),
                        notice.getCompany().getRegion(),
                        notice.getPosition(),
                        notice.getCompensation(),
                        notice.getTechRole().getEnName()
                ))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public NoticeDetailResponseDto getNoticeDetail(Long noticeId) {
        Notice notice = noticeRepository.findById(noticeId).orElseThrow(
                () -> new CustomException(NOTICE_NOT_FOUND)
        );

        List<OtherNoticeDto> otherNotices = noticeRepository.findByCompanyId(notice.getCompany().getId()).stream()
                .filter(n -> !n.getId().equals(noticeId)) // 현재 공고를 제외한 다른 공고만 포함
                .map(n -> new OtherNoticeDto(n.getId(), n.getPosition()))
                .collect(Collectors.toList());

        return new NoticeDetailResponseDto(
                notice.getId(),
                notice.getCompany().getCompanyName(),
                notice.getCompany().getCountryRole().toString(),
                notice.getCompany().getRegion(),
                notice.getPosition(),
                notice.getCompensation(),
                notice.getTechRole().getEnName(),
                notice.getContent(),
                otherNotices
        );
    }
}
