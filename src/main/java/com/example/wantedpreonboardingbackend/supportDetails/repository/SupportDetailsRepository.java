package com.example.wantedpreonboardingbackend.supportDetails.repository;

import com.example.wantedpreonboardingbackend.supportDetails.entity.SupportDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SupportDetailsRepository extends JpaRepository<SupportDetails, Long> {
    Optional<SupportDetails> findByNoticeIdAndUserId(Long noticeId, Long userId);
}
