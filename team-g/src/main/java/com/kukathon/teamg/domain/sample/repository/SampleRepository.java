package com.kukathon.teamg.domain.sample.repository;

import com.kukathon.teamg.domain.sample.entity.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleRepository extends JpaRepository<Sample,Long> {
    Boolean existsByText(String text);
}
