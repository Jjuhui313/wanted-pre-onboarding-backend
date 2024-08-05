package com.example.wantedpreonboardingbackend.notice.entity;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final StringToTechRoleConverter stringToTechRoleConverter;

    public WebConfig(StringToTechRoleConverter stringToTechRoleConverter) {
        this.stringToTechRoleConverter = stringToTechRoleConverter;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToTechRoleConverter);
    }
}
