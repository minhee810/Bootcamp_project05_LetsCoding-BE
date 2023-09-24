package com.group.letscoding.domain.review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    @Query(value = "SELECT * FROM review r WHERE r.group_id = :id ORDER BY r.createdDate DESC", nativeQuery = true)
    Page<Review> reviewList(Integer id, Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM review r WHERE r.group_id = :id", nativeQuery = true)
    public Long countReviewByGroupId(Integer id);
}
