package com.kukathon.teamg.domain.group.dto.request;

import lombok.Builder;
import lombok.Getter;
import org.joda.time.DateTime;

@Builder
@Getter
public class RequestCreateGroupDto {

    private String groupName;

    private Integer limitNumber;

    private DateTime startDate;

    private DateTime finishDate;

}
