package com.example.wantedpreonboardingbackend.notice.entity;

import lombok.Getter;

@Getter
public enum TechRole {
    PYTHON("Python"),
    DJANGO("Djange"),
    SPRING("Spring"),
    JAVA("JAVA"),
    JAVASCRIPT("javascript");

    private String enName;

    TechRole(String enName) {
        this.enName = enName;
    }
}
