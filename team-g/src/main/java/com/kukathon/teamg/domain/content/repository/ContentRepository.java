package com.kukathon.teamg.domain.content.repository;

import com.kukathon.teamg.domain.content.entity.Content;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContentRepository extends JpaRepository<Content, Long> {

    @Query(value = "SELECT * FROM Content c INNER JOIN Category cat ON c.category_id = cat.category_id WHERE cat.member_id = :memberId AND c.date = :date", nativeQuery = true)
    List<Content> findAllByMemberIdAndDate(@Param("memberId") Long memberId,
        @Param("date") DateTime date);

    List<Content> findContentsByCategoryName(String category);
}
