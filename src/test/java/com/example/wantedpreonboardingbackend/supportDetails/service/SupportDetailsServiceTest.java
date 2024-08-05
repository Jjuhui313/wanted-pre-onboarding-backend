package com.example.wantedpreonboardingbackend.supportDetails.service;

import com.example.wantedpreonboardingbackend.common.CustomException;
import com.example.wantedpreonboardingbackend.common.msg.SuccessMsg;
import com.example.wantedpreonboardingbackend.notice.entity.Notice;
import com.example.wantedpreonboardingbackend.notice.repository.NoticeRepository;
import com.example.wantedpreonboardingbackend.supportDetails.dto.SupportDetailsRequestDto;
import com.example.wantedpreonboardingbackend.supportDetails.entity.SupportDetails;
import com.example.wantedpreonboardingbackend.supportDetails.repository.SupportDetailsRepository;
import com.example.wantedpreonboardingbackend.user.entity.User;
import com.example.wantedpreonboardingbackend.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SupportDetailsServiceTest {

    @InjectMocks
    private SupportDetailsService supportDetailsService;

    @Mock
    private SupportDetailsRepository supportDetailsRepository;

    @Mock
    private NoticeRepository noticeRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testApplyNotice_Success() {
        Long noticeId = 1L;
        Long userId = 1L;
        Notice notice = new Notice();
        User user = new User();
        SupportDetailsRequestDto requestDto = new SupportDetailsRequestDto(noticeId, userId);

        when(noticeRepository.findById(noticeId)).thenReturn(Optional.of(notice));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(supportDetailsRepository.findByNoticeIdAndUserId(noticeId, userId)).thenReturn(Optional.empty());

        SuccessMsg result = supportDetailsService.applyNotice(requestDto);
        assertEquals(SuccessMsg.APPLICATION_SUCCESS, result);

        verify(noticeRepository, times(1)).findById(noticeId);
        verify(userRepository, times(1)).findById(userId);
        verify(supportDetailsRepository, times(1)).findByNoticeIdAndUserId(noticeId, userId);
        verify(supportDetailsRepository, times(1)).save(any(SupportDetails.class));
    }

    @Test
    public void testApplyNotice_AlreadyApplied() {
        Long noticeId = 1L;
        Long userId = 1L;
        Notice notice = new Notice();
        User user = new User();
        SupportDetails supportDetails = new SupportDetails(null, user, notice);
        SupportDetailsRequestDto requestDto = new SupportDetailsRequestDto(noticeId, userId);

        when(noticeRepository.findById(noticeId)).thenReturn(Optional.of(notice));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(supportDetailsRepository.findByNoticeIdAndUserId(noticeId, userId)).thenReturn(Optional.of(supportDetails));

        assertThrows(CustomException.class, () -> supportDetailsService.applyNotice(requestDto));

        verify(noticeRepository, times(1)).findById(noticeId);
        verify(userRepository, times(1)).findById(userId);
        verify(supportDetailsRepository, times(1)).findByNoticeIdAndUserId(noticeId, userId);
    }

    @Test
    public void testApplyNotice_NoticeNotFound() {
        Long noticeId = 1L;
        Long userId = 1L;
        SupportDetailsRequestDto requestDto = new SupportDetailsRequestDto(noticeId, userId);

        when(noticeRepository.findById(noticeId)).thenReturn(Optional.empty());

        assertThrows(CustomException.class, () -> supportDetailsService.applyNotice(requestDto));

        verify(noticeRepository, times(1)).findById(noticeId);
        verify(userRepository, never()).findById(userId);
        verify(supportDetailsRepository, never()).findByNoticeIdAndUserId(noticeId, userId);
    }

    @Test
    public void testApplyNotice_UserNotFound() {
        Long noticeId = 1L;
        Long userId = 1L;
        Notice notice = new Notice();
        SupportDetailsRequestDto requestDto = new SupportDetailsRequestDto(noticeId, userId);

        when(noticeRepository.findById(noticeId)).thenReturn(Optional.of(notice));
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(CustomException.class, () -> supportDetailsService.applyNotice(requestDto));

        verify(noticeRepository, times(1)).findById(noticeId);
        verify(userRepository, times(1)).findById(userId);
        verify(supportDetailsRepository, never()).findByNoticeIdAndUserId(noticeId, userId);
    }
}
