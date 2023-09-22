package com.group.letscoding.domain.studypost;

import com.group.letscoding.dto.post.PostInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudyPostRepository extends JpaRepository<StudyPost, Long> {

    List<StudyPost> findAllBy();

    //keyword 기반 제목 검색
    @Query("SELECT s.id,s.title,s.skills FROM StudyPost s WHERE s.title LIKE:keyword")
    Page<StudyPost> findByTitleContaining(@Param("keyword")String keyword, Pageable pageable);

    //keyword 기반 skill 검색
    @Query("SELECT s.id,s.title,s.skills FROM StudyPost s WHERE s.content LIKE:keyword")
    Page<StudyPost> findBySkillContaining(@Param("keyword")String keyword, Pageable pageable);

}
