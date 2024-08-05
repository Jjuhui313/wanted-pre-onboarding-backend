package com.example.wantedpreonboardingbackend.notice.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum TechRole {
    PYTHON("Python"),
    DJANGO("Django"),
    SPRING("Spring"),
    JAVA("JAVA"),
    JAVASCRIPT("javascript");

    private final String enName;

    TechRole(String enName) {
        this.enName = enName;
    }

    @JsonValue
    public String getEnName() {
        return enName;
    }
}
