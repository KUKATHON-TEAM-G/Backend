package com.kukathon.teamg.domain.group.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import org.joda.time.DateTime;

@Builder
@Getter
public record GroupCreateRequest(String groupName, Integer limitNumber,
		 @JsonFormat(pattern = "yyyyMMdd") DateTime startDate,
		 @JsonFormat(pattern = "yyyyMMdd") DateTime finishDate,
		 String category) {}

