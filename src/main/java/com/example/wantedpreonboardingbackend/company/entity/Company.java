package com.example.wantedpreonboardingbackend.company.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "company")
public class Company {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private CountryRole countryRole;

    @Column(nullable = false)
    private String region;

    public Company(Long id, String companyName, CountryRole countryRole, String region) {
        this.id = id;
        this.companyName = companyName;
        this.countryRole = countryRole;
        this.region = region;
    }
}
