package com.kukathon.teamg.domain.content.service;

import com.kukathon.teamg.auth.domain.CustomUserDetails;
import com.kukathon.teamg.common.error.ApplicationException;
import com.kukathon.teamg.domain.content.dto.response.CategoryAndContent;
import com.kukathon.teamg.domain.content.entity.Content;
import com.kukathon.teamg.domain.content.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import static com.kukathon.teamg.common.error.ErrorCode.CONTENT_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContentService {

    private final ContentRepository contentRepository;

    @Transactional
    public void updateCheck(Long contentId) {
        Content content = contentRepository.findById(contentId)
            .orElseThrow(() -> new ApplicationException(CONTENT_NOT_FOUND));

        content.updateCheck();
    }

    public CategoryAndContent findAllContents(CustomUserDetails customUserDetails,
        DateTime date) {
        CategoryAndContent categoryAndContent = new CategoryAndContent();

        contentRepository.findAllByMemberIdAndDate(customUserDetails.getId(), date)
            .stream()
            .forEach(content -> categoryAndContent.addCategoryAndContent(content.getCategory(),
	content));

        return categoryAndContent;
    }

    
}
