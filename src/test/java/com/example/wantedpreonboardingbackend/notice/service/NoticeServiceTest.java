package com.example.wantedpreonboardingbackend.notice.service;

import com.example.wantedpreonboardingbackend.common.CustomException;
import com.example.wantedpreonboardingbackend.common.msg.ExceptionMsg;
import com.example.wantedpreonboardingbackend.common.msg.SuccessMsg;
import com.example.wantedpreonboardingbackend.company.entity.CountryRole;
import com.example.wantedpreonboardingbackend.notice.dto.NoticeCreateDto;
import com.example.wantedpreonboardingbackend.notice.dto.NoticeUpdateDto;
import com.example.wantedpreonboardingbackend.notice.dto.NoticeUpdateresponseDto;
import com.example.wantedpreonboardingbackend.notice.entity.Notice;
import com.example.wantedpreonboardingbackend.notice.entity.TechRole;
import com.example.wantedpreonboardingbackend.notice.repository.NoticeRepository;
import com.example.wantedpreonboardingbackend.company.entity.Company;
import com.example.wantedpreonboardingbackend.company.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class NoticeServiceTest {

    @InjectMocks
    private NoticeService noticeService;

    @Mock
    private NoticeRepository noticeRepository;

    @Mock
    private CompanyRepository companyRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateNotice_Success() {
        NoticeCreateDto noticeCreateDto = new NoticeCreateDto(1L, "Backend Developer", 1500000, "Job Description", TechRole.PYTHON);
        Company company = new Company(1L, "Company Name", CountryRole.KOREA, "Seoul");

        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));
        when(noticeRepository.save(any(Notice.class))).thenReturn(new Notice(noticeCreateDto, company));

        String result = noticeService.createNotice(noticeCreateDto);
        assertEquals(SuccessMsg.NOTICE_CREATE_SUCCESS.getDetail(), result);

        verify(companyRepository, times(1)).findById(1L);
        verify(noticeRepository, times(1)).save(any(Notice.class));
    }

    @Test
    public void testUpdateNotice_Success() {
        Long noticeId = 1L;
        NoticeUpdateDto noticeUpdateDto = new NoticeUpdateDto(1L, "Updated Position", 2000000, "Updated Content", TechRole.JAVA);
        Company company = new Company(1L, "Company Name", CountryRole.KOREA, "Seoul");
        Notice notice = new Notice(noticeUpdateDto, company);

        when(noticeRepository.findById(noticeId)).thenReturn(Optional.of(notice));
        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));

        NoticeUpdateresponseDto result = noticeService.updateNotice(noticeId, noticeUpdateDto);

        assertEquals(company.getId(), result.getCompanyId());
        assertEquals("Updated Position", result.getPosition());
        assertEquals(2000000, result.getCompensation());
        assertEquals("Updated Content", result.getContent());
        assertEquals(TechRole.JAVA, result.getTech());

        verify(noticeRepository, times(1)).findById(noticeId);
        verify(companyRepository, times(1)).findById(1L);
    }

    @Test
    public void testUpdateNotice_CompanyNotFound() {
        Long noticeId = 1L;
        NoticeUpdateDto noticeUpdateDto = new NoticeUpdateDto(1L, "Updated Position", 2000000, "Updated Content", TechRole.JAVA);
        Notice notice = new Notice(noticeUpdateDto, new Company(1L, "Company Name", CountryRole.KOREA, "Seoul"));

        when(noticeRepository.findById(noticeId)).thenReturn(Optional.of(notice));
        when(companyRepository.findById(1L)).thenReturn(Optional.empty());

        CustomException exception = assertThrows(CustomException.class, () -> noticeService.updateNotice(noticeId, noticeUpdateDto));
        assertEquals(ExceptionMsg.COMPANY_NOT_FOUND.getDetail(), exception.getExceptionMsg().getDetail());

        verify(noticeRepository, times(1)).findById(noticeId);
        verify(companyRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteNotice_Success() {
        Long noticeId = 1L;
        Long companyId = 1L;
        Company company = new Company(1L, "Company Name", CountryRole.KOREA, "Seoul");
        Notice notice = new Notice(new NoticeUpdateDto(companyId, "Position", 1000000, "Content", TechRole.PYTHON), company);

        when(noticeRepository.findById(noticeId)).thenReturn(Optional.of(notice));
        when(companyRepository.findById(companyId)).thenReturn(Optional.of(company));

        String result = noticeService.deleteNotice(noticeId, companyId);
        assertEquals(SuccessMsg.NOTICE_DELETE_SUCCESS.getDetail(), result);

        verify(noticeRepository, times(1)).findById(noticeId);
        verify(companyRepository, times(1)).findById(companyId);
        verify(noticeRepository, times(1)).delete(notice);
    }
}
