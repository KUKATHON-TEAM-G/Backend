package com.kukathon.teamg.domain.member.controller;

import com.kukathon.teamg.domain.member.dto.MemberCreateRequest;
import com.kukathon.teamg.domain.member.dto.MemberCreateResponse;
import com.kukathon.teamg.domain.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberCreateResponse> signup(@RequestBody MemberCreateRequest request,
        HttpServletRequest httpServletRequest) {
        String accessToken = String.valueOf(httpServletRequest.getHeaders("Authorization"));

        return new ResponseEntity<>(memberService.create(accessToken, request), HttpStatus.OK);
    }

}
