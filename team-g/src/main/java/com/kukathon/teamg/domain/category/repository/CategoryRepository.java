package com.kukathon.teamg.domain.category.repository;

import com.kukathon.teamg.domain.category.entity.Category;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String category);
}
