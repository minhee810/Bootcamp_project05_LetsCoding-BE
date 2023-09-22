package com.group.letscoding.domain.review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
   // public List<Review> findByStudyGroup(StudyGroup studyGroup);
   // Page<Review> findByStudyGroup(StudyGroup studyGroup, Pageable pageable);
    // 그룹의 전체 리뷰 수를 가져옴
    // Long countByStudyGroup(StudyGroup group);
}
