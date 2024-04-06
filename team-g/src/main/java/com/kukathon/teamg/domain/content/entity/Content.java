package com.kukathon.teamg.domain.content.entity;

import com.kukathon.teamg.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.joda.time.DateTime;

import java.lang.annotation.Documented;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Content extends BaseEntity {

    @Id
    @Column(name = "content_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content_name",nullable = false)
    private String contentName;

    @Column(name = "content_score",nullable = false)
    private Integer content_score;


    @Column(name = "is_checked",nullable = false)
    private Integer is_checked;

    @Column(name = "date",nullable = false)
    private DateTime date;

}
