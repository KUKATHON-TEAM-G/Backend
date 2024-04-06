package com.kukathon.teamg.domain.group.controller;

import com.kukathon.teamg.auth.domain.CustomUserDetails;
import com.kukathon.teamg.domain.group.dto.request.GroupCreateRequest;
import com.kukathon.teamg.domain.group.service.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api/groups")
@RestController
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping
    public ResponseEntity<Long> createGroup(
        @AuthenticationPrincipal CustomUserDetails customUserDetails,
        @RequestBody GroupCreateRequest request) {
        return new ResponseEntity<>(groupService.create(customUserDetails, request), HttpStatus.OK);
    }
}
