package com.kukathon.teamg.domain.content.repository;

import com.kukathon.teamg.domain.content.entity.Content;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContentRepository extends JpaRepository<Content,Long> {
    Optional<Content> findContentByContentId(Long contentId);

    List<Content> findAllByMemberIdAndDate(Long memberId, DateTime date);
}
