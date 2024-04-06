package com.kukathon.teamg.domain.member.service;

import com.kukathon.teamg.auth.dto.TokenInfoResponse;
import com.kukathon.teamg.auth.utils.JwtTokenProvider;
import com.kukathon.teamg.domain.member.dto.MemberCreateRequest;
import com.kukathon.teamg.domain.member.dto.MemberCreateResponse;
import com.kukathon.teamg.domain.member.entity.Member;
import com.kukathon.teamg.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public MemberCreateResponse create(String accessToken, MemberCreateRequest request) {
        TokenInfoResponse response = jwtTokenProvider.parse(accessToken);
        Member member = Member.of(response.email(), request);

        return new MemberCreateResponse(memberRepository.save(member).getEmail());
    }
}
