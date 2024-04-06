package com.kukathon.teamg.domain.content.dto.request;

import lombok.Getter;

@Getter
public class UpdateContentRequest {

    private Long memberId;

    private Integer categoryId;

    private Integer contentId;

    private Integer contentName;

    private Boolean isChecked;
}
