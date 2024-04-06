package com.kukathon.teamg.domain.member.entity;

import com.kukathon.teamg.common.entity.BaseEntity;
import com.kukathon.teamg.domain.category.entity.Category;
import com.kukathon.teamg.domain.content.entity.Content;
import com.kukathon.teamg.domain.group.entity.Group;
import com.kukathon.teamg.domain.member.dto.MemberCreateRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Entity
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @Column(name = "member_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_email", nullable = false)
    private String email;

    @Column(name = "member_name")
    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "birth")
    private String birthday;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Group> groups = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> categories = new ArrayList<>();

    private Member(String email, String name, String gender, String birthday) {
        this.email = email;
        this.name = name;
        this.gender = Gender.valueOf(gender);
        this.birthday = birthday;
    }

    public static Member of(String email, MemberCreateRequest request) {
        return new Member(email, request.nickname(), request.gender(), request.birthday());
    }
}
