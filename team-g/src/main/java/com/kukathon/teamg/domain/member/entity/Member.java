package com.kukathon.teamg.domain.member.entity;

import com.kukathon.teamg.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Entity
public class Member extends BaseEntity {

    @Id
    @Column(name = "member_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_email", nullable = false)
    private String email;

    @Column(name = "member_name")
    private String name;

    @Column(name = "gender")
    private String gender;

    @Column(name = "birth")
    private String birthday;
}
