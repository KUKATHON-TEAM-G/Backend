package com.kukathon.teamg.domain.group;

import com.kukathon.teamg.common.entity.BaseEntity;
import jakarta.persistence.*;

@Entity
public class Group extends BaseEntity {
    @Id
    @Column(name = "group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "group_code",nullable = false)
    private int groupCode;

    @Column(name = "group_limit_number",nullable = false)
    private int groupLimitNumber;

    @Column(name = "field",nullable = false)
    private int field;
}
