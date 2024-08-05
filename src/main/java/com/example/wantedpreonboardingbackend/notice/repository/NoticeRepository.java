package com.example.wantedpreonboardingbackend.notice.repository;

import com.example.wantedpreonboardingbackend.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    @Query("SELECT n FROM Notice n WHERE " +
            "n.company.companyName LIKE %:search% OR " +
            "n.position LIKE %:search% OR " +
            "CAST(n.techRole AS string) LIKE %:search%")
    List<Notice> searchNotices(@Param("search") String search);
    List<Notice> findByCompanyId(Long companyId);
}
