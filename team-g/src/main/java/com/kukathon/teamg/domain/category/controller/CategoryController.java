package com.kukathon.teamg.domain.category.controller;

import com.kukathon.teamg.domain.category.repository.CategoryRepository;
import com.kukathon.teamg.domain.content.entity.Content;
import com.kukathon.teamg.domain.content.repository.ContentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final ContentRepository contentRepository;

    @GetMapping
    public List<Content> findContentsByCategory(
        @RequestParam("category") String category) {
        return contentRepository.findContentsByCategoryName(category);
    }
}
