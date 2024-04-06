package com.kukathon.teamg.domain.member.entity;

import com.kukathon.teamg.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
public class Member extends BaseEntity {

    @Id
    @Column(name = "member_id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "member_name",nullable = false)
    private String member_name;

    @Column(name = "gender",nullable = false)
    private String gender;

    @Column(name = "birth",nullable = false)
    private String birth;
}
