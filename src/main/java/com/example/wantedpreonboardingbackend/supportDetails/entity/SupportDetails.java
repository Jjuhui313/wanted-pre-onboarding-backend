package com.example.wantedpreonboardingbackend.supportDetails.entity;

import com.example.wantedpreonboardingbackend.notice.entity.Notice;
import com.example.wantedpreonboardingbackend.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "support_details")
public class SupportDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_id")
    private Notice notice;

    public SupportDetails(Long id, User user, Notice notice) {
        this.id = id;
        this.user = user;
        this.notice = notice;
    }
}
