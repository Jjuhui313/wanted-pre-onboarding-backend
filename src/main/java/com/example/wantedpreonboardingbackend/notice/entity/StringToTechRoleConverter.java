package com.example.wantedpreonboardingbackend.notice.entity;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToTechRoleConverter implements Converter<String, TechRole> {
    @Override
    public TechRole convert(String source) {
        for (TechRole role : TechRole.values()) {
            if (role.getEnName().equalsIgnoreCase(source)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid TechRole: " + source);
    }
}
