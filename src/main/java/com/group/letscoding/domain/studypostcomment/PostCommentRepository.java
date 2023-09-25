package com.group.letscoding.domain.studypostcomment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostCommentRepository extends JpaRepository<PostComment, Integer> {

    @Query(value = "SELECT * FROM postcomment WHERE post_id = :post_id", nativeQuery = true)
    List<PostComment> findByRecruitmentId(int post_id);
}
