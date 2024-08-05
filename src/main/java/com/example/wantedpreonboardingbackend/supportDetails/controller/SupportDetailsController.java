package com.example.wantedpreonboardingbackend.supportDetails.controller;

import com.example.wantedpreonboardingbackend.common.msg.SuccessMsg;
import com.example.wantedpreonboardingbackend.supportDetails.dto.SupportDetailsRequestDto;
import com.example.wantedpreonboardingbackend.supportDetails.service.SupportDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/application")
@RequiredArgsConstructor
public class SupportDetailsController {
    private final SupportDetailsService supportDetailsService;

    @PostMapping
    public ResponseEntity<SuccessMsg> applyNotice(@RequestBody SupportDetailsRequestDto supportDetailsRequestDto) {
        return ResponseEntity.ok(supportDetailsService.applyNotice(supportDetailsRequestDto));
    }
}
