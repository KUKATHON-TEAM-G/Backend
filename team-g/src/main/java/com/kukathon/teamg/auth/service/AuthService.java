package com.kukathon.teamg.auth.service;

import com.kukathon.teamg.auth.dto.KakaoOAuthResponse;
import com.kukathon.teamg.auth.dto.LoginResponse;
import com.kukathon.teamg.auth.dto.TokenResponse;
import com.kukathon.teamg.auth.utils.JwtTokenProvider;
import com.kukathon.teamg.auth.utils.TokenUtils;
import com.kukathon.teamg.domain.member.entity.Member;
import com.kukathon.teamg.domain.member.repository.MemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final TokenUtils tokenUtils;
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginResponse login(String authCode) {
        // MemberInfo 추출
        KakaoOAuthResponse kakaoOAuthResponse = tokenUtils.extractMemberInfoByAuthCode(
            authCode);

        // 추출한 정보를 토대로 JWT Token 생성
        TokenResponse tokenResponse = jwtTokenProvider.createTokenInfo(kakaoOAuthResponse.email());

        Optional<Member> member = memberRepository.findByEmail(kakaoOAuthResponse.email());
        if (!member.isPresent()) {
            return new LoginResponse(tokenResponse.accessToken(), tokenResponse.refreshToken(),
	false);
        }

        return new LoginResponse(tokenResponse.accessToken(), tokenResponse.refreshToken(), true);
    }
}
