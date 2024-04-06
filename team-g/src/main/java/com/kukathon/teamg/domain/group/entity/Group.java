package com.kukathon.teamg.domain.group.entity;

import com.kukathon.teamg.common.entity.BaseEntity;
import com.kukathon.teamg.domain.category.entity.Category;
import com.kukathon.teamg.domain.group.dto.request.GroupCreateRequest;
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

    @Column(name = "group_name", nullable = false)
    private String name;

    @Column(name = "group_limit_number", nullable = false)
    private int groupLimitNumber;

    @Column(name = "start_date", nullable = false)
    private DateTime startDate;

    @Column(name = "finish_date", nullable = false)
    private DateTime finishDate;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public static Group toEntity(Member member, GroupCreateRequest request, Category category) {
        return Group.builder()
            .groupLimitNumber(request.getLimitNumber())
            .name(request.getGroupName())
            .startDate(request.getStartDate())
            .finishDate(request.getFinishDate())
            .category(category)
            .member(member)
            .build();
    }
}
