package com.example.wantedpreonboardingbackend.notice.entity;

import com.example.wantedpreonboardingbackend.company.entity.Company;
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
    private int compensation;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TechRole techRole;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    public Notice(Long id, String position, int compensation, TechRole techRole, String content, Company company) {
        this.id = id;
        this.position = position;
        this.compensation = compensation;
        this.techRole = techRole;
        this.content = content;
        this.company = company;
    }
}
