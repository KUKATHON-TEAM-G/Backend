package com.kukathon.teamg.domain.member.dto;

public record MemberCreateRequest(
    String nickname,
    String gender,
    String birthday
) {

}
