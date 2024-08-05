package com.example.wantedpreonboardingbackend.company.entity;

import lombok.Getter;

@Getter
public enum CountryRole {
    KOREA("한국"),
    USA("미국"),
    JAPAN("일본");

    private final String krName;

    CountryRole(String krName) {
        this.krName = krName;
    }
}
