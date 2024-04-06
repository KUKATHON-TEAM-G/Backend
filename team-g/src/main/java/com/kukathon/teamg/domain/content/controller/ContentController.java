package com.kukathon.teamg.domain.content.controller;

import com.kukathon.teamg.common.response.SuccessCode;
import com.kukathon.teamg.common.response.SuccessResponse;
import com.kukathon.teamg.domain.content.dto.response.CategoryAndContent;
import com.kukathon.teamg.domain.content.dto.response.ContentResponseDto;
import com.kukathon.teamg.domain.content.service.ContentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/contents")
@Slf4j
public class ContentController {

    private final ContentService contentService;

    @PatchMapping()
    public ResponseEntity<SuccessResponse> isChecked(@RequestParam(name = "contentId") Long contentId){
        contentService.updateCheck(contentId);
        return SuccessResponse.of(SuccessCode.OK);
    }

    @GetMapping()
    public ResponseEntity<SuccessResponse<List<CategoryAndContent>>> findAllContent(@PathVariable Long memberId,@PathVariable DateTime date){
        List<CategoryAndContent> response = contentService.findAllContents(memberId,date);
        return SuccessResponse.of(SuccessCode.OK,response);
    }
}
