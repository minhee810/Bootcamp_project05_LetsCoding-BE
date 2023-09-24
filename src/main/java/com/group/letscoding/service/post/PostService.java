package com.group.letscoding.service.post;

import com.group.letscoding.domain.studypost.StudyPost;
import com.group.letscoding.dto.post.PostResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
interface PostService {

    //게시판 게시물 가져오기
    List<StudyPost> getRecruitBoardList();

    public Page<StudyPost> getRecruitBoardPage(int page, int size);

    //검색
    public Page<StudyPost> findByTitleContaining(String keyword, Pageable pageable);
    public Page<StudyPost> findBySkillContaining(String keyword, Pageable pageable);

    StudyPost savePost(String title, String topic, Date startDate, String skills, Date endDate, int max_num, String content, Long userId);
    void updatePost(String title,String topic, String skills,
                         Date start_date, Date end_date, int max_num,
                         String content, Long user_id);
    PostResponseDto deletePost(int id) throws Exception;

    PostResponseDto getPostById(int recruitmentId);


}
