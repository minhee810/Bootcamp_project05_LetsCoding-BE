package com.group.letscoding.domain.studypost;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface StudyPostRepository extends JpaRepository<StudyPost,Integer> {

    List<StudyPost> findAllBy();

//    //1. 게시물 리스트의 title 뽑아오기
//    @Query("SELECT s.title FROM StudyPost s")
//    List<String> findAllTitles();
//
//    // 2. StudyPost의 title 칼럼을 파라미터로 입력 받아 post_id를 선택하는 쿼리 메소드
//    @Query("SELECT s.post_id FROM StudyPost s WHERE s.title = :title")
//    Integer findStackIdByStudyPostTitle(@Param("title") String title);
//
//    // 3. post_id를 기반으로 stack 엔티티에서 stack을 가져오는 쿼리 메소드
//    @Query("SELECT s.stack_name FROM Stack s WHERE s.post_id = :post_id")
//    List<String> findByPostId(int post_id);

    //keyword 기반 제목 검색
    @Query("SELECT s.post_id,s.title,s.skills FROM StudyPost s WHERE s.title LIKE:keyword")
    Page<StudyPost> findByTitleContaining(@Param("keyword")String keyword, Pageable pageable);

    //keyword 기반 skill 검색
    @Query("SELECT s.post_id,s.title,s.skills FROM StudyPost s WHERE s.content LIKE:keyword")
    Page<StudyPost> findBySkillContaining(@Param("keyword")String keyword, Pageable pageable);

    @Modifying
    @Query("UPDATE StudyPost p SET p.title = :title, p.topic = :topic, p.skills = :skills," +
            " p.start_date = :start_date, p.end_date = :end_date, " +
            "p.max_num = :max_num, p.content = :content WHERE p.id = :id")
    void updatePost(
            @Param("id") Long id,
            @Param("title") String title,
            @Param("topic") String topic,
            @Param("skills") String skills,
            @Param("start_date") Date start_date,
            @Param("end_date") Date end_date,
            @Param("max_num") int max_num,
            @Param("content") String content
    );

}
