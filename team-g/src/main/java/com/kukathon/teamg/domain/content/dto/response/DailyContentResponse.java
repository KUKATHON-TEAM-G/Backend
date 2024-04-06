package com.kukathon.teamg.domain.content.dto.response;

import com.kukathon.teamg.domain.category.entity.Category;
import com.kukathon.teamg.domain.content.entity.Content;
import java.util.HashMap;
import java.util.Map;

public class DailyContentResponse {

    private Map<Category, Content> categoryAndContent = new HashMap<>();

    public void addCategoryAndContent(Category category, Content content) {
        categoryAndContent.put(category, content);
    }
}
