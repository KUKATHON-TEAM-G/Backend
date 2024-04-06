package com.kukathon.teamg.domain.group.service;

import com.kukathon.teamg.auth.domain.CustomUserDetails;
import com.kukathon.teamg.common.error.ApplicationException;
import com.kukathon.teamg.domain.category.entity.Category;
import com.kukathon.teamg.domain.category.repository.CategoryRepository;
import com.kukathon.teamg.domain.group.dto.request.GroupCreateRequest;
import com.kukathon.teamg.domain.group.entity.Group;
import com.kukathon.teamg.domain.group.repository.GroupRepository;
import com.kukathon.teamg.domain.member.entity.Member;
import com.kukathon.teamg.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.kukathon.teamg.common.error.ErrorCode.MEMBER_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional
public class GroupService {

    private final MemberRepository memberRepository;
    private final GroupRepository groupRepository;
    private final CategoryRepository categoryRepository;

    public Long create(CustomUserDetails customUserDetails, GroupCreateRequest request) {
        Member member = memberRepository.findById(customUserDetails.getId())
            .orElseThrow(() -> new ApplicationException(MEMBER_NOT_FOUND));

        Category category = categoryRepository.findByName(request.category())
            .orElseThrow(() -> new ApplicationException(MEMBER_NOT_FOUND));

        Group group = Group.toEntity(member, request, category);

        member.addGroup(group);
        return groupRepository.save(group).getId();
    }
}
