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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.wantedpreonboardingbackend.common.msg.ExceptionMsg.*;
import static com.example.wantedpreonboardingbackend.common.msg.SuccessMsg.APPLICATION_SUCCESS;

@Service
@RequiredArgsConstructor
public class SupportDetailsService {
    private final SupportDetailsRepository supportDetailsRepository;
    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;

    //사용자가 채용공고에 지원
    @Transactional
    public SuccessMsg applyNotice(SupportDetailsRequestDto supportDetailsRequestDto) {
        Notice notice = noticeRepository.findById(supportDetailsRequestDto.getNoticeId()).orElseThrow(
                () -> new CustomException(NOTICE_NOT_FOUND)
        );

        User user = userRepository.findById(supportDetailsRequestDto.getUserId()).orElseThrow(
                () -> new CustomException(USER_NOT_FOUND)
        );

        if (supportDetailsRepository.findByNoticeIdAndUserId(supportDetailsRequestDto.getNoticeId(), supportDetailsRequestDto.getUserId()).isPresent()) {
            throw new CustomException(ALREADY_APPLIED);
        }

        SupportDetails supportDetails = new SupportDetails(null, user, notice);
        supportDetailsRepository.save(supportDetails);

        return APPLICATION_SUCCESS;
    }
}
