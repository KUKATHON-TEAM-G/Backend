package com.kukathon.teamg.auth.controller;

import com.kukathon.teamg.auth.dto.LoginResponse;
import com.kukathon.teamg.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/oauth")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/kakao")
    public ResponseEntity<LoginResponse> login(
        @RequestParam("code") String code
    ) {
        return ResponseEntity.ok(authService.login(code));
    }
}
