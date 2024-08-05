package com.example.wantedpreonboardingbackend.notice.controller;

import com.example.wantedpreonboardingbackend.common.msg.SuccessMsg;
import com.example.wantedpreonboardingbackend.notice.dto.*;
import com.example.wantedpreonboardingbackend.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notice")
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;

    //채용공고 등록
    @PostMapping
    private ResponseEntity<String> createNotice(@RequestBody NoticeCreateDto noticeCreateDto) {
        return ResponseEntity.ok(noticeService.createNotice(noticeCreateDto));
    }

    //채용공고 수정
    @PatchMapping("{noticeId}")
    public ResponseEntity<NoticeUpdateresponseDto> updateNotice(
            @PathVariable Long noticeId,
            @RequestBody NoticeUpdateDto noticeUpdateDto) {
        return ResponseEntity.ok(noticeService.updateNotice(noticeId, noticeUpdateDto));
    }

    //채용공고 삭제
    @DeleteMapping("{noticeId}")
    public ResponseEntity<String> deleteNotice(
            @PathVariable Long noticeId,
            @RequestParam Long companyId) {
        return ResponseEntity.ok(noticeService.deleteNotice(noticeId, companyId));
    }

    //채용공고 리스트 출력
    @GetMapping()
    public ResponseEntity<List<NoticeResponseDto>> getNotice() {
        return ResponseEntity.ok(noticeService.getNotice());
    }

    //채용공고 검색
    @GetMapping("/search")
    public ResponseEntity<List<NoticeResponseDto>> searchNotices(@RequestParam String search) {
        return ResponseEntity.ok(noticeService.searchNotices(search));
    }

    //채용 공고 삭제
    @GetMapping("/{noticeId}")
    public ResponseEntity<NoticeDetailResponseDto> getNoticeDetail(@PathVariable Long noticeId) {
        return ResponseEntity.ok(noticeService.getNoticeDetail(noticeId));
    }

}
