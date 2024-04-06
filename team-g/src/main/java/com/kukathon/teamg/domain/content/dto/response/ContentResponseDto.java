package com.kukathon.teamg.domain.content.dto.response;

import com.kukathon.teamg.domain.content.entity.Content;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
public class ContentResponseDto {
    private List<Content> categoriesAndContents;
}
