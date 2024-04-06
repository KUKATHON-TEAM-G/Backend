package com.kukathon.teamg.domain.content.controller;

import com.kukathon.teamg.auth.domain.CustomUserDetails;
import com.kukathon.teamg.domain.content.dto.response.DailyContentResponse;
import com.kukathon.teamg.domain.content.service.ContentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/contents")
@Slf4j
public class ContentController {

    private final ContentService contentService;

    @PatchMapping
    public ResponseEntity<Void> isChecked(
        @RequestParam(name = "contentId") Long contentId) {
        contentService.updateCheck(contentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<DailyContentResponse> findAllContent(
        @AuthenticationPrincipal CustomUserDetails customUserDetails,
        @RequestParam("date") @DateTimeFormat(pattern = "yyyyMMdd") DateTime date) {
        return new ResponseEntity<>(contentService.findAllContents(customUserDetails, date),
            HttpStatus.OK);
    }
}
