package com.kukathon.teamg.domain.group.service;

import com.kukathon.teamg.auth.domain.CustomUserDetails;
import com.kukathon.teamg.common.error.ApplicationException;
import com.kukathon.teamg.domain.group.dto.request.RequestCreateGroupDto;
import com.kukathon.teamg.domain.group.entity.Group;
import com.kukathon.teamg.domain.group.repository.GroupRepository;
import com.kukathon.teamg.domain.member.entity.Member;
import com.kukathon.teamg.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.kukathon.teamg.common.error.ErrorCode.CONTENT_NOT_FOUND;
import static com.kukathon.teamg.common.error.ErrorCode.MEMBER_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional
public class GroupService {

    private final MemberRepository memberRepository;
    private final GroupRepository groupRepository;

    public Long createGroup(CustomUserDetails customUserDetails, RequestCreateGroupDto groupDto) {
        Member member = memberRepository.findById(customUserDetails.getId())
                .orElseThrow(() -> new ApplicationException(MEMBER_NOT_FOUND));
        Group group = groupRepository.save(Group.toEntitiy(member,groupDto));
        return group.getId();
    }
}
