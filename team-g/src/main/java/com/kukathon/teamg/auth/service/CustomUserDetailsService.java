package com.kukathon.teamg.auth.service;

import com.kukathon.teamg.auth.domain.CustomUserDetails;
import com.kukathon.teamg.domain.member.entity.Member;
import com.kukathon.teamg.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("해당 이메일로 가입된 계정이 존재하지 않습니다."));
        return new CustomUserDetails(member);
    }
}
