package com.kukathon.teamg.domain.group.entity;

import com.kukathon.teamg.common.entity.BaseEntity;
import com.kukathon.teamg.domain.group.dto.request.RequestCreateGroupDto;
import com.kukathon.teamg.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;
import org.joda.time.DateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Group extends BaseEntity {
    @Id
    @Column(name = "group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "group_name",nullable = false)
    private String name;

    @Column(name = "group_limit_number",nullable = false)
    private int groupLimitNumber;

    @Column(name = "start_date",nullable = false)
    private DateTime startDate;

    @Column(name = "finish_date",nullable = false)
    private DateTime finishDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public static Group toEntitiy(Member member, RequestCreateGroupDto groupDto) {
        return Group.builder()
                .groupLimitNumber(groupDto.getLimitNumber())
                .name(groupDto.getGroupName())
                .startDate(groupDto.getStartDate())
                .finishDate(groupDto.getFinishDate())
                .member(member)
                .build();
    }
}
